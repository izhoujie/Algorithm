package leetcode;

import java.util.HashMap;

/**
 * @author ZhouJie
 * @date 2020年4月29日 下午8:00:45 
 * @Description: 1095. 山脉数组中查找目标值
 *
	（这是一个 交互式问题 ）
	
	给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。
	
	如果不存在这样的下标 index，就请返回 -1。
	
	 
	
	何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
	
	首先，A.length >= 3
	
	其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
	
	A[0] < A[1] < ... A[i-1] < A[i]
	A[i] > A[i+1] > ... > A[A.length - 1]
	 
	
	你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
	
	MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
	MountainArray.length() - 会返回该数组的长度
	 
	
	注意：
	
	对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。
	
	为了帮助大家更好地理解交互式问题，我们准备了一个样例 “答案”：https://leetcode-cn.com/playground/RKhe3ave，请注意这 不是一个正确答案。
	
	 
	
	示例 1：
	
	输入：array = [1,2,3,4,5,3,1], target = 3
	输出：2
	解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。
	示例 2：
	
	输入：array = [0,1,2,4,2,1], target = 3
	输出：-1
	解释：3 在数组中没有出现，返回 -1。
	 
	
	提示：
	
	3 <= mountain_arr.length() <= 10000
	0 <= target <= 10^9
	0 <= mountain_arr.get(index) <= 10^9
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/find-in-mountain-array
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_1095 {

}

// This is MountainArray's API interface.
// You should not implement it, or speculate about its implementation
interface MountainArray {
	public int get(int index);

	public int length();
}

class Solution_1095 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月29日 下午8:02:01 
	 * @param: @param target
	 * @param: @param mountainArr
	 * @param: @return
	 * @return: int
	 * @Description: 1-二分查找，先找峰值，再找左右；
	 *
	 */
	private static HashMap<Integer, Integer> CACHE;
	private static MountainArray M;

	public int findInMountainArray_1(int target, MountainArray mountainArr) {
		M = mountainArr;
		CACHE = new HashMap<Integer, Integer>();
		int len = mountainArr.length();
		int left = 0, right = len - 1, mid;
		while (left < right) {
			mid = (left + right) >> 1;
			if (getFromCache(mid) < getFromCache(mid + 1)) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		mid = left;
		if (getFromCache(mid) == target) {
			return mid;
		}
		// 先查左半边，再查右半边
		int index = bingarySearch(0, mid - 1, target, true);
		if (index != -1) {
			return index;
		}
		index = bingarySearch(mid + 1, len - 1, target, false);
		return index == -1 ? -1 : index;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月29日 下午8:41:02 
	 * @param: @param left
	 * @param: @param right
	 * @param: @param target
	 * @param: @param f
	 * @param: @return
	 * @return: int
	 * @Description: 二分查找
	 *
	 */
	private int bingarySearch(int left, int right, int target, boolean f) {
		while (left < right) {
			int mid = (left + right) >> 1;
			int midVal = getFromCache(mid);
			if (f ? midVal < target : midVal > target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return getFromCache(left) == target ? left : -1;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月29日 下午8:40:50 
	 * @param: @param index
	 * @param: @return
	 * @return: int
	 * @Description: 缓存获取过的值
	 *
	 */
	private static int getFromCache(int index) {
		Integer val = CACHE.get(index);
		if (val == null) {
			val = M.get(index);
			CACHE.put(index, val);
		}
		return val;
	}
}