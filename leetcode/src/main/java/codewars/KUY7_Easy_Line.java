package codewars;

import java.math.BigInteger;

/**
 * @author ZhouJie
 * @date 2020年3月5日 下午10:14:04 
 * @Description: Easy Line
 *
	In the drawing below we have a part of the Pascal's triangle, lines are numbered from zero (top).
	
	We want to calculate the sum of the squares of the binomial coefficients on a given line with a function called easyline (or easyLine or easy-line).
	
	Can you write a program which calculate easyline(n) where n is the line number?
	
	The function will take n (with: n>= 0) as parameter and will return the sum of the squares of the binomial coefficients on line n.
	
	##Examples:
	
	easyline(0) => 1
	easyline(1) => 2
	easyline(4) => 70
	easyline(50) => 100891344545564193334812497256
	##Ref: http://mathworld.wolfram.com/BinomialCoefficient.html
	
	alternative text
	
	Note:
	In Javascript, Coffeescript, Typescript, C++, PHP, C, R, Nim, Fortran to get around the fact that we have no big integers the function easyLine(n) will in fact return
	
	round(log(easyline(n)))
	
	and not the easyline(n) of the other languages.
	
	So, in Javascript, Coffeescript, Typescript, C++, PHP, R, Nim, C, Fortran:
	
	easyLine(0) => 0
	easyLine(1) => 1
	easyLine(4) => 4
	easyLine(50) => 67
 */
public class KUY7_Easy_Line {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月5日 下午10:22:04 
	 * @param: @param n
	 * @param: @return
	 * @return: BigInteger
	 * @Description: 1-题干大意：求杨辉三角/帕斯卡三角的第n项的各项平方和；（第一项为1，下标从0开始）
	 * 				第n项杨辉三角的各项值公式f(1)=f(n)=1;
	 * 				公式：第i项：f(i)=f(i-1)*(n-i-1)/(i-1+1)
	 * 
	 *
	 */
	public static BigInteger easyLine(int n) {
		BigInteger rst = new BigInteger("0");
		BigInteger pre = new BigInteger("1");
		for (int i = 0; i <= n; i++) {
			rst = rst.add(pre.multiply(pre));
			pre = pre.multiply(new BigInteger(String.valueOf(n - i))).divide(new BigInteger(String.valueOf(i + 1)));
		}
		return rst;
	}
}
