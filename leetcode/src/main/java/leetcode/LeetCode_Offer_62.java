package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年3月30日 下午9:18:03 
 * @Description: 面试题62. 圆圈中最后剩下的数字
 *
	0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
	
	例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
	
	 
	
	示例 1：
	
	输入: n = 5, m = 3
	输出: 3
	示例 2：
	
	输入: n = 10, m = 17
	输出: 2
	 
	
	限制：
	
	1 <= n <= 10^5
	1 <= m <= 10^6
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_62 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月30日 下午9:17:57 
	 * @param: @param n
	 * @param: @param m
	 * @param: @return
	 * @return: int
	 * @Description: 1-模拟删除过程
	 *
	 */
	public int lastRemaining_1(int n, int m) {
		List<Integer> all = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			all.add(i);
		}
		int winner = 0;
		while (n > 1) {
			// 每次删除第m个位置的数，对应索引为m-1，直至n为1，即只剩一个数
			winner = (winner + m - 1) % n;
			all.remove(winner);
			n--;
		}
		return all.get(0);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月30日 下午9:34:57 
	 * @param: @param n
	 * @param: @param m
	 * @param: @return
	 * @return: int
	 * @Description: 2-本题为约瑟夫环类问题，数学公式解决；
	 *
	 */
	public int lastRemaining_2(int n, int m) {
		int winner = 0;
		// 从剩余两个数的时候反推到n个数
		for (int i = 2; i <= n; i++) {
			winner = (winner + m) % i;
		}
		return winner;
	}

}
