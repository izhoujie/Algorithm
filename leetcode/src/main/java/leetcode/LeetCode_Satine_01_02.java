package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月12日 上午11:11:24 
 * @Description: 面试题 01.02. 判定是否互为字符重排
 *
	给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
	
	示例 1：
	
	输入: s1 = "abc", s2 = "bca"
	输出: true 
	示例 2：
	
	输入: s1 = "abc", s2 = "bad"
	输出: false
	说明：
	
	0 <= len(s1) <= 100
	0 <= len(s2) <= 100
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/check-permutation-lcci
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Satine_01_02 {

}

class Solution_Satine_01_02 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月12日 上午11:11:56 
	 * @param: @param s1
	 * @param: @param s2
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-转ascii为int数组统计；
	 *
	 */
	public boolean CheckPermutation_1(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		int[] ascii = new int[256];
		for (int i = 0; i < s1.length(); i++) {
			// 统计先加后减
			ascii[s1.charAt(i)]++;
			ascii[s2.charAt(i)]--;
		}
		// 只要有非0的则不可重排转换
		for (int val : ascii) {
			if (val > 0) {
				return false;
			}
		}
		return true;
	}
}