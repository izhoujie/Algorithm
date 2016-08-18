package com.izhoujie.baseAlgorithm;

import java.util.Arrays;

/**
 * @author admin@izhoujie.com
 *
 *         -折半插入排序
 *
 *         -时间复杂度：O(n*n)
 *
 *         -稳定性：稳定
 */
public class BinarySelectSort {
	public static void main(String[] args) {
		int range = 100;
		int length = 25;

		int[] array = ShuffArray.getArray(range, length);
		System.out.println("排序前：" + Arrays.toString(array));
		selectSort(array);
		System.out.println("排序后：" + Arrays.toString(array));
	}

	public static void selectSort(int[] array) {
		int len = array.length;
		int temp;
		for (int i = 1; i < len; i++) {

			if (array[i] < array[i - 1]) {
				
				for (int j = i; j > 0; j--) {
					if (array[j] < array[j - 1]) {
						temp = array[j];
						array[j] = array[j - 1];
						array[j - 1] = temp;
					}
				}
			}
		}
	}
}
