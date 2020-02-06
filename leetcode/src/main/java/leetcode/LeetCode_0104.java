package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月11日 下午8:25:09 
 * @Description: 104. 二叉树的最大深度
 *
	给定一个二叉树，找出其最大深度。
	
	二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
	
	说明: 叶子节点是指没有子节点的节点。
	
	示例：
	给定二叉树 [3,9,20,null,null,15,7]，
	
	    3
	   / \
	  9  20
	    /  \
	   15   7
	返回它的最大深度 3 。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-递归解决；
 */
public class LeetCode_0104 {

}

// Definition for a binary tree node.
class TreeNode_0104 {
	int val;
	TreeNode_0104 left;
	TreeNode_0104 right;

	TreeNode_0104(int x) {
		val = x;
	}
}

class Solution_0104 {
	public int maxDepth(TreeNode_0104 root) {
		return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}
}
