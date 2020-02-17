package codewars;

/**
 * @author ZhouJie
 * @date 2020年2月17日 下午5:36:07 
 * @Description: Stop gninnipS My sdroW!
 *
	Write a function that takes in a string of one or more words, and returns the same string, but with all five or more letter words reversed (Just like the name of this Kata). Strings passed in will consist of only letters and spaces. Spaces will be included only when more than one word is present.
	
	Examples: spinWords( "Hey fellow warriors" ) => returns "Hey wollef sroirraw" spinWords( "This is a test") => returns "This is a test" spinWords( "This is another test" )=> returns "This is rehtona test"
 */

public class KYU6_SpinWords {

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月17日 下午7:52:54 
	 * @param: @param sentence
	 * @param: @return
	 * @return: String
	 * @Description: 1-分割后检测长度大于4反转后拼接，最后返回
	 *
	 */
	public String spinWords(String sentence) {
		if (sentence == null || sentence.trim().length() == 0) {
			return sentence;
		}
		StringBuilder sb = new StringBuilder(sentence.length() + 1);
		for (String s : sentence.split("\\s")) {
			if (s.length() > 4) {
				s = new StringBuilder(s).reverse().toString();
			}
			sb.append(s).append(" ");
		}
		return sb.toString().substring(0, sb.length() - 1);
	}
}