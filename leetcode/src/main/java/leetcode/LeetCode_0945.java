package leetcode;

import java.util.Arrays;

/**
 * @author ZhouJie
 * @date 2020年3月22日 下午8:04:55 
 * @Description: 945. 使数组唯一的最小增量
 *
	给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
	
	返回使 A 中的每个值都是唯一的最少操作次数。
	
	示例 1:
	
	输入：[1,2,2]
	输出：1
	解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
	示例 2:
	
	输入：[3,2,1,2,1,7]
	输出：6
	解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
	可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
	提示：
	
	0 <= A.length <= 40000
	0 <= A[i] < 40000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-排序逐步累加；
		2-统计逐步累加；
		3-标记路径/压缩路径；
 */
public class LeetCode_0945 {

}

class Solution_0945 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月22日 下午8:08:06 
	 * @param: @param A
	 * @param: @return
	 * @return: int
	 * @Description: 1-先排序，再从左向右累加每两个临近数需要的+1操作数；
	 * 				时间复杂度：O(NlogN) N=A.length
	 * 				空间复杂度：O(1)
	 *
	 */
	public int minIncrementForUnique_1(int[] A) {
		int len = 0, move = 0;
		if (A == null || (len = A.length) < 2) {
			return move;
		}
		Arrays.sort(A);
		for (int i = 1; i < len; i++) {
			// 若当前值小于等于前一个值，说明需要进行+1操作，+1操作的次数就等于差值再+1，此外还需要更新当前值为前一个值+1
			if (A[i] <= A[i - 1]) {
				move += A[i - 1] - A[i] + 1;
				A[i] = A[i - 1] + 1;
			}
		}
		return move;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月22日 下午8:15:17 
	 * @param: @param A
	 * @param: @return
	 * @return: int
	 * @Description: 2-先统计再由小到大进行操作数的累加，每次需要累加+1次数的是相同数的个数；
	 * 				其实统计这一步隐含了排序（自然数的特性），这也是比1方法快的关键原因
	 * 				时间复杂度：O(N) N=max(A.length，max(A))
	 * 				空间复杂度：O(40001)即O(1)
	 */
	public int minIncrementForUnique_2(int[] A) {
		int move = 0;
		if (A == null || A.length < 2) {
			return move;
		}
		// 因为最大数是3999，若+1为40000，需要用到40000索引
		int[] statistics = new int[40001];
		// 记录最大数，用作遍历statistics的右边界
		int max = 0;
		for (int i : A) {
			statistics[i]++;
			max = Math.max(max, i);
		}
		max++;
		// max是A中最终可能的最大值
		for (int i = 0; i < max; i++) {
			// 若A中statistics[i]的个数大于1，说明statistics[i]-1个数需要进行+1操作，
			// 这一步只是给statistics[i]-1个数各进行了一次+1操作，后续的+1交给statistics[i+1]去完成（递归）
			if (statistics[i] > 1) {
				move += statistics[i] - 1;
				statistics[i + 1] += statistics[i] - 1;
			}
		}
		// 若statistics[max]的个数大于1，则statistics[max]-1个数（记为n个）需要进行+1操作；
		// 这n个数依次需要进行+1的次数为1、2、3、4....n，即对1-n求和，直接使用求和公式
		if (statistics[max] > 1) {
			int n = statistics[max] - 1;
			move += n * (n + 1) / 2;
		}
		return move;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月22日 下午8:36:13 
	 * @param: @param A
	 * @param: @return
	 * @return: int
	 * @Description: 1-路径压缩；（来自LeetCode评论区，很秀...）
	 * 				时间复杂度：O(N) N=A.length
	 * 				空间复杂度：O(80000)即O(1)
	 * 				因为findPath每次可能更改多个点位的值，所以效率没有方法2高
	 */

	// 若A中所有数都相等且都为39999，则+1操作完成时，最大值将为79999
	int[] path = new int[80000];

	public int minIncrementForUnique_3(int[] A) {
		int move = 0;
		if (A == null || A.length < 2) {
			return move;
		}
		// -1为空地址标记，与A中数不同即可
		Arrays.fill(path, -1);
		for (int i : A) {
			int j = findPath(i);
			move += j - i;
		}
		return move;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月22日 下午8:49:55 
	 * @param: @param i
	 * @param: @return
	 * @return: int
	 * @Description: 路径压缩核心
	 *
	 */
	private int findPath(int i) {
		// 初次遇到点位，记录值并返回，此时j=0
		if (path[i] == -1) {
			path[i] = i;
			return i;
		} else {
			// 若i有记录，则向后找path[i] + 1位置的值，并最终递归更新路径值
			path[i] = findPath(path[i] + 1);
			return path[i];
		}
	}
}
