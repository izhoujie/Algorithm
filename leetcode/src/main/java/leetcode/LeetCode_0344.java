package leetcode;

/**
 * @author zhoujie
 * @date 2021/7/8 下午4:26
 * @description: 344. 反转字符串
 * <p>
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 * <p>
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0344 {
}

class Solution_0344 {
    /**
     * @return void
     * @author zhoujie
     * @date 2021/7/8 下午4:27
     * @param: s
     * @description: 左右双指针
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        char temp;
        while (left < right) {
            temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }
}