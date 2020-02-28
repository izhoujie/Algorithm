package codewars;

/**
 * @author ZhouJie
 * @date 2020年2月28日 下午9:28:56 
 * @Description: Find the first non-consecutive number
 *
	Your task is to find the first element of an array that is not consecutive.
	
	By not consecutive we mean not exactly 1 larger than the previous element of the array.
	
	E.g. If we have an array [1,2,3,4,6,7,8] then 1 then 2 then 3 then 4 are all consecutive but 6 is not, so that's the first non-consecutive number.
	
	If the whole array is consecutive then return null2.
	
	The array will always have at least 2 elements1 and all elements will be numbers. The numbers will also all be unique and in ascending order. The numbers could be positive or negative and the first non-consecutive could be either too!
	
	If you like this Kata, maybe try this one next: https://www.codewars.com/kata/represent-array-of-numbers-as-ranges
	
	1 Can you write a solution that will return null2 for both [] and [ x ] though? ( This is not tested, but you can write your own example test. )
	
	2
	Swift, Ruby and Crystal: nil
	Haskell: Nothing
	Python: None
	Julia: nothing
 */
public class KYU8_Find_the_first_not_consecutive_number {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月28日 下午9:29:20 
	 * @param: @param array
	 * @param: @return
	 * @return: Integer
	 * @Description: 1-顺序遍历校验；
	 *
	 */
	static Integer find(final int[] array) {
		int len = 0;
		if (array == null || (len = array.length) == 0) {
			return null;
		}
		for (int i = 1; i < len; i++) {
			if (array[i] - array[i - 1] != 1) {
				return array[i];
			}
		}
		return null;
	}
}
