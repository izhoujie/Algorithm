package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ZhouJie
 * @date 2020年3月7日 下午2:11:49 
 * @Description: 面试题59 - II. 队列的最大值
 *
	请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的时间复杂度都是O(1)。
	
	若队列为空，pop_front 和 max_value 需要返回 -1
	
	示例 1：
	
	输入: 
	["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
	[[],[1],[2],[],[],[]]
	输出: [null,null,null,2,1,2]
	示例 2：
	
	输入: 
	["MaxQueue","pop_front","max_value"]
	[[],[],[]]
	输出: [null,-1,-1]
	 
	
	限制：
	
	1 <= push_back,pop_front,max_value的总操作数 <= 10000
	1 <= value <= 10^5
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-利用Deque特性构造队列以最大值的保存；
		2-直接自己创建Node节点，构造链表；
 */
public class LeetCode_Offer_59_2 {

}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
/**
 * @author ZhouJie
 * @date 2020年3月7日 下午4:38:01 
 * @Description: 1-利用deque实现；
 *
 */
class MaxQueue_1 {
	private Deque<Integer> deque;
	private Deque<Integer> maxHander;

	public MaxQueue_1() {
		this.deque = new ArrayDeque<Integer>();
		this.maxHander = new ArrayDeque<Integer>();
	}

	public int max_value() {
		return this.maxHander.isEmpty() ? -1 : maxHander.peek();
	}

	public void push_back(int value) {
		this.deque.offer(value);
		while (!maxHander.isEmpty() && value > maxHander.peekLast()) {
			maxHander.pollLast();
		}
		maxHander.offer(value);
	}

	public int pop_front() {
		if (this.deque.isEmpty()) {
			return -1;
		} else {
			int e = deque.pop();
			if (maxHander.peek() == e) {
				maxHander.pop();
			}
			return e;
		}
	}

	/**
	 * @author ZhouJie
	 * @date 2020年3月7日 下午4:38:19 
	 * @Description: 2-自己构造节点链表实现；（效率高）
	 *
	 */
	class MaxQueue_2 {
		class Node_59_2 {
			int val;
			Node_59_2 next = null;

			public Node_59_2(int value) {
				this.val = value;
			}
		}

		private Node_59_2 tail;
		private Node_59_2 root;
		private Node_59_2 max;

		// 初始链表，root为哑节点
		public MaxQueue_2() {
			this.root = new Node_59_2(-1);
			this.tail = this.root;
			this.max = this.root;
		}

		public int max_value() {
			return root == tail ? -1 : max.val;
		}

		public void push_back(int value) {
			tail.next = new Node_59_2(value);
			tail = tail.next;
			// root为哑节点时此时进来第一个值，直接给max，否则判断当前新节点是否可晋升为max节点
			if (max == root || max.val <= value) {
				max = tail;
			}
		}

		public int pop_front() {
			if (root == tail) {
				return -1;
			}
			// 此时的root为即将要pop出队列的值，若与max相等，则说明需要寻找次大值了
			root = root.next;
			if (max == root) {
				Node_59_2 head = root.next;
				max = head;
				// 在root-tail之间寻找最大值；
				while (head != null) {
					max = max.val > head.val ? max : head;
					head = head.next;
				}
				// 若tail是队列的最后一个值，则需要纠正max为root
				max = max == null ? root : max;
			}
			return root.val;
		}
	}
}
