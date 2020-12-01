package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月19日 上午12:40:26
 * @Description: 34. 在排序数组中查找元素的第一个和最后一个位置
 * <p>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 思路：1-二分查找，在找到最终值的时候还需要处理边界；
 * 2-左右边界分两次二分查找，增加判断参数（来自LeetCode官方解法）
 */
public class LeetCode_0034 {

}

class Solution_0034 {

    /**
     * @author: ZhouJie
     * @date: 2020年5月22日 下午9:01:09
     * @param: @param nums
     * @param: @param target
     * @param: @return
     * @return: int[]
     * @Description: 1-两次二分查找确定target的左右边界；
     */
    public int[] searchRange(int[] nums, int target) {
        // 特例判断
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        // 找左边界
        int left = binarySearch(nums, 0, target, false);
        if (left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        // 因已有左边界，故找右边界时左边界可作为左指针初始位置
        return new int[]{left, binarySearch(nums, left, target, true) - 1};
    }

    private int binarySearch(int[] nums, int l, int target, boolean f) {
        int r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            // 找左边界时不要等于，把区间往左侧压缩，找右边界时要等于，把区间往右侧压缩
            if (nums[mid] < target || (nums[mid] == target && f)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
