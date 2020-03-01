package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月1日 下午4:01:07 
 * @Description: 67. 二进制求和
 *
	给定两个二进制字符串，返回他们的和（用二进制表示）。
	
	输入为非空字符串且只包含数字 1 和 0。
	
	示例 1:
	
	输入: a = "11", b = "1"
	输出: "100"
	示例 2:
	
	输入: a = "1010", b = "1011"
	输出: "10101"
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/add-binary
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0067 {

}

class Solution_0067 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月1日 下午4:56:06 
	 * @param: @param a
	 * @param: @param b
	 * @param: @return
	 * @return: String
	 * @Description: 1-顺序取对应位计算，使用StringBuilder;
	 *
	 */
	public String addBinary_1(String a, String b) {
		if (a.length() < b.length()) {
			return addBinary_1(b, a);
		}
		int len1 = a.length(), len2 = b.length(), carry = 0;
		StringBuilder sb = new StringBuilder(len1 + 1);
		while (--len2 > -1) {
			carry += a.charAt(--len1) - '0' + b.charAt(len2) - '0';
			sb.append(carry % 2);
			carry /= 2;
		}
		while (--len1 > -1) {
			if (carry == 0) {
				sb.reverse().insert(0, a.substring(0, len1 + 1));
				return sb.toString();
			}
			carry += a.charAt(len1) - '0';
			sb.append(carry % 2);
			carry /= 2;
		}
		return carry == 1 ? sb.append(carry).reverse().toString() : sb.reverse().toString();
	}
}