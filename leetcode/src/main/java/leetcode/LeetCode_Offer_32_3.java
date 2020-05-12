package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午5:06:39 
 * @Description: 面试题32 - III. 从上到下打印二叉树 III
 *
	请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
	
	例如:
	给定二叉树: [3,9,20,null,null,15,7],
	
	    3
	   / \
	  9  20
	    /  \
	   15   7
	返回其层次遍历结果：
	
	[
	  [3],
	  [20,9],
	  [15,7]
	]
	 
	
	提示：
	
	节点总数 <= 1000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_32_3 {

}

//  Definition for a binary tree node.
class TreeNode_Offer_32_3 {
	int val;
	TreeNode_Offer_32_3 left;
	TreeNode_Offer_32_3 right;

	TreeNode_Offer_32_3(int x) {
		val = x;
	}
}

class Solution_Offer_32_3 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月12日 下午1:53:51 
	 * @param: @param root
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 1-层次遍历，使用queue；
	 *
	 */
	public List<List<Integer>> levelOrder_1(TreeNode_Offer_32_3 root) {
		List<List<Integer>> all = new ArrayList<List<Integer>>();
		if (root == null) {
			return all;
		}
		Deque<TreeNode_Offer_32_3> deque = new ArrayDeque<TreeNode_Offer_32_3>();
		deque.offer(root);
		// 控制层的打印顺序
		boolean f = false;
		while (!deque.isEmpty()) {
			int size = deque.size();
			List<Integer> list = new ArrayList<Integer>();
			while (size-- > 0) {
				TreeNode_Offer_32_3 node = deque.poll();
				list.add(node.val);
				if (node.left != null) {
					deque.offer(node.left);
				}
				if (node.right != null) {
					deque.offer(node.right);
				}
			}
			// 翻转
			if (f) {
				Collections.reverse(list);
			}
			all.add(list);
			f = !f;
		}
		return all;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月12日 下午1:56:20 
	 * @param: @param root
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 2-层次遍历，不使用queue；
	 *
	 */
	private List<List<Integer>> all = new LinkedList<List<Integer>>();

	public List<List<Integer>> levelOrder_2(TreeNode_Offer_32_3 root) {
		treeBFS(root, 0);
		return all;
	}

	private void treeBFS(TreeNode_Offer_32_3 root, int level) {
		if (root == null) {
			return;
		}
		if (all.size() == level) {
			all.add(new LinkedList<Integer>());
		}
		LinkedList<Integer> list = (LinkedList<Integer>) all.get(level);
		// 控制记录顺序
		if ((level & 1) == 0) {
			list.addLast(root.val);
		} else {
			list.addFirst(root.val);
		}
		treeBFS(root.left, level + 1);
		treeBFS(root.right, level + 1);
	}
}