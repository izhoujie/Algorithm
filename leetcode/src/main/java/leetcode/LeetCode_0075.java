package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月3日 下午2:18:42 
 * @Description: 75. 颜色分类
 *
	给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
	
	此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
	
	注意:
	不能使用代码库中的排序函数来解决这道题。
	
	示例:
	
	输入: [2,0,2,1,1,0]
	输出: [0,0,1,1,2,2]
	进阶：
	
	一个直观的解决方案是使用计数排序的两趟扫描算法。
	首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
	你能想出一个仅使用常数空间的一趟扫描算法吗？
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/sort-colors
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-前后双指针向中遍历，另一个临时指针进行中间是否有序校验扫描；
 */
public class LeetCode_0075 {

}

class Solution_0075 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月3日 下午7:15:40 
	 * @param: @param nums
	 * @return: void
	 * @Description: 1-
	 *
	 */
	public void sortColors(int[] nums) {
		if (nums == null) {
			return;
		}
		int i = 0, j = nums.length - 1, k;
		while (i < j) {
			// i，j加速校验
			while (i < j && nums[i] == 0) {
				i++;
			}
			while (i < j && nums[j] == 2) {
				j--;
			}
			// 若[i]为2，则与[j]对换，此时[j]非0即1，进入下次循环
			if (nums[i] == 2) {
				nums[i] = nums[j];
				nums[j] = 2;

				// 若[i]为1，则k=i+1在(i,j)之间扫一次，找到一个不等于1的值，若找到则[k]与[i]对换，反之则说明(i,j)之间均为1，排序完成跳出返回
			} else if (nums[i] == 1) {
				k = i;
				while (++k < j + 1) {
					if (nums[k] != 1) {
						break;
					}
				}
				if (k == j + 1) {
					break;
				} else {
					nums[i] = nums[k];
					nums[k] = 1;
				}
			}
		}
	}
}
