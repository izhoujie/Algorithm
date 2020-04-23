package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年4月22日 下午9:12:49 
 * @Description: 199. 二叉树的右视图
 *
	给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
	
	示例:
	
	输入: [1,2,3,null,5,null,4]
	输出: [1, 3, 4]
	解释:
	
	   1            <---
	 /   \
	2     3         <---
	 \     \
	  5     4       <---
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0199 {

}

//  Definition for a binary tree node.
class TreeNode_0199 {
	int val;
	TreeNode_0199 left;
	TreeNode_0199 right;

	TreeNode_0199(int x) {
		val = x;
	}
}

class Solution_0199 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月22日 下午10:11:22 
	 * @param: @param root
	 * @param: @return
	 * @return: List<Integer>
	 * @Description: 1-其实就是树的层次遍历，只是每层取了最右侧一个数
	 *
	 */
	public List<Integer> rightSideView(TreeNode_0199 root) {
		List<Integer> list = new ArrayList<Integer>();
		Deque<TreeNode_0199> deque = new ArrayDeque<>();
		if (root == null) {
			return list;
		}
		deque.offer(root);
		int val = root.val;
		while (!deque.isEmpty()) {
			int size = deque.size();
			while (size-- > 0) {
				TreeNode_0199 poll = deque.poll();
				if (poll.left != null) {
					deque.offer(poll.left);
				}
				if (poll.right != null) {
					deque.offer(poll.right);
				}
				val = poll.val;
			}
			list.add(val);
		}
		return list;
	}
}