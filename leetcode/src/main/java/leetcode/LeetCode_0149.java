package leetcode;

import java.util.HashMap;

/**
 * @author ZhouJie
 * @date 2019年12月2日 上午12:25:56 
 * @Description: 149. 直线上最多的点数
 *
	给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
	
	示例 1:
	
	输入: [[1,1],[2,2],[3,3]]
	输出: 3
	解释:
	^
	|
	|        o
	|     o
	|  o  
	+------------->
	0  1  2  3  4
	示例 2:
	
	输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
	输出: 4
	解释:
	^
	|
	|  o
	|     o        o
	|        o
	|  o        o
	+------------------->
	0  1  2  3  4  5  6
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/max-points-on-a-line
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-实际为计算斜率分布问题，要注意的是斜率可能带为浮点型，故Java中不能直接用double类型，可以用转为dy-dx的字符串作为map的key
		2-仍计算斜率，但省掉map和转化斜率为字符串，转为计算dy1*dx1是否等于dy2*dx2
 */
public class LeetCode_0149 {

}

/**
 * @author ZhouJie
 * @date 2019年12月2日 上午12:29:51 
 * @Description: 计算斜率 
 *
 */
class Solution_0149 {
	/**
	 * @author ZhouJie
	 * @date 2020年1月2日 上午12:43:54 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月2日 上午12:43:54]  
	 * @UpdateRemark:直接统计斜率 
	 *
	 */
	public int maxPoints_1(int[][] points) {
		int len = 0;
		if (points == null || (len = points.length) < 3) {
			return len;
		}
		int max = 0;
		for (int i = 0; i < len - 1; i++) {
			HashMap<String, Integer> kMap = new HashMap<String, Integer>();
			int repeat = 0;
			int fakeMax = 0;
			for (int j = i + 1; j < len; j++) {
				int dy = points[j][1] - points[i][1];
				int dx = points[j][0] - points[i][0];

				if (dy == 0 && dx == 0) {
					repeat++;
					continue;
				}
				int g = gcd(dy, dx);
				if (g != 0) {
					dy /= g;
					dx /= g;
				}
				String k = String.valueOf(dy) + "-" + String.valueOf(dx);
				kMap.put(k, kMap.getOrDefault(k, 0) + 1);
				fakeMax = Math.max(fakeMax, kMap.get(k));
			}
			max = Math.max(max, fakeMax + 1 + repeat);
		}
		return max;
	}

	private int gcd(int dy, int dx) {
		return dx == 0 ? dy : gcd(dx, dy % dx);
	}

	/**
	 * @author ZhouJie
	 * @date 2019年12月2日 上午1:49:57 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月2日 上午12:43:14]  
	 * @UpdateRemark:计算校验dy1*dx1==dy2*dx2
	 *
	 */
	public int maxPoints_2(int[][] points) {
		int len = 0;
		if (points == null || (len = points.length) < 3) {
			return len;
		}
		int max = 0;
		for (int i = 0; i < len - 1; i++) {
			long x1 = points[i][0];
			long y1 = points[i][1];
			for (int j = i + 1; j < len; j++) {
				long x2 = points[j][0];
				long y2 = points[j][1];
				if (x1 == x2 && y1 == y2) {
					x2++;
					y2++;
				}
				int fakeMax = 0;
				for (int k = 0; k < len; k++) {
					long x = points[k][0];
					long y = points[k][1];
					if ((y - y1) * (x2 - x1) == (y2 - y1) * (x - x1)) {
						fakeMax++;
					}
				}
				max = Math.max(max, fakeMax);
			}
		}
		return max;
	}
}
