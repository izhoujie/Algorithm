package leetcode;

/**
 * @author zhoujie
 * @date 2020/12/16 下午4:56
 * @description: 738. 单调递增的数字
 * <p>
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * <p>
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 * <p>
 * 示例 1:
 * <p>
 * 输入: N = 10
 * 输出: 9
 * 示例 2:
 * <p>
 * 输入: N = 1234
 * 输出: 1234
 * 示例 3:
 * <p>
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotone-increasing-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0738 {
}

class Solution_0738 {
    /**
     * @return int
     * @author zhoujie
     * @date 2020/12/16 下午4:56
     * @param: N
     * @description: 从后向前贪心，若非递增则低位直接变9
     */
    public int monotoneIncreasingDigits(int N) {
        int t = 1;
        int r = 0;
        int M = N;
        while (t <= M) {
            // 取当前位值及其前一位值
            int d1 = N % 10;
            int d2 = N / 10 % 10;
            // 满足单调递增，直接加其值，否则该位及其后位均为9，且原值减10（因为已经/10了所以减10即可）
            if (d1 >= d2) {
                r += t * d1;
            } else {
                r = t * 10 - 1;
                N -= 10;
            }
            t *= 10;
            N /= 10;
        }
        return r;
    }
}