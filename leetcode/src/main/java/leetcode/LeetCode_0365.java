package leetcode;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author ZhouJie
 * @date 2020年3月21日 下午1:59:07 
 * @Description: 365. 水壶问题
 *
	有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
	
	如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
	
	你允许：
	
	装满任意一个水壶
	清空任意一个水壶
	从一个水壶向另外一个水壶倒水，直到装满或者倒空
	示例 1: (From the famous "Die Hard" example)
	
	输入: x = 3, y = 5, z = 4
	输出: True
	示例 2:
	
	输入: x = 2, y = 6, z = 5
	输出: False
 */
public class LeetCode_0365 {
}

class Solution_0365 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月21日 下午2:39:59 
	 * @param: @param x
	 * @param: @param y
	 * @param: @param z
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-除去xy本身，x y互相加减及递归一次的加减值组成的set，
	 * 				若set总数大于4说明可以生成1~x+y之间的任意z数，否则z只能是set的某两个散列数的组合
	 * 				::这个无法证明...但是算法通过了，虽然效率不高。
	 *
	 */
	public boolean canMeasureWater_1(int x, int y, int z) {
		if (z == 0 || z == x || z == y) {
			return true;
		}
		if (z > (x + y)) {
			return false;
		}
		TreeSet<Integer> treeSet = new TreeSet<Integer>();
		int a = Math.abs(x + y);
		int b = Math.abs(x - y);
		int c = Math.abs(x - b);
		int d = Math.abs(y - b);
		treeSet.add(a);
		treeSet.add(b);
		treeSet.add(c);
		treeSet.add(d);
		treeSet.add(x);
		treeSet.add(y);
		treeSet.remove(0);
		int min = treeSet.first();
		int size = treeSet.size();
		if (size > 4) {
			return true;
		} else if (z < min) {
			return false;
		} else {
			Iterator<Integer> ite = treeSet.iterator();
			while (ite.hasNext()) {
				if (treeSet.contains(z - ite.next().intValue())) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月21日 下午3:16:54 
	 * @param: @param x
	 * @param: @param y
	 * @param: @param z
	 * @param: @return
	 * @return: boolean
	 * @Description: 2-贝祖定理+GCD
	 *
	 */
	public boolean canMeasureWater_2(int x, int y, int z) {
		if (z == 0 || z == x || z == y) {
			return true;
		} else if (z > (x + y)) {
			return false;
		} else {
			return z % gcd(x, y) == 0;
		}
	}

	private int gcd(int x, int y) {
		return y == 0 ? x : gcd(y, x % y);
	}
}
