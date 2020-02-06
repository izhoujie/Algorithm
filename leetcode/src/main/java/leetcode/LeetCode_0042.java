package leetcode;

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
	public int trap(int[] height) {
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
}
