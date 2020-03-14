package leetcode;

import java.util.Stack;

/**
 * @author ZhouJie
 * @date 2020年3月14日 下午3:20:10 
 * @Description: 面试题09. 用两个栈实现队列
 *
	用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
	
	 
	
	示例 1：
	
	输入：
	["CQueue","appendTail","deleteHead","deleteHead"]
	[[],[3],[],[]]
	输出：[null,null,3,-1]
	示例 2：
	
	输入：
	["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
	[[],[],[5],[2],[],[]]
	输出：[null,-1,null,null,5,2]
	提示：
	
	1 <= values <= 10000
	最多会对 appendTail、deleteHead 进行 10000 次调用
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_09 {

}

/**
 * @author ZhouJie
 * @date 2020年3月14日 下午3:20:30 
 * @Description: 1-用两个栈，栈1实现队列的offer，栈2实现队列的pull；
 * 				每次放入数据执行栈1的push，每次取数据执行栈2的pop，
 * 				若栈2为空则先将栈1的数据全部倒灌入栈2，栈2再执行pop，
 * 				若都为空返回-1；
 *
 */
class CQueue {
	public Stack<Integer> offer;
	public Stack<Integer> pull;

	public CQueue() {
		this.offer = new Stack<Integer>();
		this.pull = new Stack<Integer>();
	}

	public void appendTail(int value) {
		offer.push(value);
	}

	public int deleteHead() {
		if (offer.isEmpty() && pull.isEmpty()) {
			return -1;
		} else if (!pull.isEmpty()) {
			return pull.pop();
		} else {
			while (!offer.isEmpty()) {
				pull.push(offer.pop());
			}
			return pull.pop();
		}
	}
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */