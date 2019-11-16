package com.izhoujie.someinteresting;

/**
 * @author admin@izhoujie.com
 *
 *         2016年9月5日 23:37:05
 * 
 *         题干：求两个数的加减乘除，要求不能使用+、-、*、/算数运算符
 * 
 *         引申：求两个数的差、乘积、相除不能使用+、-、*、/
 * 
 *         方案：所有的+、-、*、/运算在计算机的底层其实都是位运算，故可以用位运算来实现简单算数运算
 * 
 *         以下为实现
 */
public class BinnaryArithmeticOperation {

    public static void main(String[] args) {
	int a = 99;
	int b = -435;
	// 求和测试
	int sum1 = add(a, b);
	System.out.println("a + b = " + sum1);
	// 求和测试
	int sum2 = addOneLine(a, b);
	System.out.println("a + b = " + sum2);
	// 取相反数测试
	int changeValue = symbolChange(b);
	System.out.println(changeValue);

	// 求乘积测试
	int result = multiply(a, b);
	System.out.println(result);

    }

    /**
     * 乘积运算
     * 
     * @param a
     * @param b
     * @return a*b
     */
    public static int multiply(int a, int b) {
	boolean flag = false;
	if (b < 0) {
	    flag = true;
	    b = symbolChange(b);
	}
	int length = getBits(b);
	int index = 0;
	int sum = 0;

	while (index < length) {
	    // 获取index+1位上的二进制值
	    if (getIndexBit(b, index) > 0) {
		sum = addOneLine(sum, a << index);
	    }
	    index = addOneLine(index, 1);
	}
	return flag ? symbolChange(sum) : sum;	   
    }

    /**
     * @param n
     * @param point
     *            指定的二进制位点
     * @return n在point处二进制位点的值
     */
    public static int getIndexBit(int n, int point) {
	// 获取n在point位上的值
	return (n >> point) & 1;
    }

    /**
     * @param n
     * @return length n的二进制表示中去除左边0后的有效二进制位数
     */
    public static int getBits(int n) {
	int length = 0;
	while (n > 0) {
	    length = addOneLine(length, 1);
	    n = n >> 1;
	}
	return length;
    }

    /**
     * @param num
     * @return -num
     * 
     *         求一个数的相反数：对其所有位先取反再加1
     */
    public static int symbolChange(int num) {
	// 0XFFFFFFFF是int范围内-1的二进制码，32位都为1
	return addOneLine(num ^ 0XFFFFFFFF, 1);
    }

    /**
     * 求和/求差运算
     * 
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
     * 求和/求差运算-简化
     * 
     * @param a
     * @param b
     * @return a+b
     */
    public static int addOneLine(int a, int b) {
	// 对add(a,b)方法的代码压缩，一行解决~
	return b == 0 ? a : addOneLine(a ^ b, (a & b) << 1);
    }
}
