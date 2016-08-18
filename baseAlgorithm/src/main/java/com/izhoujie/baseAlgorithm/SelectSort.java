package com.izhoujie.baseAlgorithm;

import java.util.Arrays;

/**
 * @author admin@izhoujie.com
 * 
 *         -直接选择排序
 *
 *         -时间复杂度：O(n*n)
 *
 *         -稳定性：不稳定
 */
public class SelectSort {

	public static void main(String[] args) {
		int range = 300;
		int length = 24;

		int[] array = ShuffArray.getArray(range, length);

		System.out.println("排序前：" + Arrays.toString(array));
		insertSort(array);
		System.out.println("排序后：" + Arrays.toString(array));
	}

	public static void insertSort(int[] array) {
		int len = array.length;
		// 标记值和位置
		int temp = 0;
		int flag = 0;
		for (int i = 0; i < len - 1; i++) {
			temp = array[i];
			flag = 0;
			// 寻值
			for (int j = i + 1; j < len; j++) {
				if (temp > array[j]) {
					temp = array[j];
					flag = j;
				}
			}
			// 若寻到，则换值
			if (flag > 0) {
				array[flag] = array[i];
				array[i] = temp;
			}
		}
	}
}
