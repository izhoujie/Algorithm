package com.izhoujie.someinteresting;

/**
 * @author admin@izhoujie.com
 * 
 *         2016年9月6日 18:45:59
 * 
 *         题干：求1~n的和，要求不使用任何循环语句(for、while、do-while等)以及if-else判断
 * 
 *         方案：java可以用递归来实现，其中利用了短路与 &&的特性，其他语言也有更好的实现，基于语言特性了
 *
 */
public class SumOfN {

    public static void main(String[] args) {
	int n = 100;

	int sum = getSumOfN(n);
	System.out.println(sum);

    }

    /**
     * @param n
     * @return 1~n的自然数和
     */
    public static int getSumOfN(int n) {
	int sum = 0;

	boolean flag = (n != 0) && ((sum = getSumOfN(n - 1)) > 0);

	return sum + n;
    }
}
