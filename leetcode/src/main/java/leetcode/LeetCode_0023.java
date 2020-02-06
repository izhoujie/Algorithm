package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ZhouJie
 * @date 2020年1月10日 下午8:26:09 
 * @Description: 23. 合并K个排序链表
 *
	合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
	
	示例:
	
	输入:
	[
	  1->4->5,
	  1->3->4,
	  2->6
	]
	输出: 1->1->2->3->4->4->5->6
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-TopK的问题，使用最小堆/最大堆/优先队列解决；
		2-分治思想，0-n不断折半合并，直至合为一个；
 */
public class LeetCode_0023 {

}

// Definition for singly-linked list.
class ListNode_0023 {
	int val;
	ListNode_0023 next;

	ListNode_0023(int x) {
		val = x;
	}
}

class Solution_0023 {
	/**
	 * @author ZhouJie
	 * @date 2020年1月10日 下午8:48:38 
	 * @Description: TODO(方法简述) 
	 * @return ListNode_0023 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月10日 下午8:48:38]  
	 * @UpdateRemark:1-使用优先队列
	 *
	 */
	public ListNode_0023 mergeKLists_1(ListNode_0023[] lists) {
		if (lists == null) {
			return null;
		}
		// 优先队列，非基础类型需要重写比较器
		PriorityQueue<ListNode_0023> queue = new PriorityQueue<ListNode_0023>(new Comparator<ListNode_0023>() {
			public int compare(ListNode_0023 o1, ListNode_0023 o2) {
				return o1.val - o2.val;
			}
		});
		for (ListNode_0023 node : lists) {
			if (node != null) {
				queue.add(node);
			}
		}
		ListNode_0023 head, now;
		head = now = null;
		while (!queue.isEmpty()) {
			ListNode_0023 node = queue.poll();
			if (head == null) {
				head = now = node;
			} else {
				now.next = node;
				now = node;
			}
			if (node.next != null) {
				queue.add(node.next);
			}
		}
		return head;
	}

	/**
	 * @author ZhouJie
	 * @date 2020年1月10日 下午9:03:07 
	 * @Description: TODO(方法简述) 
	 * @return ListNode_0023 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月10日 下午9:03:07]  
	 * @UpdateRemark:2-分治思想，不断折半合并，直至为一个；
	 *
	 */
	public ListNode_0023 mergeKLists_2(ListNode_0023[] lists) {
		int len;
		if (lists == null || (len = lists.length) == 0) {
			return null;
		}
		while (len > 1) {
			for (int i = 0; i < len / 2; i++) {
				// 对半合并，后一半合并到前一半
				lists[i] = mergeHelper(lists[i], lists[len - 1 - i]);
			}
			len = (len + 1) / 2;
		}
		return lists[0];
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午2:45:50 
	 * @param: @param l1
	 * @param: @param l2
	 * @param: @return
	 * @return: ListNode_0023
	 * @Description: 合并两个有序链表辅助方法
	 *
	 */
	private ListNode_0023 mergeHelper(ListNode_0023 l1, ListNode_0023 l2) {
		if (l1 == null) {
			return l2;
		} else if (l2 == null) {
			return l1;
		} else if (l1.val < l2.val) {
			l1.next = mergeHelper(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeHelper(l1, l2.next);
			return l2;
		}
	}
}
