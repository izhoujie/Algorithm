package leetcode;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author ZhouJie
 * @date 2020年4月30日 下午10:24:08 
 * @Description: 202. 快乐数
 *
	编写一个算法来判断一个数 n 是不是快乐数。
	
	「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
	
	如果 n 是快乐数就返回 True ；不是，则返回 False 。
	
	 
	
	示例：
	
	输入：19
	输出：true
	解释：
	12 + 92 = 82
	82 + 22 = 68
	62 + 82 = 100
	12 + 02 + 02 = 1
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/happy-number
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0202 {

}

class Solution_0202 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月30日 下午10:53:37 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: 快乐数计算
	 *
	 */
	private int happyHelper(int n) {
		int temp = 0;
		while (n > 0) {
			temp += (n % 10) * (n % 10);
			n /= 10;
		}
		return temp;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月30日 下午10:24:31 
	 * @param: @param n
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-每次计算后，若为1则是快乐数，否则看是否在set，在则不是快乐数，不在则继续循环计算；（循环有限的，所以代码最后的return其实永远不会执行）
	 *
	 */
	public boolean isHappy_1(int n) {
		HashSet<Integer> noHappy = new HashSet<Integer>();
		while (n != 1 && !noHappy.contains(n)) {
			noHappy.add(n);
			n = happyHelper(n);
		}
		return n == 1;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月30日 下午10:36:06 
	 * @param: @param n
	 * @param: @return
	 * @return: boolean
	 * @Description: 2-快慢指针；原理：若为1退出循环；若有环，则快指针最终会追上慢指针（多一个循环）；
	 *
	 */
	public boolean isHappy_2(int n) {
		int slow = n, fast = happyHelper(n);
		while (slow != fast) {
			if (slow == 1 || fast == 1) {
				return true;
			} else {
				slow = happyHelper(slow);
				fast = happyHelper(happyHelper(fast));
			}
		}
		return slow == 1;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月30日 下午10:45:26 
	 * @param: @param n
	 * @param: @return
	 * @return: boolean
	 * @Description: 3-数学规律，任意数最终会进入两个循环，一个是：1自身循环，一个是： 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4
	 *
	 */
	public boolean isHappy_3(int n) {
		// 建立不快乐数的最终循环自查表
		HashSet<Integer> noHappy = new HashSet<Integer>(Arrays.asList(4, 16, 37, 58, 89, 145, 42, 20));
		while (n != 1 && !noHappy.contains(n)) {
			n = happyHelper(n);
		}
		return n == 1;
	}
}