package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月11日 上午1:37:40 
 * @Description: 1013. 将数组分成和相等的三个部分
 *
	给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
	
	形式上，如果可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 就可以将数组三等分。
	
	 
	
	示例 1：
	
	输出：[0,2,1,-6,6,-7,9,1,2,0,1]
	输出：true
	解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
	示例 2：
	
	输入：[0,2,1,-6,6,7,9,-1,2,0,1]
	输出：false
	示例 3：
	
	输入：[3,3,6,5,-2,2,5,1,-9,4]
	输出：true
	解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
	 
	
	提示：
	
	3 <= A.length <= 50000
	-10^4 <= A[i] <= 10^4
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-先求总和是否能被3整除，然后左右指针向中间累加求两遍和是否等于和的1/3；
 */
public class LeetCode_1013 {

}

class Solution_1013 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月11日 上午1:39:19 
	 * @param: @param A
	 * @param: @return
	 * @return: boolean
	 * @Description: TODO
	 *
	 */
	public boolean canThreePartsEqualSum(int[] A) {
		if (A == null || A.length < 2) {
			return false;
		}
		int sum = 0;
		for (int k : A) {
			sum += k;
		}
		if (sum % 3 != 0) {
			return false;
		}
		int avg = sum / 3;
		int i = 0, j = A.length - 1;
		int sumI = A[i], sumJ = A[j];
		// 找左半边等于part
		while (i < j && sumI != avg) {
			sumI += A[++i];
		}
		// j至少要比i大2，即中间至少要留一个数，否则说明不能平分，下同
		if (j - i < 2) {
			return false;
		}
		// 找右半边等于part
		while (i < j && sumJ != avg) {
			sumJ += A[--j];
		}
		if (j - i < 2) {
			return false;
		}
		return true;
	}

}
