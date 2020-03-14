package leetcode;

/**
 * @author ZhouJie
 * @date 2019年12月3日 下午7:01:00 
 * @Description: 2. 两数相加
 *
	给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
	
	如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
	
	您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
	
	示例：
	
	输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
	输出：7 -> 0 -> 8
	原因：342 + 465 = 807
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/add-two-numbers
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：设置一个进制标识变量，依次做加法即可；
 */
public class LeetCode_0002 {

}

/**
 * Definition for singly-linked list.
 */
class ListNode_0002 {
	int val;
	ListNode_0002 next;

	ListNode_0002(int x) {
		val = x;
	}
}

class Solution_0002 {
	/**
	 * @author ZhouJie
	 * @date 2019年12月3日 下午7:53:15 
	 * @Description: TODO(方法简述) 
	 * @return ListNode 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 上午10:54:53]  
	 * @UpdateRemark:思路1-设置一个进制标识变量，依次做加法即可； 
	 *
	 */
	public ListNode_0002 addTwoNumbers(ListNode_0002 l1, ListNode_0002 l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		// root 头结点，now为当前节点，last为now的前一节点，方便处理进位和最后一位的情况；carry为进位值
		ListNode_0002 root = new ListNode_0002(0);
		ListNode_0002 now;
		now = root;
		int carry = 0;
		while (l1 != null || l2 != null || carry != 0) {
			int val1 = (l1 == null ? 0 : l1.val);
			int val2 = (l2 == null ? 0 : l2.val);
			int val = (val1 + val2 + carry);
			now.next = new ListNode_0002(val % 10);
			carry = val / 10;
			now = now.next;
			l1 = (l1 == null ? null : l1.next);
			l2 = (l2 == null ? null : l2.next);
		}
		return root.next;
	}
}
