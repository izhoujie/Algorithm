package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月22日 下午6:03:04 
 * @Description: 面试题26. 树的子结构
 *
	输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
	
	B是A的子结构， 即 A中有出现和B相同的结构和节点值。
	
	例如:
	给定的树 A:
	
	     3
	    / \
	   4   5
	  / \
	 1   2
	给定的树 B：
	
	   4 
	  /
	 1
	返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
	
	示例 1：
	
	输入：A = [1,2,3], B = [3,1]
	输出：false
	示例 2：
	
	输入：A = [3,4,5,1,2], B = [4,1]
	输出：true
	限制：
	
	0 <= 节点个数 <= 10000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_26 {

}

// Definition for a binary tree node.
class TreeNode_Offer_26 {
	int val;
	TreeNode_Offer_26 left;
	TreeNode_Offer_26 right;

	TreeNode_Offer_26(int x) {
		val = x;
	}
}

class Solution_Offer_26 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月22日 下午6:07:38 
	 * @param: @param A
	 * @param: @param B
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-递归校验B是否是A本身或是A某子树的一部分
	 *
	 */
	public boolean isSubStructure(TreeNode_Offer_26 A, TreeNode_Offer_26 B) {
		if (A == null && B == null) {
			return true;
		} else if (A != null && B != null) {
			// t可能是s本身或者是s的左子树/右子树
			return checkSubtree(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
		} else {
			return false;
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月22日 下午6:07:56 
	 * @param: @param A
	 * @param: @param B
	 * @param: @return
	 * @return: boolean
	 * @Description: 判断B是否是A某子树的一部分
	 *
	 */
	private boolean checkSubtree(TreeNode_Offer_26 A, TreeNode_Offer_26 B) {
		// 此时t可以为null，表明t已经处理完，s是否为null已无所谓
		if (B == null) {
			return true;
			// 此时t不为null，那么s也必须不为null，且值也必须相等
		} else if (A != null && B != null && A.val == B.val) {
			return checkSubtree(A.left, B.left) && checkSubtree(A.right, B.right);
		} else {
			return false;
		}
	}
}