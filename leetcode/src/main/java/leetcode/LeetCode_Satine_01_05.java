package leetcode;

/**
 * @author ZhouJie
 * @date 2020-7-6 0:31:36 
 * @Description: 面试题 01.05. 一次编辑
 *
	字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。 
	
	示例 1:
	
	输入: 
	first = "pale"
	second = "ple"
	输出: True
	 
	示例 2:
	
	输入: 
	first = "pales"
	second = "pal"
	输出: False
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/one-away-lcci
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Satine_01_05 {
	/**
	 * @author: ZhouJie
	 * @date: 2020-7-6 0:32:45 
	 * @param: @param first
	 * @param: @param second
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-记录是否编辑过；
	 *
	 */
	public boolean oneEditAway_1(String first, String second) {
		if (first == null && second == null) {
			return true;
		}
		int len1 = first.length();
		int len2 = second.length();
		int t = len1 - len2;
		if (t > 1 || t < -1) {
			return false;
		}
		int i = 0, j = 0;
		// 只有一次机会
		boolean onceChance = true;
		while (i < len1 && j < len2) {
			if (first.charAt(i) != second.charAt(j)) {
				if (onceChance) {
					// first较长
					if (t == 1) {
						j--;
						// second较长
					} else if (t == -1) {
						i--;
					}
					onceChance = !onceChance;
				} else {
					return onceChance;
				}
			}
			i++;
			j++;
		}
		return true;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020-7-6 1:51:04 
	 * @param: @param first
	 * @param: @param second
	 * @param: @return
	 * @return: boolean
	 * @Description: 2-两侧夹逼校验最终不等部分长度差；
	 *
	 */
	public boolean oneEditAway_2(String first, String second) {
		if (first == null && second == null) {
			return true;
		}
		int len1 = first.length();
		int len2 = second.length();
		int t = len1 - len2;
		if (t > 1 || t < -1) {
			return false;
		}
		int k = 0;
		while (k < len1 && k < len2 && first.charAt(k) == second.charAt(k)) {
			k++;
		}
		len1--;
		len2--;
		while (len1 >= k && len2 >= k && first.charAt(len1) == second.charAt(len2)) {
			len1--;
			len2--;
		}
		return (len1 - k) < 1 && (len2 - k) < 1;
	}

}
