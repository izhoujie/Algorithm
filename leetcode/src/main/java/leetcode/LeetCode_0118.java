package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年3月1日 下午9:30:54
 * @Description: 118. 杨辉三角
 * <p>
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * <p>
 * <p>
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 5
 * 输出:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 思路：1-逐行规律求解；
 * 2-单个list自我求解；
 */
public class LeetCode_0118 {

}

class Solution_0118 {
    /**
     * @author: ZhouJie
     * @date: 2020年3月1日 下午10:26:07
     * @param: @param numRows
     * @param: @return
     * @return: List<List < Integer>>
     * @Description: 1-
     */
    public List<List<Integer>> generate_1(int numRows) {
        List<List<Integer>> allList = new ArrayList<>();
        if (numRows == 0) {
            return allList;
        }
        List<Integer> firstLine = new ArrayList<>();
        firstLine.add(1);
        allList.add(firstLine);
        int k = 1;
        while (k++ < numRows) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            List<Integer> last = allList.get(allList.size() - 1);
            for (int i = 1; i < k - 1; i++) {
                list.add(last.get(i - 1) + last.get(i));
            }
            list.add(1);
            allList.add(list);
        }
        return allList;
    }

    /**
     * @author: ZhouJie
     * @date: 2020年3月1日 下午10:49:31
     * @param: @param numRows
     * @param: @return
     * @return: List<List < Integer>>
     * @Description: 2-
     */
    public List<List<Integer>> generate_2(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        if (numRows == 0) {
            return list;
        }
        List<Integer> model = new ArrayList<>();
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