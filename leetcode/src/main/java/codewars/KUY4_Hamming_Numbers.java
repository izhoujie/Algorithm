package codewars;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoujie
 * @date 2023/5/26 10:41
 * @description: Hamming Numbers
 * <p>
 * A Hamming number is a positive integer of the form 2i3j5k, for some non-negative integers i, j, and k.
 * <p>
 * Write a function that computes the nth smallest Hamming number.
 * <p>
 * Specifically:
 * <p>
 * The first smallest Hamming number is 1 = 203050
 * The second smallest Hamming number is 2 = 213050
 * The third smallest Hamming number is 3 = 203150
 * The fourth smallest Hamming number is 4 = 223050
 * The fifth smallest Hamming number is 5 = 203051
 * The 20 smallest Hamming numbers are given in the Example test fixture.
 * <p>
 * Your code should be able to compute the first 5 000 ( LC: 400, Clojure: 2 000, Haskell: 12 691, NASM, C, D, C++, Go and Rust: 13 282 ) Hamming numbers without timing out.
 */
public class KUY4_Hamming_Numbers {
    /**
     * @return long
     * @author zhoujie
     * @date 2023/5/26 10:42
     * @param: n
     * @description: 1-使用list顺次记录所有Hamming numbers
     * 2-使用三个变量各自记录235的使用量，每个变量的使用次数对应到list中对应位置使用该变量时的最大值
     */
    public static long hamming(int n) {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        int num2 = 0;
        int num3 = 0;
        int num5 = 0;
        int count = 1;
        while (count < n) {
            long m2 = list.get(num2) * 2;
            long m3 = list.get(num3) * 3;
            long m5 = list.get(num5) * 5;
            long r = Math.min(Math.min(m2, m3), m5);
            if (r == m2) {
                num2++;
            }
            if (r == m3) {
                num3++;
            }
            if (r == m5) {
                num5++;
            }
            count++;
            list.add(r);
        }
        return list.get(list.size() - 1);
    }

}
