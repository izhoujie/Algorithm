package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月5日 下午3:38:16 
 * @Description: 1103. 分糖果 II
 * 
	排排坐，分糖果。
	
	我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
	
	给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
	
	然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。
	
	重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
	
	返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
	
	 
	
	示例 1：
	
	输入：candies = 7, num_people = 4
	输出：[1,2,3,1]
	解释：
	第一次，ans[0] += 1，数组变为 [1,0,0,0]。
	第二次，ans[1] += 2，数组变为 [1,2,0,0]。
	第三次，ans[2] += 3，数组变为 [1,2,3,0]。
	第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
	示例 2：
	
	输入：candies = 10, num_people = 3
	输出：[5,2,3]
	解释：
	第一次，ans[0] += 1，数组变为 [1,0,0]。
	第二次，ans[1] += 2，数组变为 [1,2,0]。
	第三次，ans[2] += 3，数组变为 [1,2,3]。
	第四次，ans[0] += 4，最终数组变为 [5,2,3]。
	 
	
	提示：
	
	1 <= candies <= 10^9
	1 <= num_people <= 1000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/distribute-candies-to-people
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-直接模拟分糖果，比较直观，但效率一般；
		2-先求出1~n的和不大于candies的n，remaining=candies-sum(1~n)，remaining为分给最后一人的数量，
	time=n/num_people，time为完整分配的轮数，lastTime=n%num_people，不为0时则0~(lastTime-1)序号的人最后依次多分对应(n-lastTime)~n个；
 */
public class LeetCode_1103 {

}

class Solution_1103 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月5日 下午4:13:58 
	 * @param: @param candies
	 * @param: @param num_people
	 * @param: @return
	 * @return: int[]
	 * @Description: 1-
	 *
	 */
	public int[] distributeCandies_1(int candies, int num_people) {
		int[] rst = new int[num_people];
		// 每次要分出去的糖果数
		int gived = 0;
		while (candies > 0) {
			rst[gived % num_people] += Math.min(++gived, candies);
			candies -= gived;
		}
		return rst;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月5日 下午6:56:18 
	 * @param: @param candies
	 * @param: @param num_people
	 * @param: @return
	 * @return: int[]
	 * @Description: 2-确定规律，然后再分配，先找完整分配的轮数，然后确定基数和增量，一次遍历分配中顺带分配最后剩余不完整的一轮即可；
	 *
	 */
	public int[] distributeCandies_2(int candies, int num_people) {
		int[] rst = new int[num_people];
		// 倒数第二次分出去的是n个糖果，则n*(n+1)/2<=candies；
		// 一元二次方程的根公式，若a*x²+b*x+c=0，则 x=(-b±sqrt(b*b-4ac)/2a)；
		int n = (int) ((Math.sqrt(2 * candies + 0.25) - 0.5));
		// time是总共完整分配的轮次数
		int time = n / num_people;
		// time次分配完成时，第一个人分配到base个，后续每个人总比前一个人多分配time个；
		// 第一个人每次分到的数量：1、1+num_people、1+2*num_people、1+3*num_people...1+(time-1)*num_people
		// 故第一个人分到：rst[0]=time+num_people*(time*(time-1)/2)
		int base = (int) (time + num_people * time * (time - 1) * 0.5);
		// time次分配完成后，处理remaining和lastTime的分配
		// remaining为最后一次分出去的糖果数，若为0则说明不存在最后一次不够分配
		// lastTime为最后一轮可够分的人数
		// 若lastTime不为0，则第1到lastTime的人将依次分到(n-lastTime)~n个糖果，第lastTime+1人将分走最后的remaining
		// 若lastTime为0，则第一个人将分走最后的remaining，此时remaining可能为0
		int remaining = (int) (candies - n * (n + 1) * 0.5);
		int lastTime = n % num_people;
		for (int i = 0; i < num_people; i++) {
			rst[i] = base + time * i;
			if (i < lastTime) {
				rst[i] += time * num_people + 1 + i;
			}
		}
		rst[lastTime] += remaining;
		return rst;
	}

}
