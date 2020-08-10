package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020-8-10 15:09:24 
 * @Description: 93. 复原IP地址
 *
	给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
	
	有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
	
	示例:
	
	输入: "25525511135"
	输出: ["255.255.11.135", "255.255.111.35"]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/restore-ip-addresses
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0093 {

}

class Solution_0093 {
	/**
	 * @author: ZhouJie
	 * @date: 2020-8-10 17:01:33 
	 * @param: @param s
	 * @param: @return
	 * @return: List<String>
	 * @Description: 1-ipv4只有4段，直接三段for循环即可解决，且每段的判断逻辑相同；
	 *
	 */
	public List<String> restoreIpAddresses(String s) {
		List<String> list = new ArrayList<String>();
		int len;
		if (s == null || (len = s.length()) < 4 || len > 12) {
			return list;
		}
		int n;
		// 每段都有四个判断：
		// 1-剩余长度大于ip剩余长度，则当前循环内continue；
		// 2-没有剩余长度，直接break；
		// 3-非单个0且有前导0的，break；
		// 4-转int后大于255的，break；
		for (int i = 0; i < 3; i++) {
			n = len - i - 1;
			if (n > 9) {
				continue;
			}
			if (n < 1 || myCheck(s, 0, i + 1)) {
				break;
			}
			for (int j = i + 1; j < 3 + i + 1; j++) {
				n = len - j - 1;
				if (n > 6) {
					continue;
				}
				if (n < 1 || myCheck(s, i + 1, j + 1)) {
					break;
				}
				for (int k = j + 1; k < 3 + j + 1; k++) {
					n = len - k - 1;
					if (n > 3) {
						continue;
					}
					if (n < 1 || myCheck(s, j + 1, k + 1)) {
						break;
					}
					if (myCheck(s, k + 1, len)) {
						continue;
					}
					list.add(new StringBuffer(s).insert(i + 1, ".").insert(j + 2, ".").insert(k + 3, ".").toString());
				}
			}
		}
		return list;
	}

	private boolean myCheck(String s, int i, int j) {
		s = s.substring(i, j);
		return s.startsWith("0") && j - i > 1 || Integer.parseInt(s) > 255;
	}
}