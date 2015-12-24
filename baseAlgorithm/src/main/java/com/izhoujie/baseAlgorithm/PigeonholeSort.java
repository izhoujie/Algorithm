package com.izhoujie.baseAlgorithm;

import java.util.Arrays;

/**
 * @author km-zhou
 *
 *         -鸽巢排序
 * 
 *         -时间复杂度：O(n+m) m为f(n)最大值
 * 
 *         -空间复杂度：O(n+m) m为f(n)最大值
 * 
 *         -稳定性：稳定
 */
public class PigeonholeSort {
	public static void main(String[] args) {
		int range = 200;
		int length = 20;

		int[] array = ShuffArray.getArray(range, length);

		System.out.println("排序前：" + Arrays.toString(array));
		pigeonholeSort(array, range);
		System.out.println("排序后：" + Arrays.toString(array));

	}

	public static void pigeonholeSort(int[] array, int range) {
		// 辅助数组：鸽巢
		int[] pigeonhole = new int[range];

		// 数据入巢
		for (int i = 0; i < array.length; i++) {
			pigeonhole[array[i]]++;
		}
		// 按巢中顺序回填原数组
		for (int i = 0, k = 0; i < pigeonhole.length; i++) {
			while (pigeonhole[i]-- > 0) {
				array[k++] = i;
			}
		}
	}
}
