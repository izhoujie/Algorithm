package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月11日 下午8:32:42 
 * @Description: 110. 平衡二叉树
 *
 *
	给定一个二叉树，判断它是否是高度平衡的二叉树。
	
	本题中，一棵高度平衡二叉树定义为：
	
	一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
	
	示例 1:
	
	给定二叉树 [3,9,20,null,null,15,7]
	
	    3
	   / \
	  9  20
	    /  \
	   15   7
	返回 true 。
	
	示例 2:
	
	给定二叉树 [1,2,2,3,3,null,null,4,4]
	
	       1
	      / \
	     2   2
	    / \
	   3   3
	  / \
	 4   4
	返回 false 。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/balanced-binary-tree
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-递归解决；
 */
public class LeetCode_0110 {

}

// Definition for a binary tree node.
class TreeNode_0110 {
	int val;
	TreeNode_0110 left;
	TreeNode_0110 right;

	TreeNode_0110(int x) {
		val = x;
	}
}

class Solution_0110 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月18日 下午11:54:54 
	 * @param: @param root
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-对根及左右子树递归计算高度差；
	 *
	 */
	public boolean isBalanced_1(TreeNode_0110 root) {
		// 当前节点为null或者其左右节点的高度差不大于1
		return root == null || isBalanced_1(root.left) && isBalanced_1(root.right)
				&& Math.abs(maxDepth(root.left) - maxDepth(root.right)) < 2;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月19日 上午11:54:20 
	 * @param: @param root
	 * @param: @return
	 * @return: int
	 * @Description: 计算树高
	 *
	 */
	private int maxDepth(TreeNode_0110 root) {
		return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月18日 下午11:55:37 
	 * @param: @param root
	 * @param: @return
	 * @return: boolean
	 * @Description: 2-后续遍历，自底向上校验每层树的高度差，一旦不平衡直接返回，上层节点不再处理；
	 *
	 */
	public boolean isBalanced_2(TreeNode_0110 root) {
		return checkBlance(root) != -1;
	}

	private int checkBlance(TreeNode_0110 root) {
		if (root == null) {
			return 0;
		}
		// 递归计算左右子树的高度，若遇到-1代表树已在某一层已经不平衡，无需再递归了，直接返回
		int left = checkBlance(root.left);
		if (left == -1) {
			return -1;
		}
		int right = checkBlance(root.right);
		if (right == -1) {
			return -1;
		}
		// 一旦任意同层左右子树的高度差大于1就直接返回-1，-1会直接返回，相当于剪枝，避免后面无意义的递归
		return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) + 1;
	}
}
