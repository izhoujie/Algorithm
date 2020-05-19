package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月18日 下午6:47:13 
 * @Description: 面试题55 - II. 平衡二叉树
 *
	输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。

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
	
	 
	
	限制：
	
	1 <= 树的结点个数 <= 10000
	注意：本题与主站 110 题相同：https://leetcode-cn.com/problems/balanced-binary-tree/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_55_2 {

}

//  Definition for a binary tree node.
class TreeNode_Offer_55_2 {
	int val;
	TreeNode_Offer_55_2 left;
	TreeNode_Offer_55_2 right;

	TreeNode_Offer_55_2(int x) {
		val = x;
	}
}

class Solution_Offer_55_2 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月18日 下午6:48:08 
	 * @param: @param root
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-递归比较左右子树的高度差
	 *
	 */
	public boolean isBalanced_1(TreeNode_Offer_55_2 root) {
		return root == null || isBalanced_1(root.left) && isBalanced_1(root.right)
				&& Math.abs(maxDepth(root.left) - maxDepth(root.right)) < 2;

	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月18日 下午7:04:59 
	 * @param: @param root
	 * @param: @return
	 * @return: int
	 * @Description: 计算树高
	 *
	 */
	private int maxDepth(TreeNode_Offer_55_2 root) {
		return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月19日 上午12:01:11 
	 * @param: @param root
	 * @param: @return
	 * @return: boolean
	 * @Description: 2-后续遍历，自底向上校验每层树的高度差，一旦不平衡直接返回，上层节点不再处理；
	 *
	 */
	public boolean isBalanced_2(TreeNode_Offer_55_2 root) {
		return checkBlance(root) != -1;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月19日 上午12:01:15 
	 * @param: @param root
	 * @param: @return
	 * @return: int
	 * @Description: TODO
	 *
	 */
	private int checkBlance(TreeNode_Offer_55_2 root) {
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