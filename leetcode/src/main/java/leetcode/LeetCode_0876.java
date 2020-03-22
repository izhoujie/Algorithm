package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月23日 上午12:11:23 
 * @Description: 876. 链表的中间结点
 *
	给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
	
	如果有两个中间结点，则返回第二个中间结点。
	
	示例 1：
	
	输入：[1,2,3,4,5]
	输出：此列表中的结点 3 (序列化形式：[3,4,5])
	返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
	注意，我们返回了一个 ListNode 类型的对象 ans，这样：
	ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
	示例 2：
	
	输入：[1,2,3,4,5,6]
	输出：此列表中的结点 4 (序列化形式：[4,5,6])
	由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
	 
	
	提示：
	
	给定链表的结点数介于 1 和 100 之间。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/middle-of-the-linked-list
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-两次遍历，一次统计节点数n，另一次找中间位置n/2；（简单直接代码略）
		2-两个指针，一个指针每次移动一个节点，另一个指针每次移动两个节点，后节点为null，前一个节点就是中间节点；
 */
public class LeetCode_0876 {

}

//  Definition for singly-linked list.
class ListNode_0876 {
	int val;
	ListNode_0876 next;

	ListNode_0876(int x) {
		val = x;
	}
}

class Solution_0876 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月23日 上午12:14:38 
	 * @param: @param head
	 * @param: @return
	 * @return: ListNode_0876
	 * @Description: 2-两个指针，一个每次移动一步，另一个每次移动两步
	 *
	 */
	public ListNode_0876 middleNode(ListNode_0876 head) {
		ListNode_0876 fast = head;
		ListNode_0876 slow = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
}