package leetcode;

/**
 * @author ZhouJie
 * @date 2019年12月13日 下午2:50:38 
 * @Description: 1281. 整数的各位积和之差
 *
	给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
	
	 
	
	示例 1：
	
	输入：n = 234
	输出：15 
	解释：
	各位数之积 = 2 * 3 * 4 = 24 
	各位数之和 = 2 + 3 + 4 = 9 
	结果 = 24 - 9 = 15
	示例 2：
	
	输入：n = 4421
	输出：21
	解释： 
	各位数之积 = 4 * 4 * 2 * 1 = 32 
	各位数之和 = 4 + 4 + 2 + 1 = 11 
	结果 = 32 - 11 = 21
	 
	
	提示：
	
	1 <= n <= 10^5
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-逐位取模计算和与积，最后求差值；
 */
public class LeetCode_1281 {

}

class Solution_1281 {
	public int subtractProductAndSum(int n) {
		int sum = 0;
		int mulit = 1;
		int temp = 0;
		while (n > 0) {
			temp = n % 10;
			mulit *= temp;
			sum += temp;
			n /= 10;
		}
		return mulit - sum;
	}
}
