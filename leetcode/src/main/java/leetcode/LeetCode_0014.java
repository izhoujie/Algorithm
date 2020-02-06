package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月2日 下午11:36:04 
 * @Description: 14. 最长公共前缀
 *
	编写一个函数来查找字符串数组中的最长公共前缀。
	
	如果不存在公共前缀，返回空字符串 ""。
	
	示例 1:
	
	输入: ["flower","flow","flight"]
	输出: "fl"
	示例 2:
	
	输入: ["dog","racecar","car"]
	输出: ""
	解释: 输入不存在公共前缀。
	说明:
	
	所有输入只包含小写字母 a-z 。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/longest-common-prefix
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-若wantedStr就是最长公共前缀，则strs[i]均会返回0，否则wantedStr长度减1再试；
 */
public class LeetCode_0014 {

}

class Solution_0014 {
	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		String wantedString = strs[0];
		for (int i = 1; i < strs.length; i++) {
			// 若wantedString是最长公共前缀，则必为0，否则其从尾部减1再测
			while (strs[i].indexOf(wantedString) != 0) {
				wantedString = wantedString.substring(0, wantedString.length() - 1);
				if (wantedString.isEmpty()) {
					return "";
				}
			}
		}
		return wantedString;
	}
}