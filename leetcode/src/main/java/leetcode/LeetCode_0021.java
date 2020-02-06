package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月6日 下午11:40:27 
 * @Description: 21. 合并两个有序链表
 *
	将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
	
	示例：
	
	输入：1->2->4, 1->3->4
	输出：1->1->2->3->4->4
	
		来源：力扣（LeetCode）
		链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-两个指针依次遍历比较拼接即可；
		2-leetcode优解递归；

 */
public class LeetCode_0021 {

}

// Definition for singly-linked list.
class ListNode_0021 {
	int val;
	ListNode_0021 next;

	ListNode_0021(int x) {
		val = x;
	}
}

class Solution_0021 {
	/**
	 * @author ZhouJie
	 * @date 2020年1月7日 上午12:32:39 
	 * @Description: TODO(方法简述) 
	 * @return ListNode_0021 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月7日 上午12:32:39]  
	 * @UpdateRemark:[本次修改内容]  
	 *
	 */
	public ListNode_0021 mergeTwoLists_1(ListNode_0021 l1, ListNode_0021 l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		ListNode_0021 dummy = new ListNode_0021(0);
		ListNode_0021 node = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				dummy.next = l1;
				dummy = l1;
				l1 = l1.next;
			} else {
				dummy.next = l2;
				dummy = l2;
				l2 = l2.next;
			}
		}
		dummy.next = l1 == null ? l2 : l1;
		return node.next;
	}

	/**
	 * @author ZhouJie
	 * @date 2020年1月7日 上午12:35:50 
	 * @Description: TODO(方法简述) 
	 * @return ListNode_0021 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月7日 上午12:35:50]  
	 * @UpdateRemark:[本次修改内容]  
	 *
	 */
	public ListNode_0021 mergeTwoLists_2(ListNode_0021 l1, ListNode_0021 l2) {
		if (l1 == null) {
			return l2;
		} else if (l2 == null) {
			return l1;
		} else if (l1.val < l2.val) {
			l1.next = mergeTwoLists_2(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists_2(l1, l2.next);
			return l2;
		}
	}
}