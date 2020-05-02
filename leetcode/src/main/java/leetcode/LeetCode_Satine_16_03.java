package leetcode;

/**
 * @author ZhouJie
 * @date 2020年4月12日 下午10:26:53 
 * @Description: 面试题 16.03. 交点
 *
	给定两条线段（表示为起点start = {X1, Y1}和终点end = {X2, Y2}），如果它们有交点，请计算其交点，没有交点则返回空值。
	
	要求浮点型误差不超过10^-6。若有多个交点（线段重叠）则返回 X 值最小的点，X 坐标相同则返回 Y 值最小的点。
	
	 
	
	示例 1：
	
	输入：
	line1 = {0, 0}, {1, 0}
	line2 = {1, 1}, {0, -1}
	输出： {0.5, 0}
	示例 2：
	
	输入：
	line1 = {0, 0}, {3, 3}
	line2 = {1, 1}, {2, 2}
	输出： {1, 1}
	示例 3：
	
	输入：
	line1 = {0, 0}, {1, 1}
	line2 = {1, 0}, {2, 1}
	输出： {}，两条线段没有交点
	 
	
	提示：
	
	坐标绝对值不会超过 2^7
	输入的坐标均是有效的二维坐标
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/intersection-lcci
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	
 */
public class LeetCode_Satine_16_03 {
	public double[] intersection_1(int[] start1, int[] end1, int[] start2, int[] end2) {
		// 两线段的x和y的最值
		int minX1 = Math.min(start1[0], end1[0]);
		int minX2 = Math.min(start2[0], end2[0]);
		int minY1 = Math.min(start1[1], end1[1]);
		int minY2 = Math.min(start2[1], end2[1]);

		int maxX1 = Math.max(start1[0], end1[0]);
		int maxX2 = Math.max(start2[0], end2[0]);
		int maxY1 = Math.max(start1[1], end1[1]);
		int maxY2 = Math.max(start2[1], end2[1]);
		// 对于x和y，若其中一条线段的最小值大于另一条的最大值，则它们必不相交
		if (minX1 > maxX2 || minX2 > maxX1 || minY1 > maxY2 || minY2 > maxY1) {
			return new double[0];
		}
		// 计算y=kx+b的斜率k和截距b 以防斜率不存在计算k时使用三目运算给防御值0
		double k1 = minX1 == maxX1 ? 0 : (start1[1] - end1[1]) * 1.0 / (start1[0] - end1[0]);
		double k2 = minX2 == maxX2 ? 0 : (start2[1] - end2[1]) * 1.0 / (start2[0] - end2[0]);
		double b1 = start1[1] - k1 * start1[0];
		double b2 = start2[1] - k2 * start2[0];
		// 若均不存在斜率，那么x值已确定，y只需要取二者最小中的最大值即可
		if (minX1 == maxX1 && minX2 == maxX2 && minX1 == maxX2) {
			return new double[] { minX1, Math.max(minY1, minY2) };
			// 若其中之一无斜率，则x可确定，直接求y值，并判断y是否在无斜率的y区间内即可
		} else if (start1[0] == end1[0]) {
			double y = k2 * minX1 + b2;
			if (y >= minY1 && y <= maxY1) {
				return new double[] { minX1, y };
			} else {
				return new double[0];
			}
		} else if (start2[0] == end2[0]) {
			double y = k1 * minX2 + b1;
			if (y >= minY2 && y <= maxY2) {
				return new double[] { minX2, y };
			} else {
				return new double[0];
			}
		} else {
			// 若斜率相等，则b不等比不想交，b相等时需要再分别判断重叠部分的情况
			if (k1 == k2) {
				if (b1 != b2) {
					return new double[0];
				}
				boolean f = minX1 == minX2;
				boolean f1 = minX1 > minX2;
				boolean f2 = maxX1 < maxX2;
				// 最小x不等，则直接取最小x中的最大x对应点返回，若相等，则反向判断最大x时的类似情况即可
				if (f1 || (f && f2)) {
					if (start1[0] < end1[0]) {
						return new double[] { start1[0], start1[1] };
					} else {
						return new double[] { end1[0], end1[1] };
					}
				} else {
					if (start2[0] < end2[0]) {
						return new double[] { start2[0], start2[1] };
					} else {
						return new double[] { end2[0], end2[1] };
					}
				}
			} else {
				// 斜率都存在且不等，直接求节点，并判断区间即可
				double x = (b2 - b1) / (k1 - k2);
				double y = k1 * x + b1;

				boolean f1 = x >= minX1 && x <= maxX1 && x >= minX2 && x <= maxX2;
				boolean f2 = y >= minY1 && y <= maxY1 && y >= minY2 && y <= maxY2;

				if (f1 && f2) {
					return new double[] { x, y };
				} else {
					return new double[0];
				}
			}
		}
	}
}
