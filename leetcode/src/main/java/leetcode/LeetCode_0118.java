package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年3月1日 下午9:30:54 
 * @Description: 118. 杨辉三角
 *
	给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
	
	
	
	在杨辉三角中，每个数是它左上方和右上方的数的和。
	
	示例:
	
	输入: 5
	输出:
	[
	     [1],
	    [1,1],
	   [1,2,1],
	  [1,3,3,1],
	 [1,4,6,4,1]
	]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/pascals-triangle
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-逐行规律求解；
		2-单个list自我求解；
 */
public class LeetCode_0118 {

}

class Solution_0118 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月1日 下午10:26:07 
	 * @param: @param numRows
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 1-
	 *
	 */
	public List<List<Integer>> generate_1(int numRows) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if (numRows == 0) {
			return list;
		}
		// 第一行
		List<Integer> l = new ArrayList<Integer>();
		l.add(1);
		list.add(l);
		for (int i = 1; i < numRows; i++) {
			// 取上一行的list
			List<Integer> li = list.get(i - 1);
			List<Integer> ll = new ArrayList<Integer>(i + 1);
			for (int j = 0; j < i + 1; j++) {
				ll.add(1);
			}
			for (int j = 1; j <= i - j; j++) {
				// 根据上一行的值计算当前行的值，只求前半段
				int val = li.get(j) + li.get(j - 1);
				ll.set(j, val);
				ll.set(i - j, val);
			}
			list.add(ll);
		}
		return list;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月1日 下午10:49:31 
	 * @param: @param numRows
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 2-
	 *
	 */
	public List<List<Integer>> generate_2(int numRows) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if (numRows == 0) {
			return list;
		}
		List<Integer> model = new ArrayList<Integer>();
		for (int i = 0; i < numRows; i++) {
			model.add(1);
			for (int j = i - 1; j > 0; j--) {
				model.set(j, model.get(j) + model.get(j - 1));
			}
			list.add(new ArrayList<>(model));
		}
		return list;
	}
}