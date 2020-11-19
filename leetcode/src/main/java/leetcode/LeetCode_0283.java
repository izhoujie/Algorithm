package leetcode;

/**
 * @author zhoujie
 * @date 2020/11/19 12:14
 * @description: 283. 移动零
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0283 {
}

class Solution_0283 {
    /**
     * @return void
     * @author zhoujie
     * @date 2020/11/19 12:15
     * @param: nums
     * @description: 双指针均从做往右遍历，左指针记录非零数实际位置，右指针顺序遍历
     */
    public void moveZeroes(int[] nums) {
        if (!(nums == null || nums.length == 0)) {
            int k = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    if (i != k) {
                        nums[k] = nums[i];
                        nums[i] = 0;
                    }
                    k++;
                }
            }
        }
    }
}