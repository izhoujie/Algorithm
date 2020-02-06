package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月13日 下午11:37:57 
 * @Description: 27. 移除元素
 *
	给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
	
	不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
	
	元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
	
	示例 1:
	
	给定 nums = [3,2,2,3], val = 3,
	
	函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
	
	你不需要考虑数组中超出新长度后面的元素。
	示例 2:
	
	给定 nums = [0,1,2,2,3,0,4,2], val = 2,
	
	函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
	
	注意这五个元素可为任意顺序。
	
	你不需要考虑数组中超出新长度后面的元素。
	说明:
	
	为什么返回数值是整数，但输出的答案是数组呢?
	
	请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
	
	你可以想象内部操作如下:
	
	// nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
	int len = removeElement(nums, val);
	
	// 在函数里修改输入数组对于调用者是可见的。
	// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
	for (int i = 0; i < len; i++) {
	    print(nums[i]);
	}
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/remove-element
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-双指针一次遍历；同侧遍历；
		2-双指针一次遍历；两侧向中间遍历；（来自leetcode官方解法）
 */
public class LeetCode_0027 {

}

class Solution_0027 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午3:47:58 
	 * @param: @param nums
	 * @param: @param val
	 * @param: @return
	 * @return: int
	 * @Description: 1-
	 *
	 */
	public int removeElement_1(int[] nums, int val) {
		if (nums == null) {
			return 0;
		}
		int k = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val) {
				nums[k++] = nums[i];
			}
		}
		return k;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午3:48:10 
	 * @param: @param nums
	 * @param: @param val
	 * @param: @return
	 * @return: int
	 * @Description: 2-
	 *
	 */
	public int removeElement_2(int[] nums, int val) {
		if (nums == null) {
			return 0;
		}
		int k = 0, n = nums.length;
		while (k < n) {
			if (nums[k] == val) {
				nums[k] = nums[n - 1];
				n--;
			} else {
				k++;
			}
		}
		return n;
	}
}
