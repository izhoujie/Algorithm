package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年3月1日 下午7:44:28 
 * @Description: 107. 二叉树的层次遍历 II
 *
	给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
	
	例如：
	给定二叉树 [3,9,20,null,null,15,7],
	
	    3
	   / \
	  9  20
	    /  \
	   15   7
	返回其自底向上的层次遍历为：
	
	[
	  [15,7],
	  [9,20],
	  [3]
	]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-每层都用队列处理一次；
 */
public class LeetCode_0107 {

}

//  Definition for a binary tree node.
class TreeNode_0107 {
	int val;
	TreeNode_0107 left;
	TreeNode_0107 right;

	TreeNode_0107(int x) {
		val = x;
	}
}

class Solution {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月1日 下午8:39:03 
	 * @param: @param root
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 1-使用队列；
	 *
	 */
	public List<List<Integer>> levelOrderBottom(TreeNode_0107 root) {
		Deque<TreeNode_0107> queue = new ArrayDeque<TreeNode_0107>();
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if (root == null) {
			return list;
		}
		queue.push(root);
		int k = 1, g = 0;
		while (k > 0) {
			List<Integer> l = new ArrayList<Integer>();
			g = 0;
			while (k-- > 0) {
				TreeNode_0107 pop = queue.pollFirst();
				l.add(pop.val);
				if (pop.left != null) {
					queue.addLast(pop.left);
					g++;
				}
				if (pop.right != null) {
					queue.addLast(pop.right);
					g++;
				}
			}
			list.add(0, l);
			k = g;
		}
		return list;
	}
}