package leetcode;

import java.util.*;

/**
 * @author ZhouJie
 * @date 2020年2月2日 下午9:48:45
 * @Description: 49. 字母异位词分组
 * <p>
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 思路：1-toarray后排序作为map的key；
 * 2-使用质数乘积值作为map的key（利用了不同质数积的唯一性））（来自leetcode评论解法整理）；
 */
public class LeetCode_0049 {

}

class Solution_0049 {
    /**
     * @return List<List < String>>
     * @author ZhouJie
     * @date 2020年2月2日 下午10:06:14
     * @Description: TODO(方法简述)
     * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月2日 下午10:06:14]
     * @UpdateRemark:1-将字符串toarray后排序再转为string作为map的key，map的value为list，存字符串的原型
     */
    public List<List<String>> groupAnagrams_1(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        if (strs == null || strs.length == 0) {
            return new ArrayList<List<String>>(map.values());
        }
        for (int i = 0; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            Arrays.sort(cs);
            String key = String.valueOf(cs);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(strs[i]);
        }
        return new ArrayList<List<String>>(map.values());
    }

    /**
     * @return List<List < String>>
     * @author ZhouJie
     * @date 2020年2月2日 下午10:08:24
     * @Description: TODO(方法简述)
     * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月2日 下午10:08:24]
     * @UpdateRemark:2-将字符串的字母对应到质数拿到连乘积作为map的key;（来自leetcode评论区解法）
     */
    public List<List<String>> groupAnagrams_2(String[] strs) {
        HashMap<Long, List<String>> map = new HashMap<Long, List<String>>();
        if (strs == null || strs.length == 0) {
            return new ArrayList<List<String>>(map.values());
        }
        // 取前26个质数对应a-z26个字母的值
        long[] value = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 57, 61, 67, 71, 73, 79, 83, 89, 97,
                101};
        for (int i = 0; i < strs.length; i++) {
            long key = 1;
            for (char c : strs[i].toCharArray()) {
                key *= value[c - 'a'];
            }
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(strs[i]);
        }
        return new ArrayList<List<String>>(map.values());
    }

    /**
     * @return java.util.List<java.util.List < java.lang.String>>
     * @author zhoujie
     * @date 2020/12/14 下午3:31
     * @param: strs
     * @description:
     */
    public List<List<String>> groupAnagrams_3(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        int[] numbers = new int[26];
        for (String s : strs) {
            // 清零
            Arrays.fill(numbers, 0);
            for (int i = 0; i < s.length(); i++) {
                numbers[s.charAt(i) - 'a']++;
            }
            // 生成key后统计
            String key = new String(numbers, 0, 26);
            List<String> value = map.getOrDefault(key, new ArrayList<>());
            value.add(s);
            map.put(key, value);
        }
        return new ArrayList<>(map.values());
    }

}