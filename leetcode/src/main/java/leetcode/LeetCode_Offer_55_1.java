package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月22日 下午9:06:54 
 * @Description: 面试题55 - I. 二叉树的深度
 *
	输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
	
	例如：
	
	给定二叉树 [3,9,20,null,null,15,7]，
	
	    3
	   / \
	  9  20
	    /  \
	   15   7
	返回它的最大深度 3 。
	
	 
	
	提示：
	
	节点总数 <= 10000
	注意：本题与主站 104 题相同：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_55_1 {

}

// Definition for a binary tree node.
class TreeNode_Offer_55_1 {
	int val;
	TreeNode_Offer_55_1 left;
	TreeNode_Offer_55_1 right;

	TreeNode_Offer_55_1(int x) {
		val = x;
	}
}

class Solution_Offer_55_1 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月22日 下午9:07:31 
	 * @param: @param root
	 * @param: @return
	 * @return: int
	 * @Description: 1-递归计算；
	 *
	 */
	public int maxDepth(TreeNode_Offer_55_1 root) {
		return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}
}