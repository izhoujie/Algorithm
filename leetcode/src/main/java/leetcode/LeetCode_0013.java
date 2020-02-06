package leetcode;

import java.util.HashMap;

/**
 * @author ZhouJie
 * @date 2020年1月2日 下午11:07:55 
 * @Description: 13. 罗马数字转整数
 *
	罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
	
	字符          数值
	I             1
	V             5
	X             10
	L             50
	C             100
	D             500
	M             1000
	例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
	
	通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
	
	I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
	X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
	C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
	给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
	
	示例 1:
	
	输入: "III"
	输出: 3
	示例 2:
	
	输入: "IV"
	输出: 4
	示例 3:
	
	输入: "IX"
	输出: 9
	示例 4:
	
	输入: "LVIII"
	输出: 58
	解释: L = 50, V= 5, III = 3.
	示例 5:
	
	输入: "MCMXCIV"
	输出: 1994
	解释: M = 1000, CM = 900, XC = 90, IV = 4.
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/roman-to-integer
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-初始化map，从高位到低位依次解析；map可以换为switch处理
		2-使用if-else/switch；效率比1高很多；
 */
public class LeetCode_0013 {

}

class Solution_0013 {
	/**
	 * @author ZhouJie
	 * @date 2020年2月3日 下午6:29:51 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 下午6:29:51]  
	 * @UpdateRemark:1-
	 *
	 */
	public int romanToInt_1(String s) {
		if (s == null || s.length() < 1) {
			return 0;
		}
		HashMap<Character, Integer> romaMap = new HashMap<Character, Integer>();
		romaMap.put('I', 1);
		romaMap.put('V', 5);
		romaMap.put('X', 10);
		romaMap.put('L', 50);
		romaMap.put('C', 100);
		romaMap.put('D', 500);
		romaMap.put('M', 1000);

		int last = 1;
		int rst = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			Integer now = romaMap.get(s.charAt(i));
			if (now < last) {
				rst -= now;
			} else {
				rst += now;
				last = now;
			}
		}
		return rst;
	}

	/**
	 * @author ZhouJie
	 * @date 2020年2月3日 下午6:30:02 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 下午6:30:02]  
	 * @UpdateRemark:2- 
	 *
	 */
	public int romanToInt_2(String s) {
		if (s == null || s.length() < 1) {
			return 0;
		}
		int rst = 0, num = 0, now = 1;
		for (int i = s.length() - 1; i > -1; i--) {
			char c = s.charAt(i);
			if (c == 'I') {
				num = 1;
			} else if (c == 'V') {
				num = 5;
			} else if (c == 'X') {
				num = 10;
			} else if (c == 'L') {
				num = 50;
			} else if (c == 'C') {
				num = 100;
			} else if (c == 'D') {
				num = 500;
			} else if (c == 'M') {
				num = 1000;
			}
			if (num < now) {
				rst -= num;
			} else {
				rst += num;
				now = num;
			}
		}
		return rst;
	}
}