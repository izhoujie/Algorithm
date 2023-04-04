package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * 1- 如果x是质数，则x的所有倍数2x、3x...都不是质数；
     * 2- x的x*x之前的非质数会被小于x的数筛掉，所以基于1可优化为从x*x开始而非从2x开始筛；
     */
    public int countPrimes1(int n) {
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

    /**
     * @return int
     * @author zhoujie
     * @date 2023/4/10 19:22
     * @param: n
     * @description: 欧拉筛算法
     * 1- 在埃氏筛的基础上改进，以及定理：对于任意自然数n，必有n=a*b，其中a是n的最小质因子；
     * 2- 即：对于从i到n的所有质数，当区间的某个非质数k首次满足k%i==0时，此时i必是k的最小质因子，当轮筛选即可终止；
     */
    public int countPrimes2(int n) {
        int[] num = new int[n];
        Arrays.fill(num, 1);
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (num[i] == 1) {
                list.add(i);
            }
            for (int j = 0; j < list.size() && i * list.get(j) < n; j++) {
                num[i * list.get(j)] = 0;
                if (i % list.get(j) == 0) {
                    break;
                }
            }
        }
        return list.size();
    }

}
