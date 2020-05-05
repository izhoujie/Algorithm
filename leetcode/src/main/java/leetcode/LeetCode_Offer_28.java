package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月3日 上午12:00:29 
 * @Description: 面试题28. 对称的二叉树
 *
	请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
	
	例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
	
	    1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
	但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
	
	    1
	   / \
	  2   2
	   \   \
	   3    3
	
	 
	
	示例 1：
	
	输入：root = [1,2,2,3,4,4,3]
	输出：true
	示例 2：
	
	输入：root = [1,2,2,null,3,null,3]
	输出：false
	 
	
	限制：
	
	0 <= 节点个数 <= 1000
	
	注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_28 {

}

// Definition for a binary tree node.
class TreeNode_Offer_28 {
	int val;
	TreeNode_Offer_28 left;
	TreeNode_Offer_28 right;

	TreeNode_Offer_28(int x) {
		val = x;
	}
}

class Solution_Offer_28 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月3日 上午12:36:29 
	 * @param: @param root
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-先验根节点，然后从左右子节点开始镜像递归；
	 *
	 */
	public boolean isSymmetric(TreeNode_Offer_28 root) {
		if (root == null) {
			return true;
		} else {
			return check(root.left, root.right);
		}
	}

	private boolean check(TreeNode_Offer_28 left, TreeNode_Offer_28 right) {
		if (left == null && right == null) {
			return true;
		} else if (left != null && right != null) {
			return left.val == right.val && check(left.left, right.right) && check(left.right, right.left);
		} else {
			return false;
		}
	}
}