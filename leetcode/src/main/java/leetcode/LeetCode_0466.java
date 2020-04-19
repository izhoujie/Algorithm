package leetcode;

/**
 * @author ZhouJie
 * @date 2020年4月19日 下午7:12:10 
 * @Description: 466. 统计重复个数
 *
	由 n 个连接的字符串 s 组成字符串 S，记作 S = [s,n]。例如，["abc",3]=“abcabcabc”。
	
	如果我们可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。例如，根据定义，"abc" 可以从 “abdbec” 获得，但不能从 “acbbe” 获得。
	
	现在给你两个非空字符串 s1 和 s2（每个最多 100 个字符长）和两个整数 0 ≤ n1 ≤ 106 和 1 ≤ n2 ≤ 106。现在考虑字符串 S1 和 S2，其中 S1=[s1,n1] 、S2=[s2,n2] 。
	
	请你找出一个可以满足使[S2,M] 从 S1 获得的最大整数 M 。
	
	 
	
	示例：
	
	输入：
	s1 ="acb",n1 = 4
	s2 ="ab",n2 = 2
	
	返回：
	2
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/count-the-repetitions
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0466 {

}

class Solution_0466 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月19日 下午7:12:31 
	 * @param: @param s1
	 * @param: @param n1
	 * @param: @param s2
	 * @param: @param n2
	 * @param: @return
	 * @return: int
	 * @Description: 1-先尝试寻找循环体，便于计算可拼接的S2数量；
	 *
	 */
	public int getMaxRepetitions_1(String s1, int n1, String s2, int n2) {
		int len1 = s1.length();
		int len2 = s2.length();
		// 特例判断
		if (n1 == 0 || n2 == 0 || n1 * len1 < n2 * len2) {
			return 0;
		}
		// 特例-若s2中字符有不在s1中的直接返回
		char[] cs2 = s2.toCharArray();
		for (char c : cs2) {
			if (s1.indexOf(c) == -1) {
				return 0;
			}
		}
		char[] cs1 = s1.toCharArray();
		// 寻找循环体时s2的下标
		int index = 0;
		int count = 0;
		// 在s2中首次匹配到的字符索引位置
		int firstIndex = 0;
		// 记录在s1的第i次拼接时匹配到的s2的总数
		int[] countRdecoder = new int[n1];
		for (int i = 0; i < n1; i++) {
			for (int j = 0; j < len1; j++) {
				// 这是一个往复匹配，匹配到s1中的s2时就右移index，完全匹配s2时记录count并重置index
				if (cs2[index] == cs1[j]) {
					index++;
					if (index == len2) {
						count++;
						index = 0;
					}
				}
			}
			// 首次匹配完时s1的停止位置
			if (i == 0) {
				firstIndex = index;
			}
			// 截至本次总匹配s2的数量
			countRdecoder[i] = count;
			// 若本次的停止位index与第一次时的停止位相同说明找到了循环体，开始数学计算并返回
			if (i != 0 && index == firstIndex) {
				// 第一部分：找到循环体时循环体内的匹配s1数量乘以n1个s2中有多少个这样的循环体片段(n1 - 1) / i)
				int part1 = ((n1 - 1) / i) * (countRdecoder[i] - countRdecoder[0]);
				// 第二部分：除去第一部分后剩余部分s2拼接起来可匹配s1的数量
				int part2 = countRdecoder[(n1 - 1) % i];
				// 总匹配s1的数量除以n2（n2个s1）即得题目要求的M
				return (part1 + part2) / n2;
			}
		}
		// 若未找到循环体 ，则直接暴力求解，这种情况基本只能是0或1了
		return countRdecoder[n1 - 1] / n2;
	}

}