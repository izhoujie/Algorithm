package leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午2:47:43 
 * @Description: 面试题30. 包含min函数的栈
 *
	定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
	
	示例:
	
	MinStack minStack = new MinStack();
	minStack.push(-2);
	minStack.push(0);
	minStack.push(-3);
	minStack.min();   --> 返回 -3.
	minStack.pop();
	minStack.top();      --> 返回 0.
	minStack.min();   --> 返回 -2.
	 
	
	提示：
	
	各函数的调用总次数不超过 20000 次
	 
	
	注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_30 {

}

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午3:23:07 
 * @Description: 1-两个栈，一个作辅助栈存单调递减值；
 *
 */
class MinStack_Offer_30_1 {
	private Stack<Integer> data;
	private Stack<Integer> min;

	/** initialize your data structure here. */
	public MinStack_Offer_30_1() {
		this.data = new Stack<Integer>();
		this.min = new Stack<Integer>();
	}

	public void push(int x) {
		data.push(x);
		if (min.isEmpty() || x <= min.peek()) {
			min.push(x);
		}
	}

	public void pop() {
		if (!data.isEmpty()) {
			if (min.peek().intValue() == data.pop().intValue()) {
				min.pop();
			}
		}
	}

	public int top() {
		if (data.isEmpty()) {
			return -1;
		}
		return data.peek();
	}

	public int min() {
		if (min.isEmpty()) {
			return -1;
		}
		return min.peek();
	}
}

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午3:23:47 
 * @Description: 2-构造链表
 *
 */
class MinStack_Offer_30_2 {
	private Node head;

	/** initialize your data structure here. */
	public MinStack_Offer_30_2() {

	}

	public void push(int x) {
		if (head == null) {
			head = new Node(x, x, null);
		} else {
			head = new Node(x, Math.min(x, head.min), head);
		}
	}

	public void pop() {
		head = head.next;
	}

	public int top() {
		return head.val;
	}

	public int min() {
		return head.min;
	}

	private static class Node {
		int val;
		int min;
		Node next;

		public Node(int val, int min, Node next) {
			this.val = val;
			this.min = min;
			this.next = next;
		}
	}
}

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午3:45:08 
 * @Description: 3-使用数组保存数据 
 *
 */
class MinStack_Offer_30_3 {
	private int[] data;
	private int[] min;
	private int head;
	private int cap = 1024;

	/** initialize your data structure here. */
	public MinStack_Offer_30_3() {
		this.head = 0;
		this.data = new int[cap];
		this.min = new int[cap];
		min[0] = Integer.MAX_VALUE;
	}

	public void push(int x) {
		if (head == cap - 2) {
			// 扩容至原来的1.5倍
			cap = cap + (cap >> 1);
			data = Arrays.copyOf(data, cap);
			min = Arrays.copyOf(min, cap);
		}
		data[++head] = x;
		min[head] = (head == 1) ? x : Math.min(min[head - 1], x);

	}

	public void pop() {
		if (head > 0) {
			head--;
		}
	}

	public int top() {
		return head > 0 ? data[head] : -1;
	}

	public int min() {
		return head > 0 ? min[head] : -1;
	}
}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */