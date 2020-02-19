package codewars;

/**
 * @author ZhouJie
 * @date 2020年2月20日 上午12:17:27 
 * @Description: Two to One
 *
	Take 2 strings s1 and s2 including only letters from ato z. Return a new sorted string, the longest possible, containing distinct letters,
	
	each taken only once - coming from s1 or s2.
	Examples:
	a = "xyaabbbccccdefww"
	b = "xxxxyyyyabklmopq"
	longest(a, b) -> "abcdefklmopqwxy"
	
	a = "abcdefghijklmnopqrstuvwxyz"
	longest(a, a) -> "abcdefghijklmnopqrstuvwxyz"
 */
public class KYU7_Two_to_One {
	public static String longest_1(String s1, String s2) {
		String s = s1 == null ? "" : s1;
		s += s2 == null ? "" : s2;
		int[] rst = new int[26];

		if (s.length() > 0) {
			for (char c : s.toCharArray()) {
				rst[c - 'a']++;
			}
		}
		StringBuilder sb = new StringBuilder(s.length());
		for (int i = 0; i < rst.length; i++) {
			if (rst[i] > 0) {
				sb.append((char) ('a' + i));
			}
		}
		return sb.toString();
	}

	public static String longest_2(String s1, String s2) {
		StringBuilder sb = new StringBuilder();
		(s1 + s2).chars().distinct().sorted().forEach(c -> sb.append((char) c));
		return sb.toString();
	}

	public static String longest_3(String s1, String s2) {
		StringBuilder sb = new StringBuilder();
		int[] rst = new int[26];
		(s1 + s2).chars().forEach(c -> rst[c - 'a']++);
		for (int i = 0; i < rst.length; i++) {
			if (rst[i] > 0) {
				sb.append((char) ('a' + i));
			}
		}
		return sb.toString();
	}
}
