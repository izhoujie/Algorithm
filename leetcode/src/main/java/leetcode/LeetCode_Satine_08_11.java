package leetcode;

import java.util.Arrays;

/**
 * @author ZhouJie
 * @date 2020年4月23日 下午9:56:54 
 * @Description: 面试题 08.11. 硬币
 *
	硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
	
	示例1:
	
	 输入: n = 5
	 输出：2
	 解释: 有两种方式可以凑成总金额:
	5=5
	5=1+1+1+1+1
	示例2:
	
	 输入: n = 10
	 输出：4
	 解释: 有四种方式可以凑成总金额:
	10=10
	10=5+5
	10=5+1+1+1+1+1
	10=1+1+1+1+1+1+1+1+1+1
	说明：
	
	注意:
	
	你可以假设：
	
	0 <= n (总金额) <= 1000000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/coin-lcci
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Satine_08_11 {

}

class Solution_08_11 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月23日 下午9:57:20 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: 1-动态规划dp;
	 *
	 */
	public int waysToChange_1(int n) {
		int mod = 1000000007;
		n /= 5;
		int[] dp = new int[n + 1];
		Arrays.fill(dp, 1);
		int[] coin = new int[] { 1, 2, 5 };
		for (int i : coin) {
			for (int j = i; j <= n; j++) {
				dp[j] = (dp[j] + dp[j - i]) % mod;
			}
		}
		return dp[n];
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月23日 下午10:24:09 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: 2-数学规律计算，
	 *
	 */
	public int waysToChange_2(int n) {
		int mod = 1000000007;
		long count = 0;
		for (int i = 0; i * 25 <= n; i++) {
			// 取i个25分的硬币后剩余remain
			int remain = n - i * 25;
			// remain中最多取a个10分硬币
			long a = remain / 10;
			// remain中最多取b个5分硬币
			long b = remain % 10 / 5;
			// 公式...
			count = (count + (a + 1) * (a + b + 1) % mod) % mod;
		}
		return (int) count;
	}

}