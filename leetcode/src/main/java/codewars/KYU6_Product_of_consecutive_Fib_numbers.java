package codewars;

/**
 * @author ZhouJie
 * @date 2020年2月20日 下午11:23:28 
 * @Description: Product of consecutive Fib numbers
 *
	Your function productFib takes an integer (prod) and returns an array:
	
	[F(n), F(n+1), true] or {F(n), F(n+1), 1} or (F(n), F(n+1), True)
	depending on the language if F(n) * F(n+1) = prod.
	
	If you don't find two consecutive F(m) verifying F(m) * F(m+1) = prodyou will return
	
	[F(m), F(m+1), false] or {F(n), F(n+1), 0} or (F(n), F(n+1), False)
	F(m) being the smallest one such as F(m) * F(m+1) > prod.
	
	Examples
	productFib(714) # should return {21, 34, 1}, 
	                # since F(8) = 21, F(9) = 34 and 714 = 21 * 34
	
	productFib(800) # should return {34, 55, 0}, 
	                # since F(8) = 21, F(9) = 34, F(10) = 55 and 21 * 34 < 800 < 34 * 55
	Notes: Not useful here but we can tell how to choose the number n up to which to go: we can use the "golden ratio" phi which is (1 + sqrt(5))/2 knowing that F(n) is asymptotic to: phi^n / sqrt(5). That gives a possible upper bound to n.
	
	You can see examples in "Example test".
	
	References
	http://en.wikipedia.org/wiki/Fibonacci_number
	
	http://oeis.org/A000045
 */
public class KYU6_Product_of_consecutive_Fib_numbers {
	public static long[] productFib(long prod) {
		long a = 0, b = 1, c;
		while (a * b < prod) {
			c = a;
			a = b;
			b = a + c;
		}
		return new long[] { a, b, a * b == prod ? 1 : 0 };
	}
}
