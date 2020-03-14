package leetcode;

import java.util.HashMap;

/**
 * @author ZhouJie
 * @date 2020年3月14日 下午5:16:25 
 * @Description: 面试题35. 复杂链表的复制
 *
	请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
	
	 
	
	示例 1：
	
	
	
	输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
	输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
	示例 2：
	
	
	
	输入：head = [[1,1],[2,1]]
	输出：[[1,1],[2,1]]
	示例 3：
	
	
	
	输入：head = [[3,null],[3,0],[3,null]]
	输出：[[3,null],[3,0],[3,null]]
	示例 4：
	
	输入：head = []
	输出：[]
	解释：给定的链表为空（空指针），因此返回 null。
	 
	
	提示：
	
	-10000 <= Node.val <= 10000
	Node.random 为空（null）或指向链表中的节点。
	节点数目不超过 1000 。
	 
	
	注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-pointer/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_35 {

}

//Definition for a Node.
class Node_Offer_35 {
	int val;
	Node_Offer_35 next;
	Node_Offer_35 random;

	public Node_Offer_35(int val) {
		this.val = val;
		this.next = null;
		this.random = null;
	}
}

class Solution_Offer_35 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月14日 下午5:42:59 
	 * @param: @param head
	 * @param: @return
	 * @return: Node_Offer_35
	 * @Description: 1-map哈希表；
	 *
	 */
	public Node_Offer_35 copyRandomList_1(Node_Offer_35 head) {
		if (head == null) {
			return head;
		}
		HashMap<Node_Offer_35, Node_Offer_35> map = new HashMap<Node_Offer_35, Node_Offer_35>();
		for (Node_Offer_35 node = head; node != null; node = node.next) {
			map.put(node, new Node_Offer_35(node.val));
		}
		for (Node_Offer_35 node = head; node != null; node = node.next) {
			map.get(node).next = map.get(node.next);
			map.get(node).random = map.get(node.random);
		}
		return map.get(head);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月14日 下午5:47:17 
	 * @param: @param head
	 * @param: @return
	 * @return: Node_Offer_35
	 * @Description: 2-原地复制，在节点后复制一个节点，然后再剥离链表；
	 *
	 */
	public Node_Offer_35 copyRandomList_2(Node_Offer_35 head) {
		if (head == null) {
			return head;
		}
		// 在原节点后复制节点 1->2->3 => 1->1`->2->2`->3->3`
		for (Node_Offer_35 node = head, copy = null; node != null; node = node.next.next) {
			copy = new Node_Offer_35(node.val);
			copy.next = node.next;
			node.next = copy;
		}
		// 把复制后的random节点链接上
		for (Node_Offer_35 node = head; node != null; node = node.next.next) {
			if (node.random != null) {
				node.next.random = node.random.next;
			}
		}
		// 原节点head的下一个几点就是新链表的头结点
		Node_Offer_35 newHead = head.next;
		// 剥离节点，一个一个间隔剥离节点
		for (Node_Offer_35 node = head, temp = null; node != null && node.next != null;) {
			temp = node.next;
			node.next = temp.next;
			node = temp;
		}
		return newHead;
	}
}