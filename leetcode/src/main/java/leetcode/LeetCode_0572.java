package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月7日 上午12:45:22 
 * @Description: 572. 另一个树的子树
 *
	给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
	
	示例 1:
	给定的树 s:
	
	     3
	    / \
	   4   5
	  / \
	 1   2
	给定的树 t：
	
	   4 
	  / \
	 1   2
	返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
	
	示例 2:
	给定的树 s：
	
	     3
	    / \
	   4   5
	  / \
	 1   2
	    /
	   0
	给定的树 t：
	
	   4
	  / \
	 1   2
	返回 false。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/subtree-of-another-tree
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LeetCode_0572 {

}

//  Definition for a binary tree node.
class TreeNode_0572 {
	int val;
	TreeNode_0572 left;
	TreeNode_0572 right;

	TreeNode_0572(int x) {
		val = x;
	}
}

class Solution_0572 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月7日 上午12:46:19 
	 * @param: @param s
	 * @param: @param t
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-递归判断t是否为s本身或者是其子树之一；
	 *
	 */
	public boolean isSubtree_1(TreeNode_0572 s, TreeNode_0572 t) {
		if (s == null && t == null) {
			return true;
		} else if (s != null && t != null) {
			// t可能是s本身或者是s的左子树/右子树
			return checkSubtree(s, t) || isSubtree_1(s.left, t) || isSubtree_1(s.right, t);
		} else {
			return false;
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月7日 上午12:52:54 
	 * @param: @param s
	 * @param: @param t
	 * @param: @return
	 * @return: boolean
	 * @Description: 是否子树判断
	 *
	 */
	private boolean checkSubtree(TreeNode_0572 s, TreeNode_0572 t) {
		if (s == null && t == null) {
			return true;
		} else if (s != null && t != null && s.val == t.val) {
			return checkSubtree(s.left, t.left) && checkSubtree(s.right, t.right);
		} else {
			return false;
		}
	}
}
