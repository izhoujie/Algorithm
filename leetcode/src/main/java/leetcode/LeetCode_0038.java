package leetcode;

/**
 * @author ZhouJie
 * @date 2020年2月1日 下午5:53:12 
 * @Description: 38. 外观数列
 *
	「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
	
	1.     1
	2.     11
	3.     21
	4.     1211
	5.     111221
	1 被读作  "one 1"  ("一个一") , 即 11。
	11 被读作 "two 1s" ("两个一"）, 即 21。
	21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
	
	给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
	
	注意：整数序列中的每一项将表示为一个字符串。
	
	 
	
	示例 1:
	
	输入: 1
	输出: "1"
	解释：这是一个基本样例。
	示例 2:
	
	输入: 4
	输出: "1211"
	解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/count-and-say
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-递归；
		2-迭代；
 */
public class LeetCode_0038 {
	public static void main(String[] args) {
		Solution_0038 o = new Solution_0038();
		System.out.println(o.countAndSay_1(37));
	}
}

class Solution_0038 {
	/**
	 * @author ZhouJie
	 * @date 2020年2月1日 下午6:21:31 
	 * @Description: TODO(方法简述) 
	 * @return String 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月1日 下午6:21:31]  
	 * @UpdateRemark:1-递归
	 *
	 */
	public String countAndSay_1(int n) {
		if (n == 1) {
			return "1";
		} else {
			String s = countAndSay_1(n - 1);
			char c = s.charAt(0);
			int count = 0;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == c) {
					count++;
				} else {
					sb.append(count).append(c);
					c = s.charAt(i);
					count = 1;
				}
			}
			sb.append(count).append(c);
			return sb.toString();
		}
	}

	/**
	 * @author ZhouJie
	 * @date 2020年2月1日 下午6:21:39 
	 * @Description: TODO(方法简述) 
	 * @return String 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月1日 下午6:21:39]  
	 * @UpdateRemark:迭代  
	 *
	 */
	public String countAndSay_2(int n) {
		String s = "1";
		if (n == 1) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		while (--n > 0) {
			char c = s.charAt(0);
			int count = 0;
			// 清空
			sb.setLength(0);
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == c) {
					count++;
				} else {
					sb.append(count).append(c);
					c = s.charAt(i);
					count = 1;
				}
			}
			s = sb.append(count).append(c).toString();
		}
		return s;
	}
}
