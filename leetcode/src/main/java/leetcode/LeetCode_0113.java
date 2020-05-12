package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午5:26:02 
 * @Description: 113. 路径总和 II
 *
	给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
	
	说明: 叶子节点是指没有子节点的节点。
	
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
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/path-sum-ii
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0113 {

}

//  Definition for a binary tree node.
class TreeNode_0113 {
	int val;
	TreeNode_0113 left;
	TreeNode_0113 right;

	TreeNode_0113(int x) {
		val = x;
	}
}

class Solution_0113 {

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月3日 下午5:27:56 
	 * @param: @param root
	 * @param: @param sum
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 1-从根递归/DFS，注意最后叶子节点的处理；
	 *
	 */
	private List<List<Integer>> all = new ArrayList<List<Integer>>();

	public List<List<Integer>> pathSum(TreeNode_0113 root, int sum) {
		if (root == null) {
			return all;
		}
		searchAllPath(root, 0, new ArrayList<Integer>(), sum);
		return all;
	}

	private void searchAllPath(TreeNode_0113 root, int add, List<Integer> list, int sum) {
		if (root == null) {
			if (add == sum && list.size() > 0) {
				all.add(list);
			}
		} else {
			add += root.val;
			list.add(root.val);
			TreeNode_0113 left = root.left;
			TreeNode_0113 right = root.right;
			// 最后的子节点需要判断，否则会出现重复值，-若叶子节点无左右子节点，直接左右递归就会产生两个相同的路径list；
			// 递归时list需要新建并加入当前路径已有的值，因为路径是唯一的，list不能继续复用；
			if (left == null && right == null) {
				searchAllPath(null, add, new ArrayList<>(list), sum);
			} else {
				if (left != null) {
					searchAllPath(left, add, new ArrayList<>(list), sum);
				}
				if (right != null) {
					searchAllPath(right, add, new ArrayList<>(list), sum);
				}
			}
		}
	}
}