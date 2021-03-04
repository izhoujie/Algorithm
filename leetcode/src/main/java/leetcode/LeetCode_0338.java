package leetcode;

/**
 * @author zhoujie
 * @date 2021/3/4 上午10:41
 * @description: 338. 比特位计数
 * <p>
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 * <p>
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0338 {
}

class Solution_0338 {
    /**
     * @return int[]
     * @author zhoujie
     * @date 2021/3/4 上午10:41
     * @param: num
     * @description:
     */
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            // 思路1： i&(i-1) 得到的是i去掉最右侧一个1的值，
            // 且 i&(i-1)<i，而ans[i&(i-1)]已经计算过了，故ans[i] = ans[i & (i - 1)] + 1
            ans[i] = ans[i & (i - 1)] + 1;
            // 思路2：i>>1得到的是个偶数，ans[i/2]已计算过
            // 而因为i可能等于 (i/2)*2+1或者（i/2）*2，故需要对i的末尾01分别计算
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }
}