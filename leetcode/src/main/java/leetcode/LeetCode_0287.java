package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月31日 下午2:58:54 
 * @Description: 287. 寻找重复数
 *
	给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
	
	示例 1:
	
	输入: [1,3,4,2,2]
	输出: 2
	示例 2:
	
	输入: [3,1,3,4,2]
	输出: 3
	说明：
	
	不能更改原数组（假设数组是只读的）。
	只能使用额外的 O(1) 的空间。
	时间复杂度小于 O(n2) 。
	数组中只有一个重复的数字，但它可能不止重复出现一次。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/find-the-duplicate-number
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0287 {

}

class Solution_0287 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月31日 下午2:59:24 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 1-将数组看作有环链表，使用快慢指针寻找环入口；
	 *
	 */
	public int findDuplicate_1(int[] nums) {
		int fast = 0, slow = 0;
		while (true) {
			fast = nums[nums[fast]];
			slow = nums[slow];
			// 此时找到了环内的一个同步节点，然后重置其中一个指针为头节点，开始寻找环入口
			if (fast == slow) {
				fast = 0;
				while (nums[fast] != nums[slow]) {
					fast = nums[fast];
					slow = nums[slow];
				}
				return nums[fast];
			}
		}
	}
}
