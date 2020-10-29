package leetcode;

/**
 * @author zhoujie
 * @date 2020/10/29 15:48
 * @description: 771. 宝石与石头
 * <p>
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * <p>
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 * 注意:
 * <p>
 * S 和 J 最多含有50个字母。
 *  J 中的字符不重复。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jewels-and-stones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0771 {

}

class Solution_0771 {
    /**
     * @return int
     * @author zhoujie
     * @date 2020/10/29 15:50
     * @param: J
     * @param: S
     * @description: 都是字母那就可以转数组处理了，当然也可以哈希处理，但效率没前者高
     */
    public int numJewelsInStones(String J, String S) {
        int count = 0;
        // 字母的统计范围
        byte[] jArray = new byte['z' - 'A' + 1];
        // 标识哪些是钻石
        for (int i = 0; i < J.length(); i++) {
            jArray[J.charAt(i) - 'A']++;
        }
        // 统计是不是钻石
        for (int i = 0; i < S.length(); i++) {
            count += jArray[S.charAt(i) - 'A'];
        }
        return count;
    }
}