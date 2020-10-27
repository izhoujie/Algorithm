package leetcode;

/**
 * @author zhoujie
 * @date 2020/10/23 17:45
 * @description: 234. 回文链表
 * <p>
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0243 {
}


//  Definition for singly-linked list.
class ListNode_0243 {
    int val;
    ListNode_0243 next;

    ListNode_0243(int x) {
        val = x;
    }
}

class Solution_0243 {
    /**
     * @return boolean
     * @author zhoujie
     * @date 2020/10/23 17:47
     * @param: head
     * @description: 先通过快慢指针定位中间位置，同时反转前半段，要注意节点总数为奇偶数时的处理，然后前后半段同步顺序校验是否为回文
     */
    public boolean isPalindrome(ListNode_0243 head) {
        // 特例判断
        if (head == null || head.next == null) {
            return true;
        }
        ListNode_0243 fast = head;
        ListNode_0243 slow = head;
        ListNode_0243 half1 = null;
        ListNode_0243 tmp;
        // 快慢指针定位中间节点位置，同时反转前半段节点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            tmp = slow.next;
            slow.next = half1;
            half1 = slow;
            slow = tmp;
        }
        // 节点总数为奇偶数时的判断：
        // 奇数：此时fast不为null，slow为中间节点可不校验，slow.next及其之后的为后半段；
        // 偶数：此时fast为null，slow及其之后的为后半段
        ListNode_0243 half2 = fast == null ? slow : slow.next;
        // 两半段已对齐，开始校验
        while (half1 != null && half2 != null) {
            if (half1.val != half2.val) {
                return false;
            }
            half1 = half1.next;
            half2 = half2.next;
        }
        return true;
    }
}