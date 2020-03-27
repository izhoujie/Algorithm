package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月27日 下午1:22:07 
 * @Description: 914. 卡牌分组
 *
	给定一副牌，每张牌上都写着一个整数。
	
	此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
	
	每组都有 X 张牌。
	组内所有的牌上都写着相同的整数。
	仅当你可选的 X >= 2 时返回 true。
	
	 
	
	示例 1：
	
	输入：[1,2,3,4,4,3,2,1]
	输出：true
	解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
	示例 2：
	
	输入：[1,1,1,2,2,2,3,3]
	输出：false
	解释：没有满足要求的分组。
	示例 3：
	
	输入：[1]
	输出：false
	解释：没有满足要求的分组。
	示例 4：
	
	输入：[1,1]
	输出：true
	解释：可行的分组是 [1,1]
	示例 5：
	
	输入：[1,1,2,2,2,2]
	输出：true
	解释：可行的分组是 [1,1]，[2,2]，[2,2]
	
	提示：
	
	1 <= deck.length <= 10000
	0 <= deck[i] < 10000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-如果所有的数至少都能分成X组，那么说明这些数的最大公约数就是X。
 */
public class LeetCode_0914 {

}

class Solution_0914 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月27日 下午1:23:20 
	 * @param: @param deck
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-题目抽象，验证所给的数是否有不小于2的最大公约数；
	 *
	 */
	public boolean hasGroupsSizeX(int[] deck) {
		// 题目指定数的范围[0,10000)
		int[] statistics = new int[10000];
		for (int i : deck) {
			statistics[i]++;
		}
		int num = 0;
		for (int i : statistics) {
			if (i > 0) {
				num = gcd(i, num);
				if (num == 1) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月27日 下午1:28:42 
	 * @param: @param a
	 * @param: @param b
	 * @param: @return
	 * @return: int
	 * @Description: 求两数的最大公约数
	 *
	 */
	private int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}