package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoujie
 * @date 2023/5/30 15:56
 * @description: 1110. 删点成林
 * <p>
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 * <p>
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 * <p>
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * 输出：[[1,2,null,4],[6],[7]]
 * 示例 2：
 * <p>
 * 输入：root = [1,2,4,null,3], to_delete = [3]
 * 输出：[[1,2,4]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数最大为 1000。
 * 每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
 * to_delete.length <= 1000
 * to_delete 包含一些从 1 到 1000、各不相同的值。
 */
public class LeetCode_1110 {
    /**
     * @return java.util.List<leetcode.TreeNode_1110>
     * @author zhoujie
     * @date 2023/5/30 17:16
     * @param: root
     * @param: to_delete
     * @description: DFS
     */
    public List<TreeNode_1110> delNodes(TreeNode_1110 root, int[] to_delete) {
        List<TreeNode_1110> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        boolean[] f = new boolean[1001];
        for (int v : to_delete) {
            f[v] = true;
        }
        TreeNode_1110 node = dfs_1110(root, f, list);
        if (node != null) {
            list.add(node);
        }
        return list;
    }

    private TreeNode_1110 dfs_1110(TreeNode_1110 root, boolean[] f, List<TreeNode_1110> list) {
        if (root == null) {
            return null;
        }
        root.left = dfs_1110(root.left, f, list);
        root.right = dfs_1110(root.right, f, list);
        if (f[root.val]) {
            if (root.left != null) {
                list.add(root.left);
            }
            if (root.right != null) {
                list.add(root.right);
            }
            return null;
        }
        return root;
    }
}

//Definition for a binary tree node.
class TreeNode_1110 {
    int val;
    TreeNode_1110 left;
    TreeNode_1110 right;

    TreeNode_1110() {
    }

    TreeNode_1110(int val) {
        this.val = val;
    }

    TreeNode_1110(int val, TreeNode_1110 left, TreeNode_1110 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
