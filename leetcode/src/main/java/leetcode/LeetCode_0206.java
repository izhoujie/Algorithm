package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月12日 下午7:54:48 
 * @Description: 206. 反转链表
 *
	反转一个单链表。
	
	示例:
	
	输入: 1->2->3->4->5->NULL
	输出: 5->4->3->2->1->NULL
	进阶:
	你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/reverse-linked-list
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-双指针遍历；
 */
public class LeetCode_0206 {

}

// Definition for singly-linked list.
class ListNode_0206 {
	int val;
	ListNode_0206 next;

	ListNode_0206(int x) {
		val = x;
	}
}

class Solution_0206 {
	/**
	 * @author ZhouJie
	 * @date 2020年1月12日 下午8:37:02 
	 * @Description: TODO(方法简述) 
	 * @return ListNode_0206 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月12日 下午8:37:02]  
	 * @UpdateRemark:迭代 
	 *
	 */
	public ListNode_0206 reverseList_1(ListNode_0206 head) {
		ListNode_0206 pre = null;
		ListNode_0206 next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	/**
	 * @author ZhouJie
	 * @date 2020年1月12日 下午8:36:57 
	 * @Description: TODO(方法简述) 
	 * @return ListNode_0206 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月12日 下午8:36:57]  
	 * @UpdateRemark:递归--尾递归---leetcode解法，太绕了..
	 *
	 */
	public ListNode_0206 reverseList_2(ListNode_0206 head) {
		return reverse(null, head);
	}

	/**
	 * @author ZhouJie
	 * @date 2020年1月12日 下午8:36:55 
	 * @Description: TODO(方法简述) 
	 * @return ListNode_0206 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月12日 下午8:36:55]  
	 * @UpdateRemark:尾递归，太绕了，不容易看懂； 
	 *
	 */
	private ListNode_0206 reverse(ListNode_0206 tail, ListNode_0206 head) {
		if (head == null) {
			return tail;
		}
		ListNode_0206 next = head.next;
		head.next = tail;
		return reverse(head, next);
	}

	/**
	 * @author ZhouJie
	 * @date 2020年1月12日 下午8:45:43 
	 * @Description: TODO(方法简述) 
	 * @return ListNode_0206 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月12日 下午8:45:43]  
	 * @UpdateRemark:直接递归，官方解法  
	 *
	 */
	public ListNode_0206 reverseList_3(ListNode_0206 head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode_0206 newHead = reverseList_3(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}
}
