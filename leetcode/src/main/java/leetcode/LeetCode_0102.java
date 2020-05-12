package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年5月12日 下午1:33:38 
 * @Description: 102. 二叉树的层序遍历
 *
	给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。

	示例：
	二叉树：[3,9,20,null,null,15,7],
	
	    3
	   / \
	  9  20
	    /  \
	   15   7
	返回其层次遍历结果：
	
	[
	  [3],
	  [9,20],
	  [15,7]
	]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0102 {

}

// Definition for a binary tree node.
class TreeNode_0102 {
	int val;
	TreeNode_0102 left;
	TreeNode_0102 right;

	TreeNode_0102(int x) {
		val = x;
	}
}

class Solution_0102 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月12日 下午1:34:32 
	 * @param: @param root
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 1-层次遍历
	 *
	 */
	public List<List<Integer>> levelOrder_1(TreeNode_0102 root) {
		List<List<Integer>> all = new ArrayList<List<Integer>>();
		if (root == null) {
			return all;
		}
		Deque<TreeNode_0102> deque = new ArrayDeque<TreeNode_0102>();
		deque.offer(root);
		while (!deque.isEmpty()) {
			int size = deque.size();
			List<Integer> list = new ArrayList<Integer>();
			while (size-- > 0) {
				TreeNode_0102 node = deque.poll();
				list.add(node.val);
				if (node.left != null) {
					deque.offer(node.left);
				}
				if (node.right != null) {
					deque.offer(node.right);
				}
			}
			all.add(list);
		}
		return all;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月12日 下午1:43:52 
	 * @param: @param root
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 2-按照树的深度递归；
	 *
	 */
	private List<List<Integer>> all = new ArrayList<List<Integer>>();

	public List<List<Integer>> levelOrder_2(TreeNode_0102 root) {
		treeBFS(root, 0);
		return all;
	}

	private void treeBFS(TreeNode_0102 root, int n) {
		if (root == null) {
			return;
		}
		if (all.size() == n) {
			all.add(new ArrayList<Integer>());
		}
		all.get(n).add(root.val);
		treeBFS(root.left, n + 1);
		treeBFS(root.right, n + 1);
	}
}