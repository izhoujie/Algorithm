package leetcode;

/**
 * @author zhoujie
 * @date 2021/4/25 上午9:53
 * @description: 897. 递增顺序搜索树
 * <p>
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 *  
 * <p>
 * 提示：
 * <p>
 * 树中节点数的取值范围是 [1, 100]
 * 0 <= Node.val <= 1000
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-order-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0897 {
}


//  Definition for a binary tree node.
class TreeNode_0897 {
    int val;
    TreeNode_0897 left;
    TreeNode_0897 right;

    TreeNode_0897() {
    }

    TreeNode_0897(int val) {
        this.val = val;
    }

    TreeNode_0897(int val, TreeNode_0897 left, TreeNode_0897 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution_0897 {
    /**
     * @return leetcode.TreeNod_0897
     * @author zhoujie
     * @date 2021/4/25 上午9:58
     * @param: root
     * @description: 中序遍历时拼接节点，并将所有节点左子树置空
     */
    TreeNode_0897 node = new TreeNode_0897(-1);
    TreeNode_0897 temp = node;

    public TreeNode_0897 increasingBST(TreeNode_0897 root) {
        if (root == null) {
            return null;
        }
        increasingBST(root.left);
        temp.right = root;
        root.left = null;
        temp = temp.right;
        increasingBST(root.right);
        return node.right;
    }
}