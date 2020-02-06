package leetcode;

/**
 * @author ZhouJie
 * @date 2019年12月10日 下午4:20:24 
 * @Description: 6. Z 字形变换
 *
	将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
	
	比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
	
	L   C   I   R
	E T O E S I I G
	E   D   H   N
	之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
	
	请你实现这个将字符串进行指定行数变换的函数：
	
	string convert(string s, int numRows);
	示例 1:
	
	输入: s = "LEETCODEISHIRING", numRows = 3
	输出: "LCIRETOESIIGEDHN"
	示例 2:
	
	输入: s = "LEETCODEISHIRING", numRows = 4
	输出: "LDREOEIIECIHNTSG"
	解释:
	
	L     D     R
	E   O E   I I
	E C   I H   N
	T     S     G
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/zigzag-conversion
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-计算每行的规律，顺序处理即可；
		2-优化后的遍历；（来自LeetCode）
 */
public class LeetCode_0006 {

}

class Solution_0006 {
	/**
	 * @author ZhouJie
	 * @date 2019年12月10日 下午4:36:38 
	 * @Description: TODO(方法简述) 
	 * @return String 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2019年12月10日 下午4:36:38]  
	 * @UpdateRemark:1-计算每行规律，拼接字符串
	 *
	 */
	public String convert_1(String s, int numRows) {
		int len = 0;
		if (s == null || numRows == 1 || (len = s.length()) <= numRows) {
			return s;
		}
		char[] cs = s.toCharArray();
		// 关键：每行的偏移量
		int move = 2 * numRows - 2;
		StringBuilder sb = new StringBuilder();
		// i为行号
		for (int i = 0; i < numRows; i++) {
			int next = i;
			// k是该行的第几个字符，0视为第一个对应i行首个字符
			int k = 0;
			while (next < len) {
				// 首行和尾行每次只取一个值
				if (i == 0 || i == numRows - 1) {
					sb.append(cs[next]);
					k++;
					next = move * k + i;
				} else {
					// 中间行每次要多取一个Z字折线上的值，x为竖线上的值，y为折线上的值，边界判断在竖线x处进行，因为x一旦越界，则说明已取完该行最后一个字符
					int x = move * k + i;
					if (x < len) {
						sb.append(cs[x]);
					} else {
						break;
					}
					k++;
					int y = move * k - i;
					if (y < len) {
						sb.append(cs[y]);
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * @author ZhouJie
	 * @date 2020年2月3日 下午5:19:46 
	 * @Description: TODO(方法简述) 
	 * @return String 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 下午5:19:46]  
	 * @UpdateRemark:2-对1的优化，只有首末行每次只取一个值，中间行每次取两个值，且相对步长add是固定的两个可计算的差值
	 *
	 */
	public String convert_2(String s, int numRows) {
		int len = 0;
		if (s == null || numRows == 1 || (len = s.length()) <= numRows) {
			return s;
		}
		// 结果字符数组
		char[] c = new char[len];
		// 绝对步长
		int step = 2 * numRows - 2;
		// 字符下标
		int k = 0;
		int index, add;
		for (int i = 0; i < numRows; i++) {
			// 当前行起始位置
			index = i;
			// 当前行的相对步长
			add = 2 * i;
			while (index < len) {
				c[k++] = s.charAt(index);
				add = step - add;
				index += (add == 0 ? step : add);
			}
		}
		return new String(c);
	}
}
