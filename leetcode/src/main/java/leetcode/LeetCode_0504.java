package leetcode;

/**
 * @author zhoujie
 * @date 2022/3/8 16:06
 * @description: 504. 七进制数
 * <p>
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = 100
 * 输出: "202"
 * 示例 2:
 * <p>
 * 输入: num = -7
 * 输出: "-10"
 *  
 * <p>
 * 提示：
 * <p>
 * -107 <= num <= 107
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/base-7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0504 {
    /**
     * @return java.lang.String
     * @author zhoujie
     * @date 2022/3/8 16:07
     * @param: num
     * @description: 拼接取余
     */
    public String convertToBase7(int num) {
        // jdk内置
        // Integer.toString(num,7);
        if (num == 0) {
            return "0";
        }
        boolean f = num < 0;
        if (f) {
            num = Math.abs(num);
        }
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % 7);
            num /= 7;
        }
        if (f) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }
}
