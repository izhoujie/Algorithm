package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月10日 下午10:39:22 
 * @Description: 101. 对称二叉树
 *
	给定一个二叉树，检查它是否是镜像对称的。
	
	例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
	
	    1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
	但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
	
	    1
	   / \
	  2   2
	   \   \
	   3    3
	说明:
	
	如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/symmetric-tree
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-递归校验；
 */
public class LeetCode_0101 {

}

// Definition for a binary tree node.
class TreeNode_0101 {
	int val;
	TreeNode_0101 left;
	TreeNode_0101 right;

	TreeNode_0101(int x) {
		val = x;
	}
}

class Solution_0101 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月4日 下午11:34:28 
	 * @param: @param root
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-递归校验；
	 *
	 */
	public boolean isSymmetric(TreeNode_0101 root) {
		if (root == null) {
			return true;
		} else {
			return checkTree(root.left, root.right);
		}
	}

	private boolean checkTree(TreeNode_0101 left, TreeNode_0101 right) {
		// 左右均不为null，且值也相等时继续递归，注意递归的镜像参数
		if (left != null && right != null && left.val == right.val) {
			return checkTree(left.right, right.left) && checkTree(left.left, right.right);
			// 左右均为null时，返回true，该层递归结束
		} else if (left == null && right == null) {
			return true;
			// 左右一个为null一个不为null或左右值不等时，返回false，该层递归结束
		} else {
			return false;
		}
	}
}
