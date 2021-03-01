package leetcode;

/**
 * @author zhoujie
 * @date 2021/3/1 上午11:30
 * @description: 303. 区域和检索 - 数组不可变
 * <p>
 * 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 * <p>
 * 实现 NumArray 类：
 * <p>
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 输出：
 * [null, 1, -1, -3]
 * <p>
 * 解释：
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 * 0 <= i <= j < nums.length
 * 最多调用 104 次 sumRange 方法
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-immutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0303 {
}

/**
 * @author zhoujie
 * @date 2021/3/1 上午11:40
 * @return
 * @description: 未优化，直接计算
 */
class NumArray_1 {
    private int[] array;

    public NumArray_1(int[] nums) {
        this.array = nums;

    }

    public int sumRange(int i, int j) {
        int sum = 0;
        while (i < j + 1) {
            sum += array[i++];
        }
        return sum;
    }
}

/**
 * @author zhoujie
 * @date 2021/3/1 上午11:40
 * @return
 * @description: 优化后，提前计算前n项和
 */
class NumArray_2 {
    private int[] array;

    public NumArray_2(int[] nums) {
        this.array = new int[nums.length + 1];
        array[0] = 0;
        for (int i = 1; i < array.length; i++) {
            array[i] += array[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return array[j + 1] - array[i];
    }
}
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */