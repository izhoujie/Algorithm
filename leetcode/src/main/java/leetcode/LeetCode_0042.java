package leetcode;

import java.util.Stack;

/**
 * @author ZhouJie
 * @date 2020年2月1日 下午10:44:25 
 * @Description: 42. 接雨水
 *
	给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
	
	
	
	上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
	
	示例:
	
	输入: [0,1,0,2,1,0,1,3,2,1,2,1]
	输出: 6
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/trapping-rain-water
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-想象左右两块挡板，小的挡板有资格向里推进搜集雨水，直至搜集不到时再检测谁小继续搜集；
		2-面积差数学求法，左扫一遍取最高值累加，右扫一遍取最高值累加，最后减去原数组的总值和最高值与数组长度构成的面积就是雨水的面积；
 */
public class LeetCode_0042 {

}

class Solution_0042 {
	/**
	 * @author ZhouJie
	 * @date 2020年2月1日 下午11:52:28 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月1日 下午11:52:28]  
	 * @UpdateRemark:1- 
	 *
	 */
	public int trap_1(int[] height) {
		if (height == null) {
			return 0;
		}
		int rst = 0, leftMax = 0, rightMax = 0;
		int i = 0, j = height.length - 1;
		while (i < j) {
			// 若左侧挡板低，则由左侧向内搜集雨水，否则从右侧向内搜集
			if (height[i] < height[j]) {
				if (height[i] < leftMax) {
					rst += leftMax - height[i];
				} else {
					leftMax = height[i];
				}
				i++;
			} else {
				if (height[j] < rightMax) {
					rst += rightMax - height[j];
				} else {
					rightMax = height[j];
				}
				j--;
			}
		}
		return rst;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月4日 上午2:03:37 
	 * @param: @param height
	 * @param: @return
	 * @return: int
	 * @Description: 2-面积差法，数学方法；
	 *
	 */
	public int trap_2(int[] height) {
		if (height == null) {
			return 0;
		}
		int rst = 0, leftMax = 0, rightMax = 0, len = height.length;
		int i = 0, j = len - 1;
		while (i < len) {
			// 从左向右不断求最大值，向右平铺面积值
			leftMax = Math.max(leftMax, height[i]);
			// 从右向左不断求最大值，向左平铺面积值
			rightMax = Math.max(rightMax, height[j - i]);
			// 顺带减去原数组的面积值
			rst += leftMax + rightMax - height[i++];
		}
		// 最后减去平铺后最高点与数组长度组成的矩形面积就是雨水面积
		return rst - len * leftMax;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月5日 下午1:45:00 
	 * @param: @param height
	 * @param: @return
	 * @return: int
	 * @Description: 3-单调栈-单调递减栈
	 *
	 */
	public int trap_3(int[] height) {
		if (height == null) {
			return 0;
		}
		Stack<Integer> stack = new Stack<Integer>();
		int allRain = 0;
		for (int i = 0; i < height.length; i++) {
			while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
				int top = stack.pop();
				// 小于栈顶元素就一直入栈
				while (!stack.isEmpty() && height[stack.peek()] == height[top]) {
					stack.pop();
				}
				if (!stack.isEmpty()) {
					// 栈非空时可能搜集到雨水
					// 搜集到的雨水为：左侧height[left]和右侧height[i]两个挡板的较低者减去中间的height[top]底部高度乘以
					// left和i之间的距离(i - left - 1)
					int left = stack.peek();
					allRain += (Math.min(height[left], height[i]) - height[top]) * (i - left - 1);
				}
			}
			stack.push(i);
		}
		return allRain;
	}
}
