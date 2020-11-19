package leetcode;

import java.util.Arrays;

/**
 * @author ZhouJie
 * @date 2020年1月17日 下午3:25:03
 * @Description: 31. 下一个排列
 * <p>
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须原地修改，只允许使用额外常数空间。
 * <p>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 思路：1-首先理解字典序就是数组顺组组成的数的大小，题干有点绕，得先看懂意思；
 */
public class LeetCode_0031 {
}

class Solution_0031 {
    /**
     * @author zhoujie
     * @date 2020年1月17日 下午6:02:26
     * @param: nums
     * @description: 自己实现的，略绕
     */
    public void nextPermutation_1(int[] nums) {
        int len;
        if (nums == null || (len = nums.length) == 0) {
            return;
        }
        int k, newIndex;
        k = newIndex = -1;
        // 倒序尝试找位置
        for (int i = len - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                k = i - 1;
                newIndex = i;
                break;
            }
        }
        // 如未找到，则直接升序后返回
        if (k == -1) {
            Arrays.sort(nums);
            return;
        }
        // 找到位置后，再找比k大的最小值
        for (int i = k + 1; i < len; i++) {
            if (nums[i] > nums[k] && nums[i] < nums[newIndex]) {
                newIndex = i;
            }
        }
        // 找到newIndex后与k位置互换，再对k+1到len的部分进行升序排序后返回
        int temp = nums[k];
        nums[k] = nums[newIndex];
        nums[newIndex] = temp;
        Arrays.sort(nums, k + 1, len);
    }

    /**
     * @author zhoujie
     * @date 2020/11/19 15:07
     * @param: nums
     * @description: 先找到下一个最大序列的变更位置，替换为次大值后部分反转即可
     */
    public void nextPermutation_2(int[] nums) {
        int i = nums.length - 2;
        // 后向前寻找相邻的第一个前小于后的位置
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 若有这样的位置，则再从后向该位置扫描寻找第一个大于该位置的值并与之互换
        // 此时下一个最大序列关键位已确定，且该位置后面的序列降序的，只需要对这部分反转即可
        if (i >= 0) {
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        // i可能为-1，即当前序列已是最大序列
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}