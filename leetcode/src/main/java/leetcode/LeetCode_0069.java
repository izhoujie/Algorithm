package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月1日 下午5:11:36 
 * @Description: 69. x 的平方根
 *
	实现 int sqrt(int x) 函数。
	
	计算并返回 x 的平方根，其中 x 是非负整数。
	
	由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
	
	示例 1:
	
	输入: 4
	输出: 2
	示例 2:
	
	输入: 8
	输出: 2
	说明: 8 的平方根是 2.82842..., 
	     由于返回类型是整数，小数部分将被舍去。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/sqrtx
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0069 {
}

class Solution_0069 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月1日 下午5:33:13 
	 * @param: @param x
	 * @param: @return
	 * @return: int
	 * @Description: 1-直接顺序计算校验（可二分法提升速度）；
	 *
	 */
	public int mySqrt_1(int x) {
		if (x < 2) {
			return x;
		}
		int k = x / 2;
		for (int i = 1; i < k + 1; i++) {
			if (i * i > x || i * i < 0) {
				return i - 1;
			} else if (i * i == x) {
				return i;
			}
		}
		return k;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月1日 下午5:33:36 
	 * @param: @param x
	 * @param: @return
	 * @return: int
	 * @Description: 2-牛顿法；
	 *
	 */
	public int mySqrt_2(int x) {
		long y = x / 2 + 1;
		while (y * y > x) {
			y = (y + x / y) / 2;
		}
		return (int) y;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月1日 下午5:57:44 
	 * @param: @param x
	 * @param: @return
	 * @return: int
	 * @Description: 3-二分法；
	 *
	 */
	public int mySqrt_3(int x) {
		if (x < 2) {
			return x;
		}
		long left = 1, right = x / 2, mid = 1;
		while (left <= right) {
			mid = left + (right - left) / 2;
			long t = mid * mid;
			if (t > x) {
				right = mid - 1;
			} else if (t < x) {
				left = mid + 1;
			} else {
				return (int) t;
			}
		}
		return (int) right;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月1日 下午5:58:56 
	 * @param: @param x
	 * @param: @return
	 * @return: int
	 * @Description: 4-位移递归；（来自LeetCode官方，还没看懂）
	 *
	 */
	public int mySqrt_4(int x) {
		if (x < 2) {
			return x;
		}
		int left = mySqrt_4(x >> 2) << 1;
		int right = left + 1;
		// 必须先把一个right转为long 而不是转其乘积，乘积可能溢出变为负数
		return (long) right * right > x ? left : right;
	}
}
