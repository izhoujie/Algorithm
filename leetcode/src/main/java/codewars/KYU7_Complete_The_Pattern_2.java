package codewars;

/**
 * @author ZhouJie
 * @date 2020年3月3日 上午1:46:10 
 * @Description: Complete The Pattern #2
 *
	Task:
	You have to write a function pattern which returns the following Pattern (See Pattern & Examples) upto n number of rows.
	
	Note: Returning the pattern is not the same as Printing the pattern.
	Rules/Note:
	If n < 1 then it should return "" i.e. empty string.
	There are no whitespaces in the pattern.
	Pattern:
	(n)(n-1)(n-2)...4321
	(n)(n-1)(n-2)...432
	(n)(n-1)(n-2)...43
	(n)(n-1)(n-2)...4
	...............
	..............
	(n)(n-1)(n-2)
	(n)(n-1)
	(n)
	Examples:
	pattern(4):
	
	4321
	432
	43
	4
	pattern(11):
	
	1110987654321
	111098765432
	11109876543
	1110987654
	111098765
	11109876
	1110987
	111098
	11109
	1110
	11
	Hint: Use \n in string to jump to next line
 */
public class KYU7_Complete_The_Pattern_2 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月3日 上午1:58:17 
	 * @param: @param n
	 * @param: @return
	 * @return: String
	 * @Description: 1-逐层遍历即可；
	 *
	 */
	public static String pattern(int n) {
		if (n < 1) {
			return "";
		}
		StringBuilder sb = new StringBuilder(n * (n - 1) / 2);
		for (int i = 0; i < n; i++) {
			int k = n;
			for (int j = 0; j < n - i; j++) {
				sb.append(k--);
			}
			sb.append("\n");
		}
		return sb.substring(0, sb.length() - 1);
	}
}
