package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月24日 下午9:07:56 
 * @Description: 337. 打家劫舍 III
 * 
	 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
	
	计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
	
	示例 1:
	
	输入: [3,2,3,null,3,null,1]
	
	     3
	    / \
	   2   3  	
	    \   \ 
	     3   1
	
	输出: 7 
	解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
	示例 2:
	
	输入: [3,4,5,1,3,null,1]
	
	     3
	    / \
	   4   5
	  / \   \ 
	 1   3   1
	
	输出: 9
	解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/house-robber-iii
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LeetCode_0337 {

}

// Definition for a binary tree node.
class TreeNode_0337 {
	int val;
	TreeNode_0337 left;
	TreeNode_0337 right;

	TreeNode_0337(int x) {
		val = x;
	}
}

class Solution_0337 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月31日 下午1:48:54 
	 * @param: @param root
	 * @param: @return
	 * @return: int
	 * @Description: 1-对于一个节点来说，当前能偷到的最大值有两个可能：
	 * 				- 偷当前节点以及其左右节点的子节点中的各自最大值之和（4个孙子节点各自的最大值之和）；
	 * 				- 不偷当前节点，偷左右子节点各自的最大值之和；
	 * 				- 子节点及孙子节点是一个可递归解决的问题；
	 *
	 */
	public int rob_1(TreeNode_0337 root) {
		TreeNode_0337 left = new TreeNode_0337(0);
		TreeNode_0337 right = new TreeNode_0337(0);
		return robMax_1(root, left, right);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月31日 下午1:56:46 
	 * @param: @param root
	 * @param: @param left
	 * @param: @param right
	 * @param: @return
	 * @return: int
	 * @Description: 1-dp+递归（节点记录值）；
	 *
	 */
	private int robMax_1(TreeNode_0337 root, TreeNode_0337 left, TreeNode_0337 right) {
		if (root == null) {
			return 0;
		} else {
			// 四个孙子节点
			TreeNode_0337 ll, lr, rl, rr;
			ll = new TreeNode_0337(0);
			lr = new TreeNode_0337(0);
			rl = new TreeNode_0337(0);
			rr = new TreeNode_0337(0);
			// 左右子节点的最大值
			left.val = robMax_1(root.left, ll, lr);
			right.val = robMax_1(root.right, rl, rr);
			return Math.max(root.val + ll.val + lr.val + rl.val + rr.val, left.val + right.val);
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月31日 下午1:57:30 
	 * @param: @param root
	 * @param: @return
	 * @return: int
	 * @Description: 2-与1的区别仅在于使用数组保存中间值；
	 *
	 */
	public int rob_2(TreeNode_0337 root) {
		int[] rob = robMax_2(root);
		return Math.max(rob[0], rob[1]);
	}

	private int[] robMax_2(TreeNode_0337 root) {
		if (root == null) {
			// 空节点返回空数组给上层计算用
			return new int[2];
		}
		// 左右子节点最终rob数组
		int[] left = robMax_2(root.left);
		int[] right = robMax_2(root.right);
		int[] robbing = new int[2];
		// 最终做一次选择，不抢当前根节点（即左右子节点可抢可不抢），那么就从左右子节点中各选择抢与不抢的最大值即可；
		robbing[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		// 若抢当前跟节点，则只能选择左右子节点不抢时的值并加上当前根节点的值
		robbing[1] = left[0] + right[0] + root.val;
		return robbing;
	}
}
