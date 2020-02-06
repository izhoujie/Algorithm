package leetcode;

/**
 * @author ZhouJie
 * @date 2019年12月12日 下午9:34:10 
 * @Description: 11. 盛最多水的容器
 *
	给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
	
	说明：你不能倾斜容器，且 n 的值至少为 2。
	
	
	
	图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
	
	 
	
	示例:
	
	输入: [1,8,6,2,5,4,8,3,7]
	输出: 49
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/container-with-most-water
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-两边两个指针ij，min(i,j)*(|j-i|)，然后i++/j--;
 */
public class LeetCode_0011 {

}

class Solution_0011 {
	public int maxArea(int[] height) {
		int len = 0;
		if (height == null || (len = height.length) < 2) {
			return 0;
		}
		int i = 0, j = len - 1;
		int max = 0;
		int min = 0;
		while (i < j) {
			// 当前左右挡板中的小值
			min = Math.min(height[i], height[j]);
			// 记录最大储水
			max = Math.max(max, min * (j - i));
			// 向后找到第一个比min大的位置
			while (i < j && height[i] <= min) {
				i++;
			}
			// 向前找到第一个比min大的位置
			while (i < j && height[j] <= min) {
				j--;
			}
		}
		return max;
	}
}
