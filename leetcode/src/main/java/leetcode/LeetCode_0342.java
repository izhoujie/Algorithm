package leetcode;

/**
 * @author zhoujie
 * @date 2021/5/31 上午10:52
 * @description: 342. 4的幂
 * <p>
 * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 16
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：n = 5
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：n = 1
 * 输出：true
 *  
 * <p>
 * 提示：
 * <p>
 * -231 <= n <= 231 - 1
 *  
 * <p>
 * 进阶：
 * <p>
 * 你能不使用循环或者递归来完成本题吗？
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-four
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0342 {
}

class Solution_0342 {
    /**
     * @return boolean
     * @author zhoujie
     * @date 2021/5/31 上午10:55
     * @param: n
     * @description: 判断bit位是否有且只有一个1且在正确的位置
     */
    public boolean isPowerOfFour_1(int n) {
        // 以4为底的所有正数幂|运算后的bit表示
        int mask = 0b01010101010101010101010101010101;
        return n > 0 && (n & mask) == n && (n & (n - 1)) == 0;
    }

    /**
     * @return boolean
     * @author zhoujie
     * @date 2021/5/31 下午1:49
     * @param: n
     * @description: n的bit位必然只有一个1，且n除以3余数必定是1
     */
    public boolean isPowerOfFour_2(int n) {
        // n的bit位只有一位是1，n除以3余数必定是1
        return (n & (n - 1)) == 0 && n % 3 == 1;
    }

}