package codewars;

/**
 * @author ZhouJie
 * @date 2020年2月17日 下午6:28:15 
 * @Description: Bit Counting
 *
	Write a function that takes an integer as input, and returns the number of bits that are equal to one in the binary representation of that number. You can guarantee that input is non-negative.
	
	Example: The binary representation of 1234 is 10011010010, so the function should return 5 in this case
 */
public class KYU6_BitCounting {

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月17日 下午7:45:00 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: 1-迭代；（比递归效率高20%左右）
	 *
	 */
	public static int countBits_1(int n) {
		if (n < 1) {
			return 0;
		}
		int count = 0;
		while (n > 0) {
			count++;
			n &= n - 1;
		}
		return count;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月17日 下午7:45:29 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: 2-递归，代码简洁；
	 *
	 */
	public static int countBits_2(int n) {
		return n > 0 ? countBits_2(n & (n - 1)) + 1 : 0;
	}
}