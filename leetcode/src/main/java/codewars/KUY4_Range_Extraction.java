package codewars;

/**
 * @author zhoujie
 * @date 2023/5/25 16:42
 * @description: Range Extraction
 * <p>
 * A format for expressing an ordered list of integers is to use a comma separated list of either
 * <p>
 * individual integers
 * or a range of integers denoted by the starting integer separated from the end integer in the range by a dash, '-'. The range includes all integers in the interval including both endpoints. It is not considered a range unless it spans at least 3 numbers. For example "12,13,15-17"
 * Complete the solution so that it takes a list of integers in increasing order and returns a correctly formatted string in the range format.
 * <p>
 * Example:
 * <p>
 * Solution.rangeExtraction(new int[] {-10, -9, -8, -6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20})
 * # returns "-10--8,-6,-3-1,3-5,7-11,14,15,17-20"
 */
public class KUY4_Range_Extraction {
    /**
     * @return java.lang.String
     * @author zhoujie
     * @date 2023/5/25 16:43
     * @param: arr
     * @description: ß
     */
    public static String rangeExtraction(int[] arr) {
        StringBuilder sb = new StringBuilder();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            sb.append(arr[i]);
            int j = i;
            while (j < len - 1 && arr[j] + 1 == arr[j + 1]) {
                j++;
            }
            if (j - i > 1) {
                i = j;
                sb.append("-").append(arr[i]);
            }
            sb.append(",");
        }
        return sb.substring(0, sb.length() - 1).toString();
    }
}
