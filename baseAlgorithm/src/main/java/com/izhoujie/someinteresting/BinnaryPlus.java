package com.izhoujie.someinteresting;

/**
 * @author km-zhou
 *
 *         2016年9月5日 23:37:05
 * 
 *         题干：求两个数的和，要求不能使用+、-、*、/算数运算符
 * 
 *         方案：所有的+、-、*、/运算在计算机的底层其实都是位运算，故可以用位运算来实现+运算
 */
public class BinnaryPlus {

    public static void main(String[] args) {
	int a = 135;
	int b = 246;

	int sum1 = add(a, b);
	System.out.println("a + b = " + sum1);

	int sum2 = addOneLine(a, b);
	System.out.println("a + b = " + sum2);
    }

    /**
     * @param a
     * @param b
     * @return a+b
     */
    public static int add(int a, int b) {
	// 若b为0则说明上一次与运算为0，那么此时的a即为最终的结果值
	if (b == 0) {
	    return a;
	} else {
	    // 异或运算，只求a和b对应位相加后的未进位的值
	    int x = a ^ b;
	    // 与运算，只计算a和b相加后的进位情况，若非零则左移1位进位
	    int y = (a & b) << 1;
	    return add(x, y);
	}
    }

    /**
     * @param a
     * @param b
     * @return a+b
     */
    public static int addOneLine(int a, int b) {
	// 对add(a,b)方法的代码压缩，一行解决~
	return b == 0 ? a : addOneLine(a ^ b, (a & b) << 1);
    }
}
