package com.izhoujie.baseAlgorithm;

import java.util.Arrays;

/**
 * @author km-zhou
 *
 *         -归并排序
 * 
 *         -时间复杂度:O(nlogn) 空间复杂度：O(n)-指额外辅助空间
 * 
 *         -稳定性:稳定
 */
public class MereySort {
	public static void main(String[] args) {
		int range = 150;
		int length = 20;
		int[] array = ShuffArray.getArray(range, length);
		// 辅助数组
		int[] temp = new int[length];

		System.out.println("排序前：" + Arrays.toString(array));
		mereySort(array, 0, length - 1, temp);
		System.out.println("排序后：" + Arrays.toString(array));
	}

	/**
	 * @param array
	 * @param start
	 *            起始点
	 * @param end
	 *            结束点
	 * @param temp
	 *            辅助数组
	 */
	public static void mereySort(int[] array, int start, int end, int[] temp) {
		if (start < end) {
			int mid = (start + end) / 2;

			mereySort(array, start, mid, temp);
			mereySort(array, mid + 1, end, temp);
			mereyArray(array, start, mid, end, temp);
		}
	}

	/**
	 * @param array
	 * @param start
	 *            起始点
	 * @param mid
	 *            分割点
	 * @param end
	 *            结束点
	 * @param temp
	 *            辅助数组
	 */
	public static void mereyArray(int[] array, int start, int mid, int end, int[] temp) {
		int i = start, j = mid + 1;
		int t = 0, k = 0;

		while (i <= mid && j <= end) {

			if (array[i] <= array[j]) {
				temp[k++] = array[i++];
			} else {
				temp[k++] = array[j++];
			}
		}
		while (i <= mid) {
			temp[k++] = array[i++];
		}
		while (j <= end) {
			temp[k++] = array[j++];
		}

		while (t < k) {
			array[start + t] = temp[t++];
		}
	}
}
