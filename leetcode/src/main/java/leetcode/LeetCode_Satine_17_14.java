package leetcode;

import java.util.Arrays;

/**
 * @author zhoujie
 * @date 2021/9/3 下午3:54
 * @description: 面试题 17.14. 最小K个数
 * <p>
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * <p>
 * 示例：
 * <p>
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * 提示：
 * <p>
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-k-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Satine_17_14 {
    /**
     * @return int[]
     * @author zhoujie
     * @date 2021/9/3 下午3:55
     * @param: arr
     * @param: k
     * @description:
     */
    public int[] smallestK(int[] arr, int k) {
        Arrays.sort(arr);
        int[] rst = new int[k];
        System.arraycopy(arr, 0, rst, 0, k);
        return rst;
    }
}
