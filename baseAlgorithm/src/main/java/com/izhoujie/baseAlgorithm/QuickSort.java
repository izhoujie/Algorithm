package com.izhoujie.baseAlgorithm;

import java.util.Arrays;

/**
 * @author km-zhou
 * 
 *         -快速排序
 * 
 *         -时间复杂度：平均-O(nlogn)最差-O(n*n)
 * 
 *         -稳定性：不稳定
 *
 */
public class QuickSort {
	public static void main(String[] args) {
		int Range = 100;
		int length = 20;
		int[] array = ShuffArray.getArray(Range, length);

		System.out.println("排序前：" + Arrays.toString(array));
		sort(array, 0, length - 1);
		System.out.println("排序后：" + Arrays.toString(array));

	}

	/**
	 * @param array
	 *            待排序数组
	 * @param start
	 *            起始点
	 * @param end
	 *            结束点
	 */
	public static void sort(int[] array, int start, int end) {
		// 若起始点大于等于结束点，则表明此次待排序部分已经有序，直接跳出
		if (start >= end) {
			return;
		}
		int left = start;
		int right = end;
		// 中值-"坑"值
		int mid = array[start];

		while (left < right) {
			// 后向前筛选
			while (left < right && array[right] >= mid) {
				right--;
			}
			// 换值
			if (left < right) {
				array[left] = array[right];
				left++;
			}
			// 前向后扫描
			while (left < right && array[left] <= mid) {
				left++;
			}
			// 换值
			if (left < right) {
				array[right] = array[left];
				right--;
			}
		}
		// 回填坑值
		array[left] = mid;
		// 分治递归
		sort(array, start, left - 1);
		sort(array, right + 1, end);
	}
}
