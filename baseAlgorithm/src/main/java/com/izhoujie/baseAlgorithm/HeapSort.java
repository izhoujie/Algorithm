package com.izhoujie.baseAlgorithm;

import java.util.Arrays;

/**
 * @author km-zhou
 *
 *         -堆排序(最大堆)
 *
 *         -时间复杂度：O(nlogn)
 * 
 *         -稳定性：不稳定
 */
public class HeapSort {
	public static void main(String[] args) {
		int range = 300;
		int length = 30;
		int[] array = ShuffArray.getArray(range, length);

		System.out.println("排序前：" + Arrays.toString(array));
		initHeap(array);
		heapSort(array);
		System.out.println("排序后：" + Arrays.toString(array));
	}

	public static void heapSort(int[] array) {
		int len = array.length;
		int temp;
		int tail;
		for (int i = 0; i < len; i++) {
			tail = len - i - 1;
			temp = array[0];
			array[0] = array[tail];
			array[tail] = temp;
			maxHeap(array, tail, 0);
		}
	}

	// 最大堆初始化
	public static void initHeap(int[] array) {
		int len = array.length;

		for (int i = len / 2; i >= 0; i--) {
			maxHeap(array, len, i);

		}
	}

	// 堆调整
	public static void maxHeap(int[] array, int len, int index) {
		int left = 2 * index + 1;
		int right = left + 1;
		int node = index;
		// 左子节点
		if (left < len && array[left] > array[node]) {
			node = left;
		}
		// 右子节点
		if (right < len && array[right] > array[node]) {
			node = right;
		}
		int temp;
		// 若有互换则继续递归进行检查调整
		if (node != index) {
			temp = array[index];
			array[index] = array[node];
			array[node] = temp;

			maxHeap(array, len, node);
		}
	}
}
