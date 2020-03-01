package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月1日 下午8:41:56 
 * @Description: 112. 路径总和
 *
	给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
	
	说明: 叶子节点是指没有子节点的节点。
	
	示例: 
	给定如下二叉树，以及目标和 sum = 22，
	
	              5
	             / \
	            4   8
	           /   / \
	          11  13  4
	         /  \      \
	        7    2      1
	返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/path-sum
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-
 */
public class LeetCode_0112 {

}

//  Definition for a binary tree node.
class TreeNode_0112 {
	int val;
	TreeNode_0112 left;
	TreeNode_0112 right;

	TreeNode_0112(int x) {
		val = x;
	}
}

class Solution_0112 {
	public boolean hasPathSum(TreeNode_0112 root, int sum) {
		if (root == null) {
			return false;
		} else if (root.left == null && root.right == null) {
			return sum == root.val;
		} else {
			return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
		}
	}

}