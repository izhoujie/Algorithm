package leetcode;

import java.util.Stack;

/**
 * @author ZhouJie
 * @date 2020年4月4日 下午12:51:29 
 * @Description: 84. 柱状图中最大的矩形
 *
	给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
	
	求在该柱状图中，能够勾勒出来的矩形的最大面积。
	
	
	以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
	
	
	图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
	
	
	示例:
	
	输入: [2,1,5,6,2,3]
	输出: 10
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	1-暴力破解，一次遍历，对于每个i都尝试向两遍扩展只要高度不低于i即可，以左右边界为宽，i的值为高的矩形为当前最大，然后遍历中保留最大值；
 */
public class LeetCode_0084 {

}

class Solution_0084 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月4日 下午1:17:32 
	 * @param: @param heights
	 * @param: @return
	 * @return: int
	 * @Description: 1-一次遍历，遍历中左右扩展，实际算法复杂度为O(n²)
	 *
	 */
	public int largestRectangleArea_1(int[] heights) {
		int len = 0;
		if (heights == null || (len = heights.length) == 0) {
			return 0;
		}
		int maxArea = 0;
		for (int i = 0; i < len; i++) {
			if (heights[i] != 0) {
				int l = i, r = i, h = heights[i];
				while (l > 0 && heights[l - 1] >= h) {
					l--;
				}
				while (r < len - 1 && heights[r + 1] >= h) {
					r++;
				}
				maxArea = Math.max(maxArea, h * (r - l + 1));
			}
		}
		return maxArea;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月4日 下午1:43:01 
	 * @param: @param heights
	 * @param: @return
	 * @return: int
	 * @Description: 2-单调栈
	 *
	 */
	public int largestRectangleArea_2(int[] heights) {
		int len = 0;
		if (heights == null || (len = heights.length) == 0) {
			return 0;
		}
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(-1);
		int maxArea = 0;
		for (int i = 0; i < len; i++) {
			while (stack.peek() != -1 && heights[stack.peek()] > heights[i]) {
				maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
			}
			stack.push(i);
		}
		while (stack.peek() != -1) {
			maxArea = Math.max(maxArea, heights[stack.pop()] * (len - stack.peek() - 1));
		}
		return maxArea;
	}

}