package leetcode;

import java.util.Arrays;

/**
 * @author ZhouJie
 * @date 2020年3月20日 下午5:21:10 
 * @Description: 面试题40. 最小的k个数
 *
	输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
	
	示例 1：
	
	输入：arr = [3,2,1], k = 2
	输出：[1,2] 或者 [2,1]
	示例 2：
	
	输入：arr = [0,1,2,1], k = 1
	输出：[0]
	 
	
	限制：
	
	0 <= k <= arr.length <= 10000
	0 <= arr[i] <= 10000
 */
public class LeetCode_Offer_40 {
}

class Solution_Offer_40 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月20日 下午4:31:31 
	 * @param: @param arr
	 * @param: @param k
	 * @param: @return
	 * @return: int[]
	 * @Description: 1-每次都取一个数跟已取得的数进行比较并二分查找插入，效率比较低..
	 * 				时间复杂度：Nlogk，N=arr.length
	 *
	 */
	public int[] getLeastNumbers_1(int[] arr, int k) {
		int len = 0;
		if (arr == null || (len = arr.length) == 0) {
			return new int[len];
		}
		if (k == 0) {
			return new int[k];
		}
		if (k >= len) {
			return arr;
		}
		int[] rst = new int[k];
		int count = 0;
		rst[0] = arr[0];
		for (int i = 1; i < len; i++) {
			if (arr[i] >= rst[count] && count < k - 1) {
				rst[++count] = arr[i];
			} else if (arr[i] < rst[count]) {
				int low = 0, high = count;
				while (low < high) {
					int mid = (high + low) / 2;
					if (rst[mid] < arr[i]) {
						low = mid + 1;
					} else {
						high = mid;
					}
				}
				int last = count;
				if (count < k - 1) {
					last++;
					count++;
				}
				while (last > low) {
					rst[last--] = rst[last];
				}
				rst[last] = arr[i];
			}
		}
		return rst;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月20日 下午4:40:29 
	 * @param: @param arr
	 * @param: @param k
	 * @param: @return
	 * @return: int[]
	 * @Description: 2-条件局部快排；时间复杂度：N N=arr.length
	 *
	 */
	public int[] getLeastNumbers_2(int[] arr, int k) {
		int len = 0;
		if (arr == null || (len = arr.length) == 0) {
			return new int[len];
		}
		if (k == 0) {
			return new int[k];
		}
		if (k >= len) {
			return arr;
		}
		quickSort(arr, 0, len - 1, k);
		return Arrays.copyOf(arr, k);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月20日 下午4:34:58 
	 * @param: @param arr
	 * @param: @param i
	 * @param: @param len
	 * @param: @param k
	 * @return: void
	 * @Description: 快排
	 *
	 */
	private void quickSort(int[] arr, int start, int end, int k) {
		if (start >= end) {
			return;
		}
		int left = start, right = end;
		int midVal = arr[(left + right) / 2];
		while (left <= right) {
			while (left <= right && arr[left] < midVal) {
				left++;
			}
			while (left <= right && arr[right] > midVal) {
				right--;
			}
			if (left <= right) {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			}
		}
		// 此时left==right，数组已经分为两部分了，左边都是小于midVal的值，右边都是大于midVal的值
		// 若right不小于k，说明右边的数都太大，只需要对左半边的区间[start，right]排序就可以了
		// 若right小于k，说明已经找到了right个数，只需要再对区间[right，end]排序并再找到k-right个次小数就可以了，但是k的索引不需要变化
		if (right >= k) {
			quickSort(arr, start, right, k);
		} else {
			quickSort(arr, left, end, k);
		}
	}
}