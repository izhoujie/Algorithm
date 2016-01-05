package com.izhoujie.baseAlgorithm;

import java.util.Arrays;

/**
 * @author km-zhou
 * 
 *         -直接插入排序
 * 
 *         -时间复杂度：O(n*n)
 * 
 *         -稳定性：稳定
 *
 */
public class SelectInsertSort {
	public static void main(String[] args) {
		int range = 250;
		int length = 26;

		int[] array = ShuffArray.getArray(range, length);

		System.out.println("排序前：" + Arrays.toString(array));
		insertSort(array);
		System.out.println("排序后：" + Arrays.toString(array));
	}

	public static void insertSort(int[] array) {
		int len = array.length;
		int temp;
		for (int i = 1; i < len; i++) {
			temp = array[i];
			int left = 0;
			int right = i - 1;
			// 若此位置可与前面已排序的保持有序则跳过此次折半比较查找
			if (array[i] < array[i - 1]) {
				while (left <= right) {
					int mid = (left + right) / 2;
					if (array[mid] <= temp) {
						left = mid + 1;
					} else {
						right = mid - 1;
					}
					mid = (left + right) / 2;
				}
				for (int j = i; j > left; j--) {
					array[j] = array[j - 1];
				}
				array[left] = temp;
			}
		}
	}
}