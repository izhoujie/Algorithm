package others;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoujie
 * @date 2021/5/13 下午5:00
 * @description: Id生成各方案研究
 */
public class IdGenerator {
}

/**
 * Twitter_Snowflake
 * SnowFlake的结构(每部分用-分开):
 * 0-00000000000000000000000000000000000000000-00000-00000-000000000000
 * 第一部分：
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，ID要是正数，最高位是0
 * 第二部分：
 * 41位毫秒数，不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 自定义起始时间截)，
 * 自定义起始时间戳即人为指定的算法起始时间，当前时间即生成ID时的时间戳
 * 41位的时间截，可以使用约69年， (1L << 41) / (365 * 24 * 3600 * 1000）≈ 69
 * 第三、四部分：
 * 10位的数据机器位，可以部署在1024(1L<<10)个节点，包括5位datacenterId（机房）和5位workerId（机器号）
 * 第五部分：
 * 12位序列，每毫秒可生成序列号数，共4096(1L<<12)个ID序号
 * 以上5部分总64bit，即需要一个Long整型来记录
 * SnowFlake的优点：
 * - 整体按时间自增排序
 * - Long整型ID，存储高效，检索高效
 * - 分布式系统内无ID碰撞（各分区由datacenterId和workerId来区分）
 * - 生成效率高，占用系统资源少，理论每秒可生成1000 * 4096 = 4096000个
 * SnowFlake的缺点：
 * - 时钟回拨问题，尤其在高并发中，时钟回拨可能会生产出重复的ID
 */
class SnowFlakeIdWorker {

    /**
     * 指定起始时间戳 (2021-05-21 00:00:00)
     */
    private final long twepoch = 1621440000000L;

    /**
     * 数据中心/机房标识所占bit位数
     */
    private final long datacenterIdBits = 5L;

    /**
     * 机器标识所占bit位数
     */
    private final long workerIdBits = 5L;

    /**
     * 每毫秒下的序列号所占bit位数
     */
    private final long sequenceBits = 12L;

    /**
     * 数据中心掩码，即最大支持32个机房
     */
    private final long maxDatacenterId = ~(-1L << datacenterIdBits);

    /**
     * 机器掩码，即最大支持32个机器
     */
    private final long maxWorkerId = ~(-1L << workerIdBits);

    /**
     * 每毫秒序列号的掩码
     */
    private final long sequenceMask = ~(-1L << sequenceBits);

    /**
     * 机器ID表示的bit在long中位置，需要左移的位数（12）
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据中心ID表示的bit在long中的位置，需要左移的位数(12+5)
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截部分需要左移的位数(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 机器ID(0~31)
     */
    private long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private long datacenterId;

    /**
     * 每毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 最后一次生成ID时的时间截
     */
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     *
     * @param workerId     机器ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    public SnowFlakeIdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获得下一个ID，synchronized同步的，此处必须同步
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        // 若当前时间戳小于最后一次生成ID时的时间戳，说明系统时钟回退过，此时无法保证ID的唯一性，算法抛异常退出
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        // 若当前时间戳等于最后一次生成ID时的时间戳（同一毫秒内），则进行序列号累加
        if (lastTimestamp == timestamp) {
            // 此操作可获得的最大值是4095，最小值是0，在溢出时为0
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 若当前时间戳大于最后一次生成ID时的时间戳，则序列号需要重置到0
            sequence = 0L;
        }
        // 更新记录本次时间戳
        lastTimestamp = timestamp;
        // 位运算，获得最终的ID并返回
        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间戳
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        SnowFlakeIdWorker idWorker = new SnowFlakeIdWorker(0, 0);
        for (int i = 0; i < 10; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
        }
    }
}

/**
 * @author zhoujie
 * @date 2021/5/17 下午5:10
 * @description: 基于SnowFlake改造的32长度hex进制的ID生成方案
 * 总128bit长度，32hex长度：
 * <p>
 * 32bit记录k8s的主机IP + 32bit记录pod的IP + 52bit时间戳 + 12bit序列号
 * <p>
 * 32bit + 32bit + 52bit +12bit = 8hex + 8hex + 13hex + 3hex = 32hex
 */
class SnowFlakeIdUtil {

    /**
     * 指定起始时间戳 (2021-05-21 00:00:00)
     */
    private static final long twepoch = 1420041600000L;

    /**
     * 每毫秒下的序列号所占bit位数
     */
    private static final long sequenceBits = 12L;

    /**
     * 每毫秒序列号的掩码
     */
    private static final long sequenceMask = ~(-1L << sequenceBits);

    /**
     * 每毫秒内序列(0~4095)
     */
    private static long sequence = 0L;

    /**
     * 最后一次生成ID时的时间截
     */
    private static long lastTimestamp = -1L;

    /**
     * HOST_IP
     */
    private static final String HOST_IP = "222.222.222.222";

    /**
     * POD_IP
     */
    private static final String POD_IP = "111.111.111.111";

    /**
     * ip的处理后16进制表示的部分
     */
    private static String IP_HEX_PART = null;

    /**
     * 静态工具类，构造器私有化
     */
    private SnowFlakeIdUtil() {
    }

    /**
     * 获得下一个ID，synchronized同步的，此处必须同步
     *
     * @return SnowflakeId
     */
    public static synchronized long nextId() {
        long timestamp = timeGen();
        // 若当前时间戳小于最后一次生成ID时的时间戳，说明系统时钟回退过，此时无法保证ID的唯一性，算法抛异常退出
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        // 若当前时间戳等于最后一次生成ID时的时间戳（同一毫秒内），则进行序列号累加
        if (lastTimestamp == timestamp) {
            // 此操作可获得的最大值是4095，最小值是0，在溢出时为0
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 若当前时间戳大于最后一次生成ID时的时间戳，则序列号需要重置到0
            sequence = 0L;
        }
        // 更新记录本次时间戳
        lastTimestamp = timestamp;
        // 位运算，此处拼接时间戳和序列号一并返回是为了效率，后面处理时还是需要拆开各自处理
        return (timestamp - twepoch) << sequenceBits | sequence;
    }

    /**
     * @author zhoujie
     * @date 2021/5/17 下午5:15
     * @description: 改造后的生成ID方案，生成32长度16进制的ID：
     * <p>
     * host_ip + pod_ip + 时间戳 + 序列号
     * <p>
     * 8hex + 8hex + 13hex +3hex
     */
    private static String getMsgId() {
        long nextId = nextId();
        long seq = nextId & sequenceMask;
        long unixTime = nextId >> sequenceBits;

        StringBuilder msgIdBuffer = new StringBuilder(32);
        // 末3位hex为序列号
        msgIdBuffer.append(Long.toHexString(seq));
        while (msgIdBuffer.length() < 3) {
            msgIdBuffer.insert(0, "0");
        }
        // 中间13位hex为时间戳
        msgIdBuffer.insert(0, Long.toHexString(unixTime));
        while (msgIdBuffer.length() < 16) {
            msgIdBuffer.insert(0, "0");
        }
        // IP为环境信息，只需要初始化一次
        if (IP_HEX_PART == null) {
            IP_HEX_PART = ipToHexString(HOST_IP) + ipToHexString(POD_IP);
        }
        // 前16位为环境相关的两个IP地址
        return msgIdBuffer.insert(0, IP_HEX_PART).toString().toUpperCase();
    }

    /**
     * @return java.lang.String
     * @author zhoujie
     * @date 2021/5/17 下午5:25
     * @param: ip
     * @description: 把ip转为16进制字符串表示
     */
    private static String ipToHexString(String ip) {
        if (ip == null) {
            return "00000000";
        }
        String[] ipPort = ip.split("\\.");
        if (ipPort.length < 4) {
            return "00000000";
        }
        StringBuilder ipBuffer = new StringBuilder(8);
        for (String s : ipPort) {
            String s1 = Integer.toHexString(Integer.parseInt(s));
            ipBuffer.append(s1.length() > 1 ? s1 : "0" + s1);
        }
        return ipBuffer.toString();
    }

    /**
     * @return java.lang.String
     * @author zhoujie
     * @date 2021/5/17 下午5:36
     * @param: msgId
     * @description: 泛解析msg获取信息
     */
    private static Map<String, String> parseMsgIdInfo(String msgId) {
        if (msgId == null) {
            return null;
        }
        int len = msgId.length();
        if (len != 32) {
            return null;
        }
        // 项目用的json对象存储并返回json字符串对象，简化import，此处使用map，意会即可
        HashMap<String, String> msgIdInfo = new HashMap<>();
        msgIdInfo.put("msgId", msgId);
        msgIdInfo.put("host_ip", hexStrToIp(msgId.substring(0, 8)));
        msgIdInfo.put("pod_ip", hexStrToIp(msgId.substring(8, 16)));
        msgIdInfo.put("sequence", new BigInteger(msgId.substring(30), 16).toString());
        long timestamp = Long.parseLong(msgId.substring(16, 29), 16) + twepoch;
        msgIdInfo.put("dateTime", LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()).toString());
        return msgIdInfo;
    }

    /**
     * @return java.lang.String
     * @author zhoujie
     * @date 2021/5/18 下午3:19
     * @param: hexIpStr
     * @description: 把16进制的字符串ip转为常规显示
     */
    private static String hexStrToIp(String hexIpStr) {

        int step = 2;
        StringBuilder ipBuffer = new StringBuilder(17);
        for (int i = 0; i < hexIpStr.length(); i += step) {
            String ipPart = hexIpStr.substring(i, i + step);
            ipBuffer.append(new BigInteger(ipPart, 16).toString()).append(".");
        }
        ipBuffer.setLength(ipBuffer.length() - 1);
        return ipBuffer.toString();
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间戳
     *
     * @return 当前时间(毫秒)
     */
    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(SnowFlakeIdUtil.getMsgId());
        }
        String msgId = SnowFlakeIdUtil.getMsgId();
        Map<String, String> stringStringMap = parseMsgIdInfo(msgId);
        for (Map.Entry<String, String> entry : stringStringMap.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }
}
