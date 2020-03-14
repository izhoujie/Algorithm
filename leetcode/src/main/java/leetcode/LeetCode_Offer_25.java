package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月14日 下午3:28:41 
 * @Description: 面试题25. 合并两个排序的链表
 *
	输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
	
	示例1：
	
	输入：1->2->4, 1->3->4
	输出：1->1->2->3->4->4
	限制：
	
	0 <= 链表长度 <= 1000
	
	注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_25 {

}

class Solution_Offer_25 {
//  Definition for singly-linked list.
	class ListNode_Offer_25 {
		int val;
		ListNode_Offer_25 next;

		ListNode_Offer_25(int x) {
			val = x;
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月14日 下午3:34:36 
	 * @param: @param l1
	 * @param: @param l2
	 * @param: @return
	 * @return: ListNode_Offer_25
	 * @Description: 1-递归；
	 *
	 */
	public ListNode_Offer_25 mergeTwoLists(ListNode_Offer_25 l1, ListNode_Offer_25 l2) {
		if (l1 == null) {
			return l2;
		} else if (l2 == null) {
			return l1;
		} else if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}
}