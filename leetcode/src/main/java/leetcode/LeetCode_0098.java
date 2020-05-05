package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月5日 下午5:09:14 
 * @Description: 98. 验证二叉搜索树
 *
	给定一个二叉树，判断其是否是一个有效的二叉搜索树。
	
	假设一个二叉搜索树具有如下特征：
	
	节点的左子树只包含小于当前节点的数。
	节点的右子树只包含大于当前节点的数。
	所有左子树和右子树自身必须也是二叉搜索树。
	示例 1:
	
	输入:
	    2
	   / \
	  1   3
	输出: true
	示例 2:
	
	输入:
	    5
	   / \
	  1   4
	     / \
	    3   6
	输出: false
	解释: 输入为: [5,1,4,null,null,3,6]。
	     根节点的值为 5 ，但是其右子节点值为 4 。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/validate-binary-search-tree
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0098 {

}

//  Definition for a binary tree node.
class TreeNode_0098 {
	int val;
	TreeNode_0098 left;
	TreeNode_0098 right;

	TreeNode_0098(int x) {
		val = x;
	}
}

class Solution_0098 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月5日 下午5:10:13 
	 * @param: @param root
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-二叉搜索树的中序遍历恰是一个单调递增序列，中序遍历二叉树判断是否为递增序列；
	 *
	 */
	private long min = Long.MIN_VALUE;

	public boolean isValidBST(TreeNode_0098 root) {
		if (root == null) {
			return true;
		} else {
			if (isValidBST(root.left) && root.val > min) {
				min = root.val;
				return isValidBST(root.right);
			} else {
				return false;
			}
		}
	}
}