package leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午4:00:43 
 * @Description: 155. 最小栈
 *
	设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
	
	push(x) —— 将元素 x 推入栈中。
	pop() —— 删除栈顶的元素。
	top() —— 获取栈顶元素。
	getMin() —— 检索栈中的最小元素。
	 
	
	示例:
	
	输入：
	["MinStack","push","push","push","getMin","pop","top","getMin"]
	[[],[-2],[0],[-3],[],[],[],[]]
	
	输出：
	[null,null,null,null,-3,null,0,-2]
	
	解释：
	MinStack minStack = new MinStack();
	minStack.push(-2);
	minStack.push(0);
	minStack.push(-3);
	minStack.getMin();   --> 返回 -3.
	minStack.pop();
	minStack.top();      --> 返回 0.
	minStack.getMin();   --> 返回 -2.
	 
	
	提示：
	
	pop、top 和 getMin 操作总是在 非空栈 上调用。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/min-stack
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0155 {

}

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午3:23:07 
 * @Description: 1-两个栈，一个作辅助栈存单调递减值；
 *
 */
class MinStack_0155_1 {
	private Stack<Integer> data;
	private Stack<Integer> min;

	/** initialize your data structure here. */
	public MinStack_0155_1() {
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

	public int getMin() {
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
class MinStack_0155_2 {
	private Node head;

	/** initialize your data structure here. */
	public MinStack_0155_2() {

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

	public int getMin() {
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
class MinStack_0155_3 {
	private int[] data;
	private int[] min;
	private int head;
	private int cap = 1024;

	/** initialize your data structure here. */
	public MinStack_0155_3() {
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

	public int getMin() {
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