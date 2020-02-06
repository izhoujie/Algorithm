package leetcode;

/**
 * @author ZhouJie
 * @date 2020年2月2日 下午6:29:38 
 * @Description: 43. 字符串相乘
 *
	给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
	
	示例 1:
	
	输入: num1 = "2", num2 = "3"
	输出: "6"
	示例 2:
	
	输入: num1 = "123", num2 = "456"
	输出: "56088"
	说明：
	
	num1 和 num2 的长度小于110。
	num1 和 num2 只包含数字 0-9。
	num1 和 num2 均不以零开头，除非是数字 0 本身。
	不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/multiply-strings
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-拆分为多位数和单位数的乘积，再累加和（效率低）；
		2-累乘计算对应位的值；tips:两数相乘，积的位数至少是两数的位数和减1，最大为两数位数和；
 *
 */
public class LeetCode_0043 {
	public static void main(String[] args) {
		String s1 = new String("0");
		String s2 = new String("52");
		System.out.println(new Solution_0043().multiply_1(s1, s2));
		System.out.println(new Solution_0043().multiply_2(s1, s2));
	}
}

class Solution_0043 {
	/**
	 * @author ZhouJie
	 * @date 2020年2月2日 下午11:14:44 
	 * @Description: TODO(方法简述) 
	 * @return String 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月2日 下午11:14:44]  
	 * @UpdateRemark:1-拆分先求单个积，再求和；  
	 *
	 */
	public String multiply_1(String num1, String num2) {
		int n;
		if (num1 == null || num2 == null || num1.length() == 0 || (n = num2.length()) == 0 || num1.equals("0")
				|| num2.equals("0")) {
			return "0";
		}
		String rst = "0";
		String s0 = "";
		while (n-- > 0) {
			char c = num2.charAt(n);
			if (c == '0') {
				s0 += "0";
				continue;
			}
			rst = add(rst, helper(num1 + s0, c));
			s0 += "0";
		}
		return rst;
	}

	private String add(String a, String b) {
		int n1 = a.length();
		int n2 = b.length();
		String s = "";
		int carry = 0;
		while (n1 > 0 || n2 > 0) {
			int a1 = --n1 > -1 ? a.charAt(n1) - '0' : 0;
			int a2 = --n2 > -1 ? b.charAt(n2) - '0' : 0;
			carry += a1 + a2;
			s = carry % 10 + s;
			carry /= 10;
		}
		return carry > 0 ? carry + s : s;
	}

	private String helper(String a, char b) {
		int c = b - '0';
		int n = a.length();
		int carry = 0;
		String s = "";
		while (n-- > 0) {
			int d = a.charAt(n) - '0';
			carry += c * d;
			s = carry % 10 + s;
			carry /= 10;
		}
		return carry > 0 ? carry + s : s;
	}

	/**
	 * @author ZhouJie
	 * @date 2020年2月2日 下午11:15:18 
	 * @Description: TODO(方法简述) 
	 * @return String 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月2日 下午11:15:18]  
	 * @UpdateRemark:2-竖式单步乘法，计算对应位置的结果；（来自leetcoed评论区解法）
	 *
	 */
	/**
	num1的第i位(高位从0开始)和num2的第j位相乘的结果在乘积中的位置是[i+j, i+j+1]
	例: 123 * 45,  123的第1位 2 和45的第0位 4 乘积 08 存放在结果的第[1, 2]位中
	  index:    0 1 2 3 4  
	      
	                1 2 3
	            *     4 5
	            ---------
	                  1 5
	                1 0
	              0 5
	            ---------
	              0 6 1 5
	                1 2
	              0 8
	            0 4
	            ---------
	            0 5 5 3 5
	这样我们就可以单独都对每一位进行相乘计算把结果存入相应的index中        
	**/
	public String multiply_2(String num1, String num2) {
		if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0 || num1.equals("0")
				|| num2.equals("0")) {
			return "0";
		}
		int n1 = num1.length();
		int n2 = num2.length();
		int len = n1 + n2;
		int[] rst = new int[len];
		for (int i = n1 - 1; i > -1; i--) {
			int d = num1.charAt(i) - '0';
			for (int j = n2 - 1; j > -1; j--) {
				int carry = d * (num2.charAt(j) - '0') + rst[i + j + 1];
				rst[i + j + 1] = carry % 10;
				rst[i + j] += carry / 10;
			}
		}
		StringBuilder sb = new StringBuilder();
		int k = rst[0] == 0 ? 1 : 0;
		for (; k < len; k++) {
			sb.append(rst[k]);
		}
		return sb.toString();
	}
}
