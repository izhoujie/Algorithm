package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午4:58:36 
 * @Description: 面试题32 - II. 从上到下打印二叉树 II 
 *
	从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
	
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
	  [9,20],
	  [15,7]
	]
	 
	
	提示：
	
	节点总数 <= 1000
	注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_32_2 {

}

//Definition for a binary tree node.
class TreeNode_Offer_32_2 {
	int val;
	TreeNode_Offer_32_2 left;
	TreeNode_Offer_32_2 right;

	TreeNode_Offer_32_2(int x) {
		val = x;
	}
}

class Solution_Offer_32_2 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月3日 下午5:03:55 
	 * @param: @param root
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 1-树的层次遍历，每层需要处理一下，记录到list；
	 *
	 */
	public List<List<Integer>> levelOrder_1(TreeNode_Offer_32_2 root) {
		List<List<Integer>> all = new ArrayList<List<Integer>>();
		if (root == null) {
			return all;
		}
		Deque<TreeNode_Offer_32_2> deque = new ArrayDeque<TreeNode_Offer_32_2>();
		deque.offer(root);
		while (!deque.isEmpty()) {
			int size = deque.size();
			List<Integer> list = new ArrayList<Integer>();
			while (size-- > 0) {
				TreeNode_Offer_32_2 node = deque.poll();
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
	 * @date: 2020年5月12日 下午1:38:54 
	 * @param: @param root
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 2-BFS，按照树的深度递归；
	 *
	 */
	private List<List<Integer>> all = new ArrayList<List<Integer>>();

	public List<List<Integer>> levelOrder_2(TreeNode_Offer_32_2 root) {
		treeBFS(root, 0);
		return all;
	}

	private void treeBFS(TreeNode_Offer_32_2 root, int n) {
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