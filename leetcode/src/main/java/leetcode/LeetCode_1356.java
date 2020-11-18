package leetcode;


import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author zhoujie
 * @date 2020/11/6 16:11
 * @description: 1356. 根据数字二进制下 1 的数目排序
 * <p>
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 * <p>
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 * <p>
 * 请你返回排序后的数组。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [0,1,2,3,4,5,6,7,8]
 * 输出：[0,1,2,4,8,3,5,6,7]
 * 解释：[0] 是唯一一个有 0 个 1 的数。
 * [1,2,4,8] 都有 1 个 1 。
 * [3,5,6] 有 2 个 1 。
 * [7] 有 3 个 1 。
 * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
 * 示例 2：
 * <p>
 * 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
 * 输出：[1,2,4,8,16,32,64,128,256,512,1024]
 * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
 * 示例 3：
 * <p>
 * 输入：arr = [10000,10000]
 * 输出：[10000,10000]
 * 示例 4：
 * <p>
 * 输入：arr = [2,3,5,7,11,13,17,19]
 * 输出：[2,3,5,17,7,11,13,19]
 * 示例 5：
 * <p>
 * 输入：arr = [10,100,1000,10000]
 * 输出：[10,100,10000,1000]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^4
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_1356 {
}

class Solution_1356 {
    /**
     * @return int[]
     * @author zhoujie
     * @date 2020/11/6 16:11
     * @param: arr
     * @description: 用十进制的高位存bit为1的数量，用十进制的低位存原始值，把原始数扩充为十进制6个长度的值，然后用集合工具排序后还原值即可；
     */
    public int[] sortByBits_1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 高位存bit为1的值，低位存原始值
            arr[i] += Integer.bitCount(arr[i]) * 100000;
        }
        // 排序
        Arrays.sort(arr);
        // 还原
        for (int i = 0; i < arr.length; i++) {
            arr[i] %= 100000;
        }
        return arr;
    }

    /**
     * @return int[]
     * @author zhoujie
     * @date 2020/11/18 16:38
     * @param: arr
     * @description: java8 stream语法:Arrays.stream(arr)
     */
    public int[] sortByBits_2(int[] arr) {
        return Arrays.stream(arr).map(e -> Integer.bitCount(e) * 100000 + e).sorted().map(e -> e % 100000).toArray();
    }

    /**
     * @return int[]
     * @author zhoujie
     * @date 2020/11/18 16:47
     * @param: arr
     * @description: java8 IntStream语法:IntStream.of(arr)
     */
    public int[] sortByBits_3(int[] arr) {
        return IntStream.of(arr).map(e -> Integer.bitCount(e) * 100000 + e).sorted().map(e -> e % 100000).toArray();
    }


}