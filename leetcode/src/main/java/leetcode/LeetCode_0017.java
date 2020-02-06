package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年1月5日 下午10:40:10 
 * @Description: 17. 电话号码的字母组合
 *
	给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
	
	给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
	
	
	
	示例:
	
	输入："23"
	输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
	说明:
	尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-按键2-9转为对应的0-7字符数组，参数转为对应的0-7之间的int数组，然后递归全排列；
 */
public class LeetCode_0017 {
	public static void main(String[] args) {
		System.out.println(new Solution_0017().letterCombinations("23"));
	}

}

class Solution_0017 {
	public List<String> letterCombinations(String digits) {
		String[] numbers = new String[] { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		List<String> list = new ArrayList<String>();
		int len;
		if (digits == null || (len = digits.length()) == 0) {
			return list;
		}
		int[] d = convertToNumber(digits);
		char[] cs = new char[len];
		next(numbers, d, 0, len - 1, cs, list);

		return list;
	}

	/**
	 * @author ZhouJie
	 * @date 2020年1月5日 下午11:10:03 
	 * @Description: TODO(方法简述) 
	 * @return void 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月5日 下午11:10:03]  
	 * @UpdateRemark:递归组合
	 *
	 */
	private void next(String[] numbers, int[] d, int start, int end, char[] cs, List<String> list) {
		int k = d[start];
		for (int i = 0; i < numbers[k].length(); i++) {
			// 第start位置对应键盘数字的字母字符串
			cs[start] = numbers[k].charAt(i);
			if (start == end) {
				list.add(String.valueOf(cs));
			} else {
				next(numbers, d, start + 1, end, cs, list);
			}
		}
	}

	/**
	 * @author ZhouJie
	 * @date 2020年1月5日 下午11:09:10 
	 * @Description: TODO(方法简述) 
	 * @return int[] 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月5日 下午11:09:10]  
	 * @UpdateRemark:辅助函数，将digits转为0-7的数组对应按键的2-9
	 *
	 */
	private int[] convertToNumber(String digits) {
		int[] d = new int[digits.length()];
		for (int i = 0; i < digits.length(); i++) {
			d[i] = digits.charAt(i) - '2';
		}
		return d;
	}
}
