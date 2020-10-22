package leetcode;


/**
 * @author zhoujie
 * @date 2020/10/21 17:53
 * @description: 925. 长按键入
 * <p>
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * <p>
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：name = "alex", typed = "aaleex"
 * 输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 * 示例 2：
 * <p>
 * 输入：name = "saeed", typed = "ssaaedd"
 * 输出：false
 * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 * 示例 3：
 * <p>
 * 输入：name = "leelee", typed = "lleeelee"
 * 输出：true
 * 示例 4：
 * <p>
 * 输入：name = "laiden", typed = "laiden"
 * 输出：true
 * 解释：长按名字中的字符并不是必要的。
 *  
 * <p>
 * 提示：
 * <p>
 * name.length <= 1000
 * typed.length <= 1000
 * name 和 typed 的字符都是小写字母。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/long-pressed-name
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0925 {
}

class Solution_0925 {
    /**
     * @return boolean
     * @author zhoujie
     * @date 2020/10/21 17:54
     * @param: name
     * @param: typed
     * @description:
     */
    public boolean isLongPressedName_1(String name, String typed) {
        // 特例判断
        if (name == null) {
            return typed == null;
        }
        // 特例判断
        if (name.length() == 0) {
            return typed != null && typed.length() == 0;
        }
        char c = name.charAt(0);
        int nameLen = name.length();
        int typedLen = typed.length();
        // 长度判断
        if (nameLen > typedLen) {
            return false;
        }
        int i = 0;
        int j = 0;
        // 验证
        while (i < nameLen || j < typedLen) {
            if (i == nameLen) {
                if (typed.charAt(j) != c) {
                    return false;
                }
                j++;
            } else if (j == typedLen) {
                return false;
            } else if (name.charAt(i) == typed.charAt(j)) {
                c = name.charAt(i);
                i++;
                j++;
            } else if (typed.charAt(j) == c) {
                j++;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * @return boolean
     * @author zhoujie
     * @date 2020/10/22 09:05
     * @param: name
     * @param: typed
     * @description:
     */
    public boolean isLongPressedName_2(String name, String typed) {
        // 特例判断
        if (name == null) {
            return typed == null;
        }
        // 特例判断
        if (name.length() == 0) {
            return typed != null && typed.length() == 0;
        }
        int nameLen = name.length();
        int typedLen = typed.length();
        // 长度判断
        if (nameLen > typedLen) {
            return false;
        }
        char[] nameChar = name.toCharArray();
        char[] typedChar = typed.toCharArray();

        int i = 0;
        int j = 0;
        // 验证
        while (i < nameLen && j < typedLen) {
            if (nameChar[i] == typedChar[j]) {
                i++;
                j++;
            } else if (j > 0 && typedChar[j - 1] == typedChar[j]) {
                j++;
            } else {
                return false;
            }
        }
        // 若typed长度校验完了而name还有剩余，false
        if (i < nameLen) {
            return false;
        }
        // 若typed有剩余，则剩余部分必须都与前一个字符一样
        while (j < typedLen) {
            if (typedChar[j - 1] != typedChar[j]) {
                return false;
            }
            j++;
        }
        return true;
    }
}