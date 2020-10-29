package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhoujie
 * @date 2020/10/29 10:20
 * @description: 129. 求根到叶子节点数字之和
 * <p>
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * <p>
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * <p>
 * 计算从根到叶子节点生成的所有数字之和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 1
 * / \
 * 2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 * <p>
 * 输入: [4,9,0,5,1]
 * 4
 * / \
 * 9   0
 *  / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0129 {
}

//  Definition for a binary tree node.
class TreeNode_0129 {
    int val;
    TreeNode_0129 left;
    TreeNode_0129 right;

    TreeNode_0129(int x) {
        val = x;
    }
}

class Solution_0129 {
    /**
     * @return int
     * @author zhoujie
     * @date 2020/10/29 10:21
     * @param: root
     * @description: 迭代/BFS/树的层次遍历
     */
    public int sumNumbers_1(TreeNode_0129 root) {
        int sum = 0;
        if (root == null) {
            return sum;
        }
        // 每层的处理/BFS
        Deque<TreeNode_0129> deque = new LinkedList<>();
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size-- > 0) {
                TreeNode_0129 pop = deque.pollLast();
                // 多余 纯为免除idea的黄标
                if (pop == null) {
                    continue;
                }
                // 左右均null就累加，否则累加到子节点继续处理
                if (pop.left == null && pop.right == null) {
                    sum += pop.val;
                } else {
                    if (pop.left != null) {
                        pop.left.val += pop.val * 10;
                        deque.addLast(pop.left);
                    }
                    if (pop.right != null) {
                        pop.right.val += pop.val * 10;
                        deque.addLast(pop.right);
                    }
                }
            }
        }
        return sum;
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2020/10/29 10:37
     * @param: root
     * @description: 递归/DFS
     */
    public int sumNumbers_2(TreeNode_0129 root) {
        TreeNode_0129 sum = new TreeNode_0129(0);
        deal(root, sum);
        return sum.val;
    }

    private void deal(TreeNode_0129 root, TreeNode_0129 sum) {
        if (root != null) {
            // 左右子节点均为null，结束递归累加值，否则值处理覆盖子节点并递归
            if (root.left == null && root.right == null) {
                sum.val += root.val;
            } else {
                if (root.left != null) {
                    root.left.val += root.val * 10;
                    deal(root.left, sum);
                }
                if (root.right != null) {
                    root.right.val += root.val * 10;
                    deal(root.right, sum);
                }
            }
        }
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2020/10/29 11:07
     * @param: root
     * @description: DFS-更纯粹的写法
     */
    public int sumNumbers_3(TreeNode_0129 root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode_0129 root, int sum) {
        if (root == null) {
            return 0;
        } else {
            sum = sum * 10 + root.val;
            if (root.left == null && root.right == null) {
                return sum;
            } else {
                return dfs(root.left, sum) + dfs(root.right, sum);
            }
        }
    }

}