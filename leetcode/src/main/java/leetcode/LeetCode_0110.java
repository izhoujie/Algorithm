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
	public boolean isBalanced(TreeNode_0110 root) {
		// 当前节点为null或者其左右节点的高度差不大于1
		return root == null || isBalanced(root.left) && isBalanced(root.right)
				&& Math.abs(maxDepth(root.left) - maxDepth(root.right)) < 2;
	}

	/**
	 * @author ZhouJie
	 * @date 2020年1月12日 上午12:27:38 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月12日 上午12:27:38]  
	 * @UpdateRemark:计算节点的最大高
	 *
	 */
	private int maxDepth(TreeNode_0110 root) {
		return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}
}
