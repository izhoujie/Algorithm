package codewars;

/**
 * @author ZhouJie
 * @date 2020年2月27日 下午6:48:33 
 * @Description: Sum of Digits / Digital Root
 *
	In this kata, you must create a digital root function.
	
	A digital root is the recursive sum of all the digits in a number. Given n, take the sum of the digits of n. If that value has more than one digit, continue reducing in this way until a single-digit number is produced. This is only applicable to the natural numbers.
	
	Here's how it works:
	
	digital_root(16)
	=> 1 + 6
	=> 7
	
	digital_root(942)
	=> 9 + 4 + 2
	=> 15 ...
	=> 1 + 5
	=> 6
	
	digital_root(132189)
	=> 1 + 3 + 2 + 1 + 8 + 9
	=> 24 ...
	=> 2 + 4
	=> 6
	
	digital_root(493193)
	=> 4 + 9 + 3 + 1 + 9 + 3
	=> 29 ...
	=> 2 + 9
	=> 11 ...
	=> 1 + 1
	=> 2
 */
public class KYU6_Sum_of_Digits_Or_Digital_Root {

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月27日 下午7:20:26 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: 1-实质是递归求n的取模与取余的最终和小于10
	 *
	 */
	public static int digital_root_1(int n) {
		return n < 10 ? n : digital_root_1(n / 10 + n % 10);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月27日 下午7:20:59 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: 1-暂时没看懂...（但比方法1的效率要高）
	 *
	 */
	public static int digital_root_2(int n) {
		return (n != 0 && n % 9 == 0) ? 9 : n % 9;
	}
}
