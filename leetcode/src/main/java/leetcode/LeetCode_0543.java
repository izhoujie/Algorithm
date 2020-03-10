package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月10日 下午2:30:44 
 * @Description: 543. 二叉树的直径
 *
	给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
	
	示例 :
	给定二叉树
	
	          1
	         / \
	        2   3
	       / \     
	      4   5    
	返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
	
	注意：两结点之间的路径长度是以它们之间边的数目表示。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-求节点两边各自的最大树高之和-2，不断递归保留最大值；
 */
public class LeetCode_0543 {

}

// Definition for a binary tree node.
class TreeNode_0543 {
	int val;
	TreeNode_0543 left;
	TreeNode_0543 right;

	TreeNode_0543(int x) {
		val = x;
	}
}

class Solution_0543 {
	public int maxD = 0;

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月10日 下午3:54:15 
	 * @param: @param root
	 * @param: @return
	 * @return: int
	 * @Description: 1-转化为求树的最大高度问题，然后递归解决；
	 *
	 */
	public int diameterOfBinaryTree(TreeNode_0543 root) {
		maxR(root);
		return maxD;
	}

	private int maxR(TreeNode_0543 root) {
		if (root == null) {
			return 0;
		} else {
			int left = maxR(root.left) + 1;
			int right = maxR(root.right) + 1;
			maxD = Math.max(left + right - 1, maxD);
			return Math.max(left, right);
		}
	}

}