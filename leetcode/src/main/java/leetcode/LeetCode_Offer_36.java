package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年3月14日 下午6:10:03 
 * @Description: 面试题36. 二叉搜索树与双向链表
 *
	输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
	
	为了让您更好地理解问题，以下面的二叉搜索树为例：
	
	我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
	
	下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
	
	特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
	
	注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
	
	注意：此题对比原题有改动。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_36 {

}

//Definition for a Node.
class Node_Offer_36 {
	public int val;
	public Node_Offer_36 left;
	public Node_Offer_36 right;

	public Node_Offer_36() {
	}

	public Node_Offer_36(int _val) {
		val = _val;
	}

	public Node_Offer_36(int _val, Node_Offer_36 _left, Node_Offer_36 _right) {
		val = _val;
		left = _left;
		right = _right;
	}
};

class Solution_Offer_36 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月14日 下午7:04:20 
	 * @param: @param root
	 * @param: @return
	 * @return: Node_Offer_36
	 * @Description: 1-先用list对搜索二叉树顺次保存，然后遍历list组装链表；
	 *
	 */
	public Node_Offer_36 treeToDoublyList_1(Node_Offer_36 root) {
		if (root == null) {
			return root;
		}
		List<Node_Offer_36> list = new ArrayList<Node_Offer_36>();
		afterTree(root, list);
		for (int i = 1; i < list.size(); i++) {
			Node_Offer_36 node1 = list.get(i - 1);
			Node_Offer_36 node2 = list.get(i);
			node1.right = node2;
			node2.left = node1;
		}
		Node_Offer_36 node1 = list.get(0);
		Node_Offer_36 node2 = list.get(list.size() - 1);
		node1.left = node2;
		node2.right = node1;
		return list.get(0);
	}

	private void afterTree(Node_Offer_36 root, List<Node_Offer_36> list) {
		if (root == null) {
			return;
		}
		afterTree(root.right, list);
		list.add(0, root);
		afterTree(root.left, list);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月14日 下午7:14:31 
	 * @param: @param root
	 * @param: @return
	 * @return: Node_Offer_36
	 * @Description: 2-直接在遍历链表时完成前继指针和后继指针的变换；（还没看太明白）
	 *
	 */
	Node_Offer_36 pre, head, tail;

	public Node_Offer_36 treeToDoublyList_2(Node_Offer_36 root) {
		if (root == null) {
			return root;
		}
		transform(root);
		head.left = tail;
		tail.right = head;
		return head;
	}

	private void transform(Node_Offer_36 root) {
		if (root == null) {
			return;
		}
		transform(root.left);
		root.left = pre;
		if (pre == null) {
			head = root;
		} else {
			pre.right = root;
		}
		pre = root;
		tail = root;
		transform(root.right);
	}
}