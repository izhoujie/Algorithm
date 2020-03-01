package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ZhouJie
 * @date 2020年3月1日 下午2:53:07 
 * @Description: 225. 用队列实现栈
 *
	使用队列实现栈的下列操作：
	
	push(x) -- 元素 x 入栈
	pop() -- 移除栈顶元素
	top() -- 获取栈顶元素
	empty() -- 返回栈是否为空
	注意:
	
	你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
	你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
	你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/implement-stack-using-queues
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-使用Deque，但是只使用左进右出或者右进左出的一对队列操作实现；（我使用了右进左出即addLast()和pollFirst()的一对队列操作）
 */
public class LeetCode_0225 {

}

class MyStack_0025 {
	private Deque<Integer> queue;

	/** Initialize your data structure here. */
	public MyStack_0025() {
		this.queue = new ArrayDeque<Integer>();
	}

	/** Push element x onto stack. */
	public void push(int x) {
		queue.addLast(x);
		int i = queue.size();
		while (i-- > 1) {
			queue.add(queue.pollFirst());
		}
	}

	/** Removes the element on top of the stack and returns that element. */
	public int pop() {
		return queue.pollFirst();
	}

	/** Get the top element. */
	public int top() {
		return queue.peekFirst();
	}

	/** Returns whether the stack is empty. */
	public boolean empty() {
		return queue.isEmpty();
	}
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */