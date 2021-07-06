package leetcode;

/**
 * @author zhoujie
 * @date 2021/7/6 下午8:56
 * @description: 70. 爬楼梯
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0070 {
}

class Solution_0070 {
    /**
     * @return int
     * @author zhoujie
     * @date 2021/7/6 下午8:57
     * @param: n
     * @description: 类似斐波那契--动态规划
     */
    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int a = 1;
        int b = 2;
        int c;
        while (n-- > 2) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}