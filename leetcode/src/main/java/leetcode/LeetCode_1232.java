package leetcode;

/**
 * @author zhoujie
 * @date 2021/1/17 下午12:39
 * @description: 1232. 缀点成线
 *
 * <p>
 * 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
 * <p>
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates 中不含重复的点
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-it-is-a-straight-line
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_1232 {
}

class Solution_1232 {
    /**
     * @return boolean
     * @author zhoujie
     * @date 2021/1/17 下午12:40
     * @param: coordinates
     * @description: 分有斜率，无斜率，斜率为0三种情况分别处理
     */
    public boolean checkStraightLine(int[][] coordinates) {
        int x1 = coordinates[0][0];
        int y1 = coordinates[0][1];
        int x2 = coordinates[1][0];
        int y2 = coordinates[1][1];
        int len = coordinates.length;
        if (x1 == x2) {
            for (int i = 2; i < len; i++) {
                if (coordinates[i][0] != x1) {
                    return false;
                }
            }
        } else if (y1 == y2) {
            for (int i = 2; i < len; i++) {
                if (coordinates[i][1] != y1) {
                    return false;
                }
            }
        } else {
            int dx = x1 - x2;
            int dy = y1 - y2;
            for (int i = 2; i < len; i++) {
                int x3 = coordinates[i][0];
                int y3 = coordinates[i][1];
                if (dy * (x3 - x1) != dx * (y3 - y1)) {
                    return false;
                }
            }
        }
        return true;
    }
}