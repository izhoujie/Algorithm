package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhoujie
 * @date 2020/12/11 下午4:07
 * @description: 649. Dota2 参议院
 * <p>
 * Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)
 * <p>
 * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的一项：
 * <p>
 * 禁止一名参议员的权利：
 * <p>
 * 参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。
 * <p>
 * 宣布胜利：
 * <p>
 *           如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。
 * <p>
 *  
 * <p>
 * 给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。
 * <p>
 * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
 * <p>
 * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 Radiant 或 Dire。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："RD"
 * 输出："Radiant"
 * 解释：第一个参议员来自 Radiant 阵营并且他可以使用第一项权利让第二个参议员失去权力，因此第二个参议员将被跳过因为他没有任何权利。然后在第二轮的时候，第一个参议员可以宣布胜利，因为他是唯一一个有投票权的人
 * 示例 2：
 * <p>
 * 输入："RDD"
 * 输出："Dire"
 * 解释：
 * 第一轮中,第一个来自 Radiant 阵营的参议员可以使用第一项权利禁止第二个参议员的权利
 * 第二个来自 Dire 阵营的参议员会被跳过因为他的权利被禁止
 * 第三个来自 Dire 阵营的参议员可以使用他的第一项权利禁止第一个参议员的权利
 * 因此在第二轮只剩下第三个参议员拥有投票的权利,于是他可以宣布胜利
 *  
 * <p>
 * 提示：
 * <p>
 * 给定字符串的长度在 [1, 10,000] 之间.
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dota2-senate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0649 {
}

class Solution_0649 {
    /**
     * @return java.lang.String
     * @author zhoujie
     * @date 2020/12/11 下午4:08
     * @param: senate
     * @description: 使用两个队列分别记录R和D的索引，然后开始模拟循环投票，能留下的将索引加一个不小于Senate的长度到队尾，
     * 直至有一个队列为空
     */
    public String predictPartyVictory_1(String senate) {
        Deque<Integer> R = new LinkedList<>();
        Deque<Integer> D = new LinkedList<>();
        int len = senate.length();
        for (int i = 0; i < len; i++) {
            if (senate.charAt(i) == 'R') {
                R.offer(i);
            } else {
                D.offer(i);
            }
        }
        while (!R.isEmpty() && !D.isEmpty()) {
            int dPoll = D.poll();
            int rPoll = R.poll();
            // 能留下的索引加的上的值只要大于Senate的长度即可，保证原始位置的相对性
            if (rPoll < dPoll) {
                R.offer(rPoll + len);
            } else {
                D.offer(dPoll + len);
            }
        }
        return R.isEmpty() ? "Dire" : "Radiant";
    }

    /**
     * @return java.lang.String
     * @author zhoujie
     * @date 2020/12/11 下午4:42
     * @param: senate
     * @description: 把Senate数组化，统计D和R的总数然后模拟循环投票，每轮的胜者保存到数组首部，直至D和R中有一方归零
     */
    public String predictPartyVictory_2(String senate) {
        int rCount = 0, dCount;
        char[] sChar = senate.toCharArray();
        for (char c : sChar) {
            if (c == 'R') {
                rCount++;
            }
        }
        int len = sChar.length;
        dCount = len - rCount;
        int rVote = 0;
        int dVote = 0;
        int index = 0;
        int end = len;
        while (rCount > 0 && dCount > 0) {
            if (index > 0) {
                end = index;
            }
            index = 0;
            for (int i = 0; i < end; i++) {
                // 若当前是R且D的可投票数为0，说明当前R胜出，保存R到数组并把R的票数加1
                // 若当前是R且D的可投票数不为0，说明D之前领先，当前的R将被票出，需要把R的议员总数减一且D的可投票数减一
                if (sChar[i] == 'R') {
                    if (dVote == 0) {
                        rVote++;
                        sChar[index++] = 'R';
                    } else {
                        rCount--;
                        dVote--;
                    }
                    // 与以上逻辑类似
                } else {
                    if (rVote == 0) {
                        dVote++;
                        sChar[index++] = 'D';
                    } else {
                        dCount--;
                        rVote--;
                    }
                }
            }
        }
        return sChar[0] == 'D' ? "Dire" : "Radiant";
    }

}