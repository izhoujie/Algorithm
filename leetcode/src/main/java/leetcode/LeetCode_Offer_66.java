package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月14日 下午10:23:21 
 * @Description: 面试题66. 构建乘积数组
 *
	给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
	
	示例:
	
	输入: [1,2,3,4,5]
	输出: [120,60,40,30,24]
	 
	
	提示：
	
	所有元素乘积之和不会溢出 32 位整数
	a.length <= 100000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_66 {

}

class Solution_Offer_66 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月14日 下午10:23:49 
	 * @param: @param a
	 * @param: @return
	 * @return: int[]
	 * @Description: 1-错位连乘两遍即可；
	 *
	 */
	public int[] constructArr(int[] a) {
		if (a.length < 2) {
			return a;
		}
		int len = a.length;
		int[] b = new int[len];
		int c = 1;
		for (int i = 0; i < len; i++) {
			b[i] = c;
			c *= a[i];
		}
		c = 1;
		for (int i = len - 1; i >= 0; i--) {
			b[i] *= c;
			c *= a[i];
		}
		return b;
	}
}