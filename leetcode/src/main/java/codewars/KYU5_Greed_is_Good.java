package codewars;

/**
 * @author ZhouJie
 * @date 2020年2月28日 下午8:37:12 
 * @Description: Greed is Good
 *
	Greed is a dice game played with five six-sided dice. Your mission, should you choose to accept it, is to score a throw according to these rules. You will always be given an array with five six-sided dice values.
	
	 Three 1's => 1000 points
	 Three 6's =>  600 points
	 Three 5's =>  500 points
	 Three 4's =>  400 points
	 Three 3's =>  300 points
	 Three 2's =>  200 points
	 One   1   =>  100 points
	 One   5   =>   50 point
	A single die can only be counted once in each roll. For example, a "5" can only count as part of a triplet (contributing to the 500 points) or as a single 50 points, but not both in the same roll.
	
	Example scoring
	
	 Throw       Score
	 ---------   ------------------
	 5 1 3 4 1   50 + 2 * 100 = 250
	 1 1 1 3 1   1000 + 100 = 1100
	 2 4 4 5 4   400 + 50 = 450
	In some languages, it is possible to mutate the input to the function. This is something that you should never do. If you mutate the input, you will not be able to pass all the tests.
 */
public class KYU5_Greed_is_Good {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月28日 下午9:07:13 
	 * @param: @param dice
	 * @param: @return
	 * @return: int
	 * @Description: 1-一次遍历统计骰子点数对应颗数，然后直接计算分值返回；
	 *
	 */
	public static int greedy(int[] dice) {
		int[] record = new int[7];
		for (int i : dice) {
			record[i]++;
		}
		return record[1] / 3 * 1000 + record[1] % 3 * 100 + record[2] / 3 * 200 + record[3] / 3 * 300
				+ record[4] / 3 * 400 + record[5] / 3 * 500 + record[5] % 3 * 50 + record[6] / 3 * 600;
	}
}
