package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午4:44:48 
 * @Description: 面试题32 - I. 从上到下打印二叉树
 *
	从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
	
	例如:
	给定二叉树: [3,9,20,null,null,15,7],
	
	    3
	   / \
	  9  20
	    /  \
	   15   7
	返回：
	
	[3,9,20,15,7]
	 
	
	提示：
	
	节点总数 <= 1000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_32_1 {

}

// Definition for a binary tree node.
class TreeNode_Offer_32_1 {
	int val;
	TreeNode_Offer_32_1 left;
	TreeNode_Offer_32_1 right;

	TreeNode_Offer_32_1(int x) {
		val = x;
	}
}

class Solution_Offer_32_1 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月3日 下午4:45:42 
	 * @param: @param root
	 * @param: @return
	 * @return: int[]
	 * @Description: 1-树的层次遍历，使用list或者queue；
	 *
	 */
	public int[] levelOrder(TreeNode_Offer_32_1 root) {
		if (root == null) {
			return new int[0];
		}
		List<Integer> list = new ArrayList<Integer>();
		Deque<TreeNode_Offer_32_1> deque = new ArrayDeque<TreeNode_Offer_32_1>();
		deque.offer(root);
		while (!deque.isEmpty()) {
			TreeNode_Offer_32_1 node = deque.poll();
			list.add(node.val);
			if (node.left != null) {
				deque.offer(node.left);
			}
			if (node.right != null) {
				deque.offer(node.right);
			}
		}
		int[] rst = new int[list.size()];
		int i = 0;
		for (int val : list) {
			rst[i++] = val;
		}
		return rst;
	}
}