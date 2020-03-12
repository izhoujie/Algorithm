package codewars;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author ZhouJie
 * @date 2020年3月4日 下午10:10:46 
 * @Description: Irreducible Sum of Rationals
 *
	You will have a list of rationals in the form
	
	lst = [ [numer_1, denom_1] , ... , [numer_n, denom_n] ]
	or
	
	lst = [ (numer_1, denom_1) , ... , (numer_n, denom_n) ]
	where all numbers are positive integers. You have to produce their sum N / D in an irreducible form: this means that N and D have only 1 as a common divisor.
	
	Return the result in the form:
	
	[N, D] in Ruby, Crystal, Python, Clojure, JS, CS, PHP, Julia
	Just "N D" in Haskell, PureScript
	"[N, D]" in Java, CSharp, TS, Scala, PowerShell, Kotlin
	"N/D" in Go, Nim
	{N, D} in C++, Elixir
	"{N, D}" in C
	Some((N, D)) in Rust
	Some "N D" in F#, Ocaml
	c(N, D) in R
	(N, D) in Swift
	'(N D) in Racket
	If the result is an integer (D evenly divides N) return:
	
	an integer in Ruby, Crystal, Elixir, Clojure, Python, JS, CS, PHP, R, Julia
	Just "n" (Haskell, PureScript)
	"n" Java, CSharp, TS, Scala, PowerShell, Go, Nim, Kotlin
	{n, 1} in C++
	"{n, 1}" in C
	Some((n, 1)) in Rust
	Some "n" in F#, Ocaml,
	(n, 1) in Swift
	n in Racket
	If the input list is empty, return
	
	nil/None/null/Nothing
	{0, 1} in C++
	"{0, 1}" in C
	"0" in Scala, PowerShell, Go, Nim
	O in Racket
	"" in Kotlin
	Example:
	[ [1, 2], [1, 3], [1, 4] ]  -->  [13, 12]
	
	    1/2  +  1/3  +  1/4     =      13/12
	Note
	See sample tests for more examples and the form of results.
 */
/**
 * @author ZhouJie
 * @date 2020年3月4日 下午10:44:07 
 * @Description: Irreducible Sum of Rationals
 *
	You will have a list of rationals in the form
	
	lst = [ [numer_1, denom_1] , ... , [numer_n, denom_n] ]
	or
	
	lst = [ (numer_1, denom_1) , ... , (numer_n, denom_n) ]
	where all numbers are positive integers. You have to produce their sum N / D in an irreducible form: this means that N and D have only 1 as a common divisor.
	
	Return the result in the form:
	
	[N, D] in Ruby, Crystal, Python, Clojure, JS, CS, PHP, Julia
	Just "N D" in Haskell, PureScript
	"[N, D]" in Java, CSharp, TS, Scala, PowerShell, Kotlin
	"N/D" in Go, Nim
	{N, D} in C++, Elixir
	"{N, D}" in C
	Some((N, D)) in Rust
	Some "N D" in F#, Ocaml
	c(N, D) in R
	(N, D) in Swift
	'(N D) in Racket
	If the result is an integer (D evenly divides N) return:
	
	an integer in Ruby, Crystal, Elixir, Clojure, Python, JS, CS, PHP, R, Julia
	Just "n" (Haskell, PureScript)
	"n" Java, CSharp, TS, Scala, PowerShell, Go, Nim, Kotlin
	{n, 1} in C++
	"{n, 1}" in C
	Some((n, 1)) in Rust
	Some "n" in F#, Ocaml,
	(n, 1) in Swift
	n in Racket
	If the input list is empty, return
	
	nil/None/null/Nothing
	{0, 1} in C++
	"{0, 1}" in C
	"0" in Scala, PowerShell, Go, Nim
	O in Racket
	"" in Kotlin
	Example:
	[ [1, 2], [1, 3], [1, 4] ]  -->  [13, 12]
	
	    1/2  +  1/3  +  1/4     =      13/12
	Note
	See sample tests for more examples and the form of results.
 */
public class KYU6_Irreducible_Sum_of_Rationals {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月4日 下午10:11:22 
	 * @param: @param l
	 * @param: @return
	 * @return: String
	 * @Description: 1-题干大意：求二维数组的最终无公约数形态；
	 * 				例子：[ [1, 2], [1, 3], [1, 4] ]  -->  [13, 12]
	 * 				因为： 1/2  +  1/3  +  1/4     =      13/12
	 * 
	 * 				思路：先将每个二维数字的对应数字公约化，然后求分母的最小公倍数，用最小公倍数分别除去分母后再连加；
	 * 				最后的和与最小公倍数再公约化即可；
	 *
	 */
	public static String sumFracts_1(int[][] l) {
		if (l == null || l.length == 0) {
			return null;
		}
		for (int i = 0; i < l.length; i++) {
			if (l[i].length == 0) {
				continue;
			}
			int a = l[i][0];
			int b = l[i][1];
			int c = gcd(a, b);
			l[i][0] = a / c;
			l[i][1] = b / c;
		}
		int maxG = l[0][1];
		for (int i = 1; i < l.length; i++) {
			if (l[i].length == 0) {
				continue;
			}
			int d = l[i][1];
			int e = gcd(maxG, d);
			maxG = (maxG * d) / e;
		}
		int mult = 0;
		for (int i = 0; i < l.length; i++) {
			if (l[i].length == 0) {
				continue;
			}
			mult += (maxG) / l[i][1] * l[i][0];
		}
		int f = gcd(mult, maxG);
		mult /= f;
		maxG /= f;
		return maxG == 1 ? String.valueOf(mult) : String.format("[%d, %d]", mult, maxG);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月4日 下午10:19:48 
	 * @param: @param a
	 * @param: @param b
	 * @param: @return
	 * @return: int
	 * @Description: 求两数的最大公约数
	 *
	 */
	private static int gcd(int a, int b) {
		if (a < b) {
			gcd(b, a);
		}
		return b == 0 ? a : gcd(b, a % b);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月4日 下午10:44:11 
	 * @param: @param l
	 * @param: @return
	 * @return: String
	 * @Description: TODO
	 *
	 */
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月4日 下午10:44:18 
	 * @param: @param l
	 * @param: @return
	 * @return: String
	 * @Description: 2-还是1的思路，但是使用工具类简化代码；
	 *
	 */
	public static String sumFracts_2(int[][] l) {
		if (l == null || l.length == 0) {
			return null;
		}
		int d = Arrays.stream(l).mapToInt(e -> e[1]).reduce(1, (a, b) -> a * b);
		int n = Arrays.stream(l).mapToInt(e -> d * e[0] / e[1]).sum();
		int c = BigInteger.valueOf(n).gcd(BigInteger.valueOf(d)).intValue();
		return d == c ? String.valueOf(n / d) : String.format("[%d, %d]", n / c, d / c);
	}
}
