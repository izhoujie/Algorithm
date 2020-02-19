package codewars;

/**
 * @author ZhouJie
 * @date 2020年2月20日 上午12:10:41 
 * @Description: Complementary DNA
 *
	Deoxyribonucleic acid (DNA) is a chemical found in the nucleus of cells and carries the "instructions" for the development and functioning of living organisms.
	
	If you want to know more http://en.wikipedia.org/wiki/DNA
	
	In DNA strings, symbols "A" and "T" are complements of each other, as "C" and "G". You have function with one side of the DNA (string, except for Haskell); you need to get the other complementary side. DNA strand is never empty or there is no DNA at all (again, except for Haskell).
	
	More similar exercise are found here http://rosalind.info/problems/list-view/ (source)
	
	DnaStrand.makeComplement("ATTGC") // return "TAACG"
	
	DnaStrand.makeComplement("GTAT") // return "CATA"
 */
public class KYU7_Complementary_DNA {
	public static String makeComplement(String dna) {
		if (dna == null || dna.length() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder(dna.length());
		for (char c : dna.toCharArray()) {
			if (c == 'A') {
				sb.append("T");
			} else if (c == 'T') {
				sb.append("A");
			} else if (c == 'C') {
				sb.append("G");
			} else if (c == 'G') {
				sb.append("C");
			}
		}
		return sb.toString();
	}
}
