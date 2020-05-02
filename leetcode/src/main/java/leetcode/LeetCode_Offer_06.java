package leetcode;

import java.util.Stack;

/**
 * @author ZhouJie
 * @date 2020年4月28日 下午6:29:56 
 * @Description: 面试题06. 从尾到头打印链表
 *
	输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
	
	示例 1：
	
	输入：head = [1,3,2]
	输出：[2,3,1]
	 
	
	限制：
	
	0 <= 链表长度 <= 10000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_06 {

}

//  Definition for singly-linked list.
class ListNode_Offer_06 {
	int val;
	ListNode_Offer_06 next;

	ListNode_Offer_06(int x) {
		val = x;
	}
}

class Solution_Offer_06 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月28日 下午6:34:16 
	 * @param: @param head
	 * @param: @return
	 * @return: int[]
	 * @Description: 1-两次遍历，一次统计总数用以建数组，一次用来填数组；
	 *
	 */
	public int[] reversePrint_1(ListNode_Offer_06 head) {
		// 保存头节点
		ListNode_Offer_06 node = head;
		int count = 0;
		// 统计总数
		while (node != null) {
			count++;
			node = node.next;
		}
		int[] rst = new int[count];
		while (head != null) {
			// 倒序放入
			rst[--count] = head.val;
			head = head.next;
		}
		return rst;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月28日 下午6:35:38 
	 * @param: @param head
	 * @param: @return
	 * @return: int[]
	 * @Description: 2-两次遍历，一次用栈保存，一次从栈放入数组
	 *
	 */
	public int[] reversePrint_2(ListNode_Offer_06 head) {
		int count = 0;
		Stack<Integer> stack = new Stack<Integer>();
		while (head != null) {
			stack.push(head.val);
			head = head.next;
			count++;
		}
		int[] rst = new int[count];
		int i = 0;
		while (!stack.isEmpty()) {
			rst[i++] = stack.pop();
		}
		return rst;
	}
}