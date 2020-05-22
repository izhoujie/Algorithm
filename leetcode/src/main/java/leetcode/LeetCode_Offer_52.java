package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月19日 下午1:53:39 
 * @Description: 面试题52. 两个链表的第一个公共节点
 *
	输入两个链表，找出它们的第一个公共节点。
	
	如下面的两个链表：
	
	在节点 c1 开始相交。
	
	示例 1：

	输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
	输出：Reference of the node with value = 8
	输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
	 
	示例 2：
	
	输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
	输出：Reference of the node with value = 2
	输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。

	示例 3：

	输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
	输出：null
	输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
	解释：这两个链表不相交，因此返回 null。

	注意：
	
	如果两个链表没有交点，返回 null.
	在返回结果后，两个链表仍须保持原有的结构。
	可假定整个链表结构中没有循环。
	程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
	本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_52 {

}

//Definition for singly-linked list.
class ListNode_Offer_52 {
	int val;
	ListNode_Offer_52 next;

	ListNode_Offer_52(int x) {
		val = x;
		next = null;
	}
}

class Solution_Offer_52 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月19日 下午1:32:36 
	 * @param: @param headA
	 * @param: @param headB
	 * @param: @return
	 * @return: ListNode_Offer_52
	 * @Description: 1-链表各需要遍历两次，一次统计长度，然后让长的先移动比短的多出的节点数，最后一起移动找相同节点；
	 *
	 */
	public ListNode_Offer_52 getIntersectionNode_1(ListNode_Offer_52 headA, ListNode_Offer_52 headB) {
		if (headA == null || headB == null) {
			return null;
		}
		int a = 0, b = 0;
		ListNode_Offer_52 A = headA, B = headB;
		// 统计链表长度
		while (headA != null) {
			a++;
			headA = headA.next;
		}
		while (headB != null) {
			b++;
			headB = headB.next;
		}
		// 链表头对齐
		if (a > b) {
			a -= b;
			while (a-- > 0) {
				A = A.next;
			}
		} else if (a < b) {
			b -= a;
			while (b-- > 0) {
				B = B.next;
			}
		}
		// 寻找相交点，有则返回相交点，无则返回null
		while (A != B) {
			A = A.next;
			B = B.next;
		}
		return A;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月19日 下午1:44:52 
	 * @param: @param headA
	 * @param: @param headB
	 * @param: @return
	 * @return: ListNode_Offer_52
	 * @Description: 2-对方法1的优化
	 *
	 */
	public ListNode_Offer_52 getIntersectionNode_2(ListNode_Offer_52 headA, ListNode_Offer_52 headB) {
		if (headA == null || headB == null) {
			return null;
		}
		ListNode_Offer_52 A = headA, B = headB;
		// 这里实际上相当于在A链表后面拼接了一个B链表，在B链表后面拼接了一个A链表，这样就保证了两个链表都有了相同的长度
		// 且最多遍历两次即可得到结果
		while (A != B) {
			A = A == null ? headB : A.next;
			B = B == null ? headA : B.next;
		}
		return A;
	}
}