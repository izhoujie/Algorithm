package codewars;

/**
 * @author ZhouJie
 * @date 2020年2月18日 下午11:40:02 
 * @Description: Credit Card Mask
 *
	Usually when you buy something, you're asked whether your credit card number, phone number or answer to your most secret question is still correct. However, since someone could look over your shoulder, you don't want that shown on your screen. Instead, we mask it.
	
	Your task is to write a function maskify, which changes all but the last four characters into '#'.
	
	Examples
	Maskify.Maskify("4556364607935616"); // should return "############5616"
	Maskify.Maskify("64607935616");      // should return "#######5616"
	Maskify.Maskify("1");                // should return "1"
	Maskify.Maskify("");                 // should return ""
	
	// "What was the name of your first pet?"
	Maskify.Maskify("Skippy");                                   // should return "##ippy"
	Maskify.Maskify("Nananananananananananananananana Batman!"); // should return "####################################man!"
 */
public class KYU7_Credit_Card_Mask {
	public static String maskify(String str) {
		int len = 0;
		if (str == null || (len = str.length()) < 5) {
			return str;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len - 4; i++) {
			sb.append("#");
		}
		return sb.append(str.substring(len - 4, len)).toString();
	}
}
