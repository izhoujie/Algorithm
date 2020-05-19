package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月17日 上午1:15:08 
 * @Description: 面试题54. 二叉搜索树的第k大节点
 *
	给定一棵二叉搜索树，请找出其中第k大的节点。
	
	示例 1:
	
	输入: root = [3,1,4,null,2], k = 1
	   3
	  / \
	 1   4
	  \
	   2
	输出: 4
	示例 2:
	
	输入: root = [5,3,6,2,4,null,null,1], k = 3
	       5
	      / \
	     3   6
	    / \
	   2   4
	  /
	 1
	输出: 4
	 
	
	限制：
	
	1 ≤ k ≤ 二叉搜索树元素个数
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_54 {

}

//  Definition for a binary tree node.
class TreeNode_Offer_54 {
	int val;
	TreeNode_Offer_54 left;
	TreeNode_Offer_54 right;

	TreeNode_Offer_54(int x) {
		val = x;
	}
}

class Solution_Offer_54 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月17日 上午1:16:10 
	 * @param: @param root
	 * @param: @param k
	 * @param: @return
	 * @return: int
	 * @Description: 1-中序遍历，左根右为递增可求第k小，右根左为递减可求第k大；
	 *
	 */
	// count记录排序序号
	int rst = 0, count = 0;

	public int kthLargest_1(TreeNode_Offer_54 root, int k) {
		dfs(root, k);
		return rst;
	}

	private void dfs(TreeNode_Offer_54 root, int k) {
		if (root == null) {
			return;
		} else {
			dfs(root.right, k);
			// count为k时即找到了第k大的数
			if (++count == k) {
				rst = root.val;
				return;
			}
			dfs(root.left, k);
		}
	}
}