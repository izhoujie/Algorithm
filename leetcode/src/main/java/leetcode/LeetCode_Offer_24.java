package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月14日 下午3:39:46 
 * @Description: 面试题24. 反转链表
 *
	定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
	
	 
	
	示例:
	
	输入: 1->2->3->4->5->NULL
	输出: 5->4->3->2->1->NULL
	 
	
	限制：
	
	0 <= 节点个数 <= 5000
	
	 
	
	注意：本题与主站 206 题相同：https://leetcode-cn.com/problems/reverse-linked-list/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_24 {

}

//  Definition for singly-linked list.
class ListNode_Offer_24 {
	int val;
	ListNode_Offer_24 next;

	ListNode_Offer_24(int x) {
		val = x;
	}
}

class Solution_Offer_24 {

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月14日 下午3:49:27 
	 * @param: @param head
	 * @param: @return
	 * @return: ListNode_Offer_24
	 * @Description: 1-直接递归；
	 *
	 */
	public ListNode_Offer_24 reverseList_1(ListNode_Offer_24 head) {
		if (head == null || head.next == null) {
			return head;
		} else {
			ListNode_Offer_24 newHead = reverseList_1(head.next);
			head.next.next = head;
			head.next = null;
			return newHead;
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月14日 下午3:49:43 
	 * @param: @param head
	 * @param: @return
	 * @return: ListNode_Offer_24
	 * @Description: 2-正常迭代；
	 *
	 */
	public ListNode_Offer_24 reverseList_2(ListNode_Offer_24 head) {
		ListNode_Offer_24 pre = null;
		while (head != null) {
			ListNode_Offer_24 next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月14日 下午3:50:21 
	 * @param: @param head
	 * @param: @return
	 * @return: ListNode_Offer_24
	 * @Description: 2-另一种递归；
	 *
	 */
	public ListNode_Offer_24 reverseList_3(ListNode_Offer_24 head) {
		ListNode_Offer_24 pre = null;
		return reverse(head, pre);
	}

	private ListNode_Offer_24 reverse(ListNode_Offer_24 head, ListNode_Offer_24 pre) {
		if (head == null) {
			return pre;
		}
		ListNode_Offer_24 next = head.next;
		head.next = pre;
		pre = head;
		return reverse(next, pre);
	}
}