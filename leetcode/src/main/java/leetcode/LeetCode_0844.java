package leetcode;

/**
 * @author zhoujie
 * @date 2020/10/19 15:59
 * @description: 844. 比较含退格的字符串
 * <p>
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * <p>
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * 示例 2：
 * <p>
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * 示例 3：
 * <p>
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * 示例 4：
 * <p>
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 * <p>
 * 进阶：
 * <p>
 * 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/backspace-string-compare
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0844 {
}

class Solution_0844 {
    /**
     * @return boolean
     * @author zhoujie
     * @date 2020/10/19 16:02
     * @param: S
     * @param: T
     * @description: 从尾部向头部对其比较，因为退格只影响其前面的字符不影响其后面的字符
     */
    public boolean backspaceCompare(String S, String T) {
        int lenS = S.length() - 1;
        int lenT = T.length() - 1;
        char c1 = '#';
        char c2 = '#';
        int countS = 0;
        int countT = 0;
        while (lenS > -1 || lenT > -1) {
            // 再无退格时则表示找到了下一个可能有效字符
            while (lenS > -1 && (countS > 0 || S.charAt(lenS) == '#')) {
                if (S.charAt(lenS) == '#') {
                    countS++;
                } else {
                    countS--;
                }
                lenS--;
            }
            // 确定有效
            if (lenS > -1) {
                c1 = S.charAt(lenS);
            }
            // 再无退格时则表示找到了下一个可能有效字符
            while (lenT > -1 && (countT > 0 || T.charAt(lenT) == '#')) {
                if (T.charAt(lenT) == '#') {
                    countT++;
                } else {
                    countT--;
                }
                lenT--;
            }
            // 确定有效
            if (lenT > -1) {
                c2 = T.charAt(lenT);
            }
            // 若不相等则可直接返回，若相等且都为'#'说明剩余字符都是退格也可直接返回
            if (c1 != c2) {
                return false;
            } else if (c1 == '#') {
                return true;
            }
            // 重置标志位，开始下一轮寻找与对比
            c1 = c2 = '#';
            lenS--;
            lenT--;
        }
        return true;
    }
}