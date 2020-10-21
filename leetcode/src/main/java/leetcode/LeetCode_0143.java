package leetcode;

/**
 * @author zhoujie
 * @date 2020/10/20 09:23
 * @description: 143. 重排链表
 * <p>
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * <p>
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * <p>
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0143 {
}

class ListNode_0143 {
    int val;
    ListNode_0143 next;

    ListNode_0143() {
    }

    ListNode_0143(int val) {
        this.val = val;
    }

    ListNode_0143(int val, ListNode_0143 next) {
        this.val = val;
        this.next = next;
    }
}

class Solution_0143 {
    /**
     * @author zhoujie
     * @date 2020/10/20 09:28
     * @param: head
     * @description: 链表实际上的操作是：后半段链表反转后与前半段链表错位相连
     */
    public void reorderList(ListNode_0143 head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode_0143 fast = head;
        ListNode_0143 slow = head;
        // 定位中间节点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 从中间切断链表
        ListNode_0143 half2 = slow.next;
        slow.next = null;
        // 把后半段链表反转
        half2 = reverse(half2);
        ListNode_0143 half1 = head;
        ListNode_0143 tmp;
        // 前半段与后半段错位拼接
        while (half2 != null) {
            tmp = half1.next;
            half1.next = half2;
            half1 = half2;
            half2 = tmp;
        }
    }

    /**
     * @return leetcode.ListNode_0143
     * @author zhoujie
     * @date 2020/10/20 10:19
     * @param: head
     * @description: 迭代法反转一个链表
     */
    private ListNode_0143 reverse(ListNode_0143 head) {
        ListNode_0143 tail = null;
        ListNode_0143 tmp;
        while (head != null) {
            tmp = head.next;
            head.next = tail;
            tail = head;
            head = tmp;
        }
        return tail;
    }
}