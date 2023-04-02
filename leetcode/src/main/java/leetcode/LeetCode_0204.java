package leetcode;

import java.util.Arrays;

/**
 * @author zhoujie
 * @date 2023/4/10 18:02
 * @description: 204. 计数质数
 * <p>
 * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：n = 1
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 5 * 106
 */
public class LeetCode_0204 {
    /**
     * @return int
     * @author zhoujie
     * @date 2023/4/10 18:02
     * @param: n
     * @description: 埃氏筛算法
     * 1- 如果x是质数，则x的所有倍数2x、3x...都不是指数；
     * 2-
     */
    public int countPrimes(int n) {
        int[] num = new int[n];
        int count = 0;
        Arrays.fill(num, 1);

        for (int i = 2; i < n; i++) {
            if (num[i] == 1) {
                count++;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        num[j] = 0;
                    }
                }
            }
        }
        return count;
    }
}
