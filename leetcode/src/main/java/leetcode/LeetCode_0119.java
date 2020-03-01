package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年3月1日 下午10:51:12 
 * @Description: 119. 杨辉三角 II
 *
	给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
	
	
	
	在杨辉三角中，每个数是它左上方和右上方的数的和。
	
	示例:
	
	输入: 3
	输出: [1,3,3,1]
	进阶：
	
	你可以优化你的算法到 O(k) 空间复杂度吗？
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/pascals-triangle-ii
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-动态规划；
		2-公式1；
		3-公式2；
 */
public class LeetCode_0119 {

}

class Solution_0119 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月1日 下午11:01:10 
	 * @param: @param rowIndex
	 * @param: @return
	 * @return: List<Integer>
	 * @Description: 1-动态规划；
	 *
	 */
	public List<Integer> getRow_1(int rowIndex) {
		Integer[] dp = new Integer[rowIndex + 1];
		// 填充默认值
		Arrays.fill(dp, 1);
		for (int i = 2; i < dp.length; i++) {
			for (int j = i - 1; j > 0; j--) {
				dp[j] = dp[j] + dp[j - 1];
			}
		}
		return Arrays.asList(dp);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月1日 下午11:21:10 
	 * @param: @param rowIndex
	 * @param: @return
	 * @return: List<Integer>
	 * @Description: 2-使用公式；
	 * 
	 * 				获取杨辉三角的指定行
	 * 				直接使用组合公式C(n,i) = n!/(i!*(n-i)!)
	 * 				则第(i+1)项是第i项的倍数=(n-i)/(i+1);	
	 * 				第i项：f(i)=f(i-1)*(n-i-1)/(i-1+1)
	 *
	 */
	public List<Integer> getRow_2(int rowIndex) {
		List<Integer> list = new ArrayList<Integer>(rowIndex + 1);
		long pre = 1;
		for (int i = 0; i <= rowIndex; i++) {
			list.add((int) pre);
			pre = pre * (rowIndex - i) / (i + 1);
		}
		return list;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月1日 下午11:37:12 
	 * @param: @param rowIndex
	 * @param: @return
	 * @return: List<Integer>
	 * @Description: 3-公式，另一种写法；
	 *
	 *				第i项：f(i) =f(i-1)*(n-i+1)/k
	 *
	 */
	public List<Integer> getRow_3(int rowIndex) {
		List<Integer> list = new ArrayList<Integer>(rowIndex + 1);
		list.add(1);
		long pre = 1;
		long cur;
		for (int i = 1; i <= rowIndex; i++) {
			cur = pre * (rowIndex - i + 1) / i;
			list.add((int) cur);
			pre = cur;
		}
		return list;
	}

}