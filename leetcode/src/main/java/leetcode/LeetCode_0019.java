package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月6日 上午1:32:08 
 * @Description: 19. 删除链表的倒数第N个节点
 *
	给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
	
	示例：
	
	给定一个链表: 1->2->3->4->5, 和 n = 2.
	
	当删除了倒数第二个节点后，链表变为 1->2->3->5.
	说明：
	
	给定的 n 保证是有效的。
	
	进阶：
	
	你能尝试使用一趟扫描实现吗？
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-两个指针，一个预先走n个节点，第二个再走，前节点到尾部时，第二个删除下一个即可；
 *
 */
public class LeetCode_0019 {

}

// Definition for singly-linked list.
class ListNode_0019 {
	int val;
	ListNode_0019 next;

	ListNode_0019(int x) {
		val = x;
	}
}

class Solution_0019 {
	public ListNode_0019 removeNthFromEnd(ListNode_0019 head, int n) {
		if (head == null) {
			return null;
		}
		ListNode_0019 first, second;
		first = second = head;
		// 快指针先走n个节点
		for (int i = 0; i < n; i++) {
			first = first.next;
		}
		// 删除首节点的情况
		if (first == null) {
			return head.next;
		}
		// 快慢指针一起走
		while (first.next != null) {
			first = first.next;
			second = second.next;
		}
		// 删除第n个节点
		second.next = second.next.next;
		return head;
	}
}