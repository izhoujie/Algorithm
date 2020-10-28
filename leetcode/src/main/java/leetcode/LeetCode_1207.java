package leetcode;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhoujie
 * @date 2020/10/28 10:01
 * @description: 1207. 独一无二的出现次数
 * <p>
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * <p>
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,2,2,1,1,3]
 * 输出：true
 * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
 * 示例 2：
 * <p>
 * 输入：arr = [1,2]
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * 输出：true
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 1000
 * -1000 <= arr[i] <= 1000
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-number-of-occurrences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_1207 {
}

class Solution_1207 {
    /**
     * @return boolean
     * @author zhoujie
     * @date 2020/10/28 10:02
     * @param: arr
     * @description: 先用HashMap统计，再用HashSet校验
     */
    public boolean uniqueOccurrences_1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int val : arr) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        // 也可以一次性的全加入判断，但是逐个加入可提前结束，效果要好点:
        // 若value的值都不相等，那必与key的总数大小一致
        // return map.size() == new HashSet<Integer>(map.values()).size();
        for (Integer val : map.values()) {
            if (!set.add(val)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return boolean
     * @author zhoujie
     * @date 2020/10/28 10:47
     * @param: arr
     * @description: 因为数组的值有确定范围[-1000,1000]，故也可以先用数组统计，再用HashSet校验
     */
    public boolean uniqueOccurrences_2(int[] arr) {
        // 把负值+1000变为正值以便数组下标都合法
        int[] all = new int[2001];
        for (int val : arr) {
            all[val + 1000] += 1;
        }
        Set<Integer> set = new HashSet<>();
        for (int val : all) {
            if (val > 0 && !set.add(val)) {
                return false;
            }
        }
        return true;
    }
}