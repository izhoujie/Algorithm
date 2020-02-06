package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月12日 下午6:56:38 
 * @Description: 83. 删除排序链表中的重复元素
 *
 *
	给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
	
	示例 1:
	
	输入: 1->1->2
	输出: 1->2
	示例 2:
	
	输入: 1->1->2->3->3
	输出: 1->2->3
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-递归处理；
		2-迭代处理；
 */
public class LeetCode_0083 {

}

// Definition for singly-linked list.
class ListNode_0083 {
	int val;
	ListNode_0083 next;

	ListNode_0083(int x) {
		val = x;
	}
}

class Solution_0083 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月5日 下午9:05:55 
	 * @param: @param head
	 * @param: @return
	 * @return: ListNode_0083
	 * @Description: 1-
	 *
	 */
	public ListNode_0083 deleteDuplicates_1(ListNode_0083 head) {
		if (head == null) {
			return head;
		}
		// 若当前head节点值等于head.next节点的值则继续比较head节点值与head.next.next节点的值
		while (head.next != null && head.val == head.next.val) {
			head.next = head.next.next;
		}
		// 直至找到一个不同值的节点再继续递归处理head.next之后的节点
		deleteDuplicates_1(head.next);
		return head;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月5日 下午9:08:14 
	 * @param: @param head
	 * @param: @return
	 * @return: ListNode_0083
	 * @Description: 2-
	 *
	 */
	public ListNode_0083 deleteDuplicates_2(ListNode_0083 head) {
		ListNode_0083 now = head;
		while (now != null && now.next != null) {
			if (now.val == now.next.val) {
				now.next = now.next.next;
			} else {
				now = now.next;
			}
		}
		return head;
	}
}
