package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年3月14日 下午3:57:58 
 * @Description: 面试题34. 二叉树中和为某一值的路径
 *
	输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
	
	示例:
	给定如下二叉树，以及目标和 sum = 22，
	
	              5
	             / \
	            4   8
	           /   / \
	          11  13  4
	         /  \    / \
	        7    2  5   1
	返回:
	
	[
	   [5,4,11,2],
	   [5,8,4,5]
	]
	 
	
	提示：
	
	节点总数 <= 10000
	注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_34 {

}

//  Definition for a binary tree node.
class TreeNode_Offer_34 {
	int val;
	TreeNode_Offer_34 left;
	TreeNode_Offer_34 right;

	TreeNode_Offer_34(int x) {
		val = x;
	}
}

class Solution_Offer_34 {
	public List<List<Integer>> list = new ArrayList<List<Integer>>();

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月14日 下午4:53:24 
	 * @param: @param root
	 * @param: @param sum
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 1-递归判断，关键在最终叶子节点的处理；
	 *
	 */
	public List<List<Integer>> pathSum_1(TreeNode_Offer_34 root, int sum) {
		List<Integer> l = new ArrayList<Integer>();
		if (root.left == null && root.right == null)
			allPath(root, 0, l, sum);
		return list;
	}

	private void allPath(TreeNode_Offer_34 root, int add, List<Integer> l, int sum) {
		if (root == null) {
			if (add == sum && l.size() > 0) {
				list.add(l);
			}
			return;
		} else {
			add += root.val;
			l.add(root.val);
			if (root.left == null && root.right == null) {
				allPath(null, add, new ArrayList<Integer>(l), sum);
			} else {
				if (root.left != null) {
					allPath(root.left, add, new ArrayList<Integer>(l), sum);
				}
				if (root.right != null) {
					allPath(root.right, add, new ArrayList<Integer>(l), sum);
				}
			}
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月14日 下午7:19:29 
	 * @param: @param root
	 * @param: @param sum
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 2-dfs，深度优先...  最后的remove一个节点还不是很明白；
	 *
	 */
	public List<List<Integer>> pathSum_2(TreeNode_Offer_34 root, int sum) {
		dfs(root, sum, new ArrayList<Integer>());
		return list;
	}

	private void dfs(TreeNode_Offer_34 root, int sum, ArrayList<Integer> l) {
		if (root == null) {
			return;
		} else {
			sum -= root.val;
			l.add(root.val);
			if (sum == 0 && root.left == null && root.right == null) {
				list.add(new ArrayList<Integer>(l));
			} else {
				dfs(root.left, sum, l);
				dfs(root.right, sum, l);
			}
			l.remove(l.size() - 1);
		}
	}
}