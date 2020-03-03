package codewars;

import java.util.HashMap;

/**
 * @author ZhouJie
 * @date 2020年3月3日 下午8:16:30 
 * @Description: parseInt() reloaded
 *
	In this kata we want to convert a string into an integer. The strings simply represent the numbers in words.
	
	Examples:
	
	"one" => 1
	"twenty" => 20
	"two hundred forty-six" => 246
	"seven hundred eighty-three thousand nine hundred and nineteen" => 783919
	Additional Notes:
	
	The minimum number is "zero" (inclusively)
	The maximum number, which must be supported is 1 million (inclusively)
	The "and" in e.g. "one hundred and twenty-four" is optional, in some cases it's present and in others it's not
	All tested numbers are valid, you don't need to validate them
 */
public class KYU4_parseInt_reloaded {
	private static HashMap<String, Integer> map = new HashMap<String, Integer>();
	static {
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		map.put("four", 4);
		map.put("five", 5);
		map.put("six", 6);
		map.put("seven", 7);
		map.put("eight", 8);
		map.put("nine", 9);
		map.put("ten", 10);
		map.put("eleven", 11);
		map.put("twelve", 12);
		map.put("thirteen", 13);
		map.put("fourteen", 14);
		map.put("fifteen", 15);
		map.put("sixteen", 16);
		map.put("seventeen", 17);
		map.put("eighteen", 18);
		map.put("nineteen", 19);
		map.put("twenty", 20);
		map.put("thirty", 30);
		map.put("forty", 40);
		map.put("fifty", 50);
		map.put("sixty", 60);
		map.put("seventy", 70);
		map.put("eighty", 80);
		map.put("ninety", 90);
		map.put("one million", 1000000);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月3日 下午9:00:51 
	 * @param: @param numStr
	 * @param: @return
	 * @return: int
	 * @Description: 1-首先1-19，20-90需要一个map，0可忽略，然后截断thousand和hundred递归求解，最小子问题为转换100内的数；
	 *
	 */
	public static int parseInt(String numStr) {
		numStr = numStr.trim();
		if (map.get(numStr) != null) {
			return map.get(numStr);
		} else {
			if (numStr.contains("thousand")) {
				String[] s = numStr.split("thousand");
				if (numStr.endsWith("thousand")) {
					return parseInt(s[0]) * 1000;
				} else {
					return parseInt(s[0]) * 1000 + parseInt(s[1]);
				}
			} else if (numStr.contains("hundred")) {
				String[] s = numStr.split("hundred");
				if (numStr.endsWith("hundred")) {
					return parseInt(s[0]) * 100;
				} else {
					return parseInt(s[0]) * 100 + parseInt(s[1]);
				}
			} else {
				String[] arr = numStr.replaceAll("-", " ").split(" ");
				int t = 0;
				for (String s : arr) {
					t += map.getOrDefault(s, 0);
				}
				return t;
			}
		}
	}

}
