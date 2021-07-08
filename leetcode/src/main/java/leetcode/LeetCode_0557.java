package leetcode;

/**
 * @author zhoujie
 * @date 2021/7/8 下午4:44
 * @description: 557. 反转字符串中的单词 III
 * <p>
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 *  
 * <p>
 * 提示：
 * <p>
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 * 通过
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0557 {
}

class Solution_0557 {
    /**
     * @return java.lang.String
     * @author zhoujie
     * @date 2021/7/8 下午4:45
     * @param: s
     * @description: 逐个单词反转后拼接
     */
    public String reverseWords(String s) {
        StringBuilder sb1 = new StringBuilder(s.length());
        StringBuilder sb2 = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                sb1.append(c);
            } else {
                sb2.append(sb1.reverse()).append(' ');
                sb1.setLength(0);
            }
        }
        return sb2.length() == 0 ? sb1.reverse().toString() : sb2.append(sb1.reverse()).toString();
    }
}