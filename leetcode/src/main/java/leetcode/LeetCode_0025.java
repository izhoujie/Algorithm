package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月13日 下午10:25:53 
 * @Description: 25. K 个一组翻转链表
 *
	给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
	
	k 是一个正整数，它的值小于或等于链表的长度。
	
	如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
	
	示例 :
	
	给定这个链表：1->2->3->4->5
	
	当 k = 2 时，应当返回: 2->1->4->3->5
	
	当 k = 3 时，应当返回: 3->2->1->4->5
	
	说明 :
	
	你的算法只能使用常数的额外空间。
	你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-迭代；
		2-递归；（来自leetcode评论区解法）
 */
public class LeetCode_0025 {

}

// Definition for singly-linked list.
class ListNode_0025 {
	int val;
	ListNode_0025 next;

	ListNode_0025(int x) {
		val = x;
	}
}

class Solution_0025 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午3:15:23 
	 * @param: @param head
	 * @param: @param k
	 * @param: @return
	 * @return: ListNode_0025
	 * @Description: 1-迭代；
	 *
	 */
	public ListNode_0025 reverseKGroup(ListNode_0025 head, int k) {
		ListNode_0025 p1, p2, p3, newHead;
		p1 = p2 = p3 = newHead = null;
		int n = 1;
		ListNode_0025 dummy = new ListNode_0025(0);
		dummy.next = head;
		while (head != null) {
			dummy.next = head;
			while (head != null && n < k) {
				head = head.next;
				n++;
			}
			if (n < k || head == null) {
				break;
			}
			p2 = head;
			p3 = head.next;
			head.next = null;
			// 翻转一段链表
			reverse(null, dummy.next);
			if (newHead == null) {
				newHead = p2;
			} else {
				p1.next = p2;
			}
			p1 = dummy.next;
			p1.next = p3;
			head = p3;
			n = 1;
		}
		return newHead == null ? dummy.next : newHead;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午3:20:04 
	 * @param: @param tail
	 * @param: @param head
	 * @param: @return
	 * @return: ListNode_0025
	 * @Description: 翻转链表
	 *
	 */
	private ListNode_0025 reverse(ListNode_0025 tail, ListNode_0025 head) {
		if (head == null) {
			return tail;
		}
		ListNode_0025 next = head.next;
		head.next = tail;
		return reverse(head, next);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午3:26:30 
	 * @param: @param head
	 * @param: @param k
	 * @param: @return
	 * @return: ListNode_0025
	 * @Description: 2-递归；
	 *
	 */
	public ListNode_0025 reverseKGroup_2(ListNode_0025 head, int k) {
		ListNode_0025 now = head;
		int count = 0;
		while (now != null && count < k) {
			now = now.next;
			count++;
		}
		if (count == k) {
			now = reverseKGroup_2(now, k);
			// head是满足连续k个的最后一段首节点，now是最后一段尾节点的下一个节点
			while (count-- > 0) {
				ListNode_0025 temp = head.next;
				head.next = now;
				now = head;
				head = temp;
			}
			// 返回当前段处理完后的头结点
			return now;
		}
		return head;
	}
}
