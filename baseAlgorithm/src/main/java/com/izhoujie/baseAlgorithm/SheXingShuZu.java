package com.izhoujie.baseAlgorithm;

/**
 * @author km-zhou
 * 
 *         --蛇形数组
 * 
 *         --给定正整数n(n>3),回环输出1-n*n的所有数
 *
 */
public class SheXingShuZu {

	// 控制循环方向的数组指针：右、下、左、上
	private static int[][] index = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) {
		SheXingShuZu shuZu = new SheXingShuZu();
		// 给定的正整数
		int number = 8;

		int[][] array = new int[number][number];
		// 数组的蛇形赋值
		shuZu.print(number, array);
		int value = 0;
		// 获取格式化输出位数
		int num_0 = (number * number + "").length();

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {

				value++;
				// 格式化输出
				System.out.printf("%" + num_0 + "d ", array[j][i]);
				if (value % number == 0) {
					System.out.println();
				}
			}
		}
	}

	// 蛇形数组赋值
	private void print(int n, int[][] array) {

		// 最大值
		int endValue = n * n;

		// 初始化参数
		int value = 1;
		int p = 0;
		int index_x = 0;
		int index_y = 0;
		int x = -1;
		int y = 0;
		int x1, y1;
		// 终止判断
		while (value <= endValue) {
			// 某层的循环指针
			p = p % 4;
			index_x = index[p][0];
			index_y = index[p][1];

			// 某层的赋值-为false该层赋值完毕跳出
			while (true) {
				x1 = x + index_x;
				y1 = y + index_y;
				// 边界检测-是否数组越界
				if (x1 < 0 || x1 > n - 1 || y1 < 0 || y1 > n - 1) {
					break;
				}
				// 边界检测-是否已达已赋值位置
				if (array[x1][y1] != 0) {
					break;
				}
				x = x1;
				y = y1;
				// 赋值
				array[x][y] = value++;
			}
			// 进行下一层指针赋值
			p++;
		}
	}
}
