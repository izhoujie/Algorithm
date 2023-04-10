package leetcode;

import java.util.*;

/**
 * @author zhoujie
 * @date 2023/4/10 22:02
 * @description: 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * 示例 2：
 * <p>
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class LeetCode_0077 {
    ArrayList<Integer> temp = new ArrayList<>();
    ArrayList<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        combineDfs(1, n, k);
        return list;
    }

    private void combineDfs(int now, int n, int k) {
        // 剩余数的长度不足k时直接终止
        if (temp.size() + n - now + 1 < k) {
            return;
        }
        if (temp.size() == k) {
            list.add(new ArrayList<>(temp));
            return;
        }
        // 选当前值
        temp.add(now);
        combineDfs(now + 1, n, k);
        // 不选当前值
        temp.remove(temp.size() - 1);
        combineDfs(now + 1, n, k);
    }
}
