package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月10日 下午11:42:53 
 * @Description: 111. 二叉树的最小深度
 * 
	给定一个二叉树，找出其最小深度。
	
	最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
	
	说明: 叶子节点是指没有子节点的节点。
	
	示例:
	
	给定二叉树 [3,9,20,null,null,15,7],
	
	    3
	   / \
	  9  20
	    /  \
	   15   7
	返回它的最小深度  2.
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-递归递归~~
 */
public class LeetCode_0111 {

}

// Definition for a binary tree node.
class TreeNode_0111 {
	int val;
	TreeNode_0111 left;
	TreeNode_0111 right;

	TreeNode_0111(int x) {
		val = x;
	}
}

class Solution_0111 {
	/**
	 * @author ZhouJie
	 * @date 2020年1月11日 上午12:07:06 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月11日 上午12:07:06]  
	 * @UpdateRemark: root不作为叶子节点，当root的子节点有一个为null时，最小深度为2并不是1需要注意！
	 *
	 */
	public int minDepth(TreeNode_0111 root) {
		// 两层双目运算，内层判断节点为null时返回0，不为null判断左右节点；左右节点至少有一个为null时递归计算非null节点的最小深度，否则递归计算左右节点的最小深度
		return root == null ? 0
				: ((root.left == null || root.right == null)
						? (minDepth(root.left == null ? root.right : root.left) + 1)
						: Math.min(minDepth(root.left), minDepth(root.right)) + 1);
	}
}
