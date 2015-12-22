package com.izhoujie.baseAlgorithm;

import java.util.Random;

/**
 * @author km-zhou
 * 
 *         -数组工具类
 *
 */
public class ShuffArray {
	public static void main(String[] args) {

	}

	/**
	 * @param RangeScope
	 *            数据值范围
	 * @param length
	 *            数组长度
	 * @return
	 */
	public static int[] getArray(int RangeScope, int length) {

		Random random = new Random();
		int[] array = new int[length];
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(RangeScope);
		}

		return array;
	}

	public static void printArray(int[] array) {
	}
}
