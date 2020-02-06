package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月1日 下午10:50:01 
 * @Description: 12. 整数转罗马数字
 *
	罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
	
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
	给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
	
	示例 1:
	
	输入: 3
	输出: "III"
	示例 2:
	
	输入: 4
	输出: "IV"
	示例 3:
	
	输入: 9
	输出: "IX"
	示例 4:
	
	输入: 58
	输出: "LVIII"
	解释: L = 50, V = 5, III = 3.
	示例 5:
	
	输入: 1994
	输出: "MCMXCIV"
	解释: M = 1000, CM = 900, XC = 90, IV = 4.
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/integer-to-roman
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-按照1-4，5-9，9-分区间处理高位的数的逻辑一样，只是要换符号---可以抽取相似部分为一个公用处理方法；
		2-直接制定整数和罗马数的对照表，进行减数拼接；（来自LeetCode已提交中解法）
 */
public class LeetCode_0012 {

}

class Solution_0012 {
	/**
	 * @author ZhouJie
	 * @date 2020年2月3日 下午6:19:55 
	 * @Description: TODO(方法简述) 
	 * @return String 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 下午6:19:55]  
	 * @UpdateRemark:1-
	 *
	 */
	public String intToRoman_1(int num) {
		if (num < 1) {
			return "";
		}
		// 定义罗马字符表
		char[] roma = new char[] { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
		int p = 0;
		StringBuilder sb = new StringBuilder();
		// p用来定位V、L、D三个关键位置
		for (int i = 0; num > 0; i++) {
			p = 2 * i + 1;
			int number = num % 10;
			if (number == 9) {
				sb.insert(0, roma[p - 1]).insert(1, roma[p + 1]);
			} else if (number > 4) {
				sb.insert(0, roma[p]);
				while (number > 5) {
					sb.insert(1, roma[p - 1]);
					number--;
				}
			} else if (number == 4) {
				sb.insert(0, roma[p - 1]).insert(1, roma[p]);
			} else if (number > 0) {
				while (number > 0) {
					sb.insert(0, roma[p - 1]);
					number--;
				}
			}
			num /= 10;
		}
		return sb.toString();
	}

	/**
	 * @author ZhouJie
	 * @date 2020年2月3日 下午6:20:11 
	 * @Description: TODO(方法简述) 
	 * @return String 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 下午6:20:11]  
	 * @UpdateRemark:2-制定对照表  
	 *
	 */
	public String intToRoman_2(int num) {
		if (num < 1 || num > 3999) {
			return "";
		}
		int[] number = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] romans = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < number.length; i++) {
			while (num >= number[i]) {
				sb.append(romans[i]);
				num -= number[i];
			}
		}
		return sb.toString();
	}
}