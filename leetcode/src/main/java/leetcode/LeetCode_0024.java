package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月10日 下午9:22:23 
 * @Description: 24. 两两交换链表中的节点
 *
	给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
	
	你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
	
	 
	
	示例:
	
	给定 1->2->3->4, 你应该返回 2->1->4->3.
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-迭代；设置哨兵节点，注意处理前继节点和后继节点依次遍历即可；
		2-递归；（来自leetcode评论区解法）；
 */
public class LeetCode_0024 {

}

//Definition for singly-linked list.
class ListNode_0024 {
	int val;
	ListNode_0024 next;

	ListNode_0024(int x) {
		val = x;
	}
}

class Solution_0024 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午3:13:54 
	 * @param: @param head
	 * @param: @return
	 * @return: ListNode_0024
	 * @Description: 1-迭代；
	 *
	 */
	public ListNode_0024 swapPairs_1(ListNode_0024 head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode_0024 newHead = head.next;
		ListNode_0024 l1, l2, l3, l4;
		l4 = null;
		// l1和l2为交换节点，l3为后继节点，l4为前继节点
		while (head != null && head.next != null) {
			l1 = l2 = l3 = null;
			l1 = head;
			l2 = head.next;
			l3 = l2.next;
			l2.next = l1;
			l1.next = l3;
			head = l3;
			if (l4 == null) {
				l4 = l1;
			} else {
				l4.next = l2;
				l4 = l1;
			}
		}
		return newHead;
	}

	/**
	 * @author ZhouJie
	 * @date 2020年1月10日 下午10:10:49 
	 * @Description: TODO(方法简述) 
	 * @return ListNode_0024 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月10日 下午10:10:49]  
	 * @UpdateRemark:1-递归
	 *
	 */
	public ListNode_0024 swapPairs_2(ListNode_0024 head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode_0024 next = head.next;
		// 下一层的问题丢给下一层，只关心该层要解决的问题，即next.next和head.next问题，head.next的解就递归子问题的解
		head.next = swapPairs_2(next.next);
		next.next = head;
		return next;
	}
}
