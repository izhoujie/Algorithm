package com.izhoujie.baseAlgorithm;

import java.util.Arrays;

/**
 * @author admin@izhoujie.com
 *
 *         -每次找出当前最值，两遍归中逼近 类似cocktailSort-鸡尾酒排序/双向冒泡排序
 *
 *         -时间复杂度:O(n*n)
 *
 *         -稳定性:稳定
 */
public class MinMaxSort {
	public static void main(String[] args) {

		int range = 300;
		int length = 25;
		int[] array = ShuffArray.getArray(range, length);

		System.out.println("排序前：" + Arrays.toString(array));
		sort(array);
		System.out.println("排序后：" + Arrays.toString(array));
	}

	public static void sort(int[] array) {
		int len = array.length;
		int min, max;
		int x, y;
		for (int i = 0; i < len / 2; i++) {
			x = y = -1;
			min = max = array[i];

			for (int j = i; j < len - j; j++) {
				if (array[j] < min) {
					min = array[j];
					x = j;
				}
				if (array[j] > max) {
					max = array[j];
					y = j;
				}
			}
			if (x != -1) {
				array[x] = array[i];
				array[i] = min;
			}
			if (y != -1) {
				array[y] = array[len - i - 1];
				array[len - i - 1] = max;
			}
		}
	}
}
