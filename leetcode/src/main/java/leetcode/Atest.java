package leetcode;


import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhoujie
 * @date 2020/12/1 上午10:44
 * @description:
 */
public class Atest {


    private int binarySearch(int[] nums, int l, int target, boolean f) {
        int r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            // 找左边界时不要等于，把区间往左侧压缩，找右边界时要等于，把区间往右侧压缩
            if (nums[mid] < target || (nums[mid] == target && f)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> allList = new ArrayList<>();
        if (numRows == 0) {
            return allList;
        }
        List<Integer> firstLine = new ArrayList<>();
        firstLine.add(1);
        allList.add(firstLine);
        int k = 1;
        while (k++ < numRows) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            List<Integer> last = allList.get(allList.size() - 1);
            for (int i = 1; i < k - 1; i++) {
                list.add(last.get(i - 1) + last.get(i));
            }
            list.add(1);
            allList.add(list);
        }
        return allList;
    }

    public String interpret(String command) {
        StringBuilder sb = new StringBuilder(command.length());
        for (int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);
            if (c == 'G') {
                sb.append('G');
            } else if (c == '(') {
                char c1 = command.charAt(i + 1);
                if (c1 == ')') {
                    sb.append('o');
                    i++;
                } else {
                    sb.append("al");
                    i += 3;
                }
            }
        }
        return sb.toString();
    }

    public int maxOperations(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0] == k ? 1 : 0;
        }
        Arrays.sort(nums);
        int l = 0;
        int r = nums.length - 1;
        int count = 0;
        while (l < r) {
            int sum = nums[l] + nums[r];

            if (sum < k) {
                l++;
            } else if (sum > k) {
                r--;
            } else {
                l++;
                r--;
                count++;
            }
        }
        return count;
    }

    public int concatenatedBinary(int n) {
        long rst = 0;
        int bit = 0;
        int dc = 1000000007;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                bit++;
            }
            rst = ((rst << bit) + i) % dc;
        }
        return (int) rst;
    }

    public int concatenatedBinary1(int n) {
        int rst = 1;
        int dc = 1000000007;
        if (n == 1) {
            return rst;
        }
        for (int i = 2; i <= n; i++) {
            rst *= Math.pow(2, 1 + Math.floor(Math.log(i) / Math.log(2)));
            rst += i;
            rst %= dc;
        }
        return rst;
    }

    public String reformatNumber(String number) {
        String s = number.replace(" ", "").replace("-", "");
        int len = s.length();
        int len0 = len;
        int t = 0;
        StringBuilder sb = new StringBuilder(len + len / 3 + 2);
        while (len != 4 && len >= 3) {
            sb.append(s.substring(t, t + 3)).append("-");
            t += 3;
            len -= 3;
        }
        if (len == 0) {
            return sb.substring(0, sb.length() - 1);
        } else if (len == 2) {
            return sb.append(s.substring(len0 - 2, len0)).toString();
        } else {
            return sb.append(s.substring(len0 - 4, len0 - 2)).append("-").append(s.substring(len0 - 2, len0)).toString();
        }
    }

    public int maximumUniqueSubarray(int[] nums) {
        long max, temp;
        int left = 0;
        int right = 1;
        int len = nums.length;
        HashSet<Integer> set = new HashSet<>();
        set.add(nums[0]);
        max = temp = nums[0];
        while (left <= right && right < len) {
            while (right < len && !set.contains(nums[right])) {
                temp += nums[right];
                set.add(nums[right]);
                right++;
            }
            max = Math.max(max, temp);
            if (right >= len) {
                break;
            }
            while (nums[left] != nums[right]) {
                temp -= nums[left];
                set.remove(nums[left]);
                left++;
            }
            temp -= nums[left];
            set.remove(nums[left]);
            left++;
        }
        return (int) max;
    }

    //[10,-5,-2,4,0,3]
    //3
    public int maxResult(int[] nums, int k) {
        int t = 1;
        int r = nums[0];
        int len = nums.length;
        while (t < len - 1) {
            if (nums[t] >= 0) {
                r += nums[t++];
                continue;
            }
            int f = t;
            int max = nums[t];
            boolean ff = true;
            for (int i = 0; i < k && t < len - 1; i++) {
                if (nums[t] >= 0) {
                    r += nums[t];
                    t++;
                    ff = false;
                    break;
                } else {
                    if (nums[t] > max) {
                        max = nums[t];
                        f = t;
                        t++;
                    }

                }
            }
            if (ff) {
                r += max;
                if (t >= len - 1) {
                    break;
                }
                t = f;
            }
        }
        return len > 1 ? r + nums[len - 1] : r;
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int temp;
        for (int i = 0; i < n / 2 + 1; i++) {
            for (int j = i; j < n - i - 1; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    public boolean increasingTriplet(int[] nums) {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        for (int val : nums) {
            if (a >= val) {
                a = val;
            } else if (b >= val) {
                b = val;
            } else {
                return true;
            }
        }
        return false;
    }

    //      Definition for singly-linked list.
    class ListNode_1 {
        int val;
        ListNode_1 next;

        ListNode_1() {
        }

        ListNode_1(int val) {
            this.val = val;
        }

        ListNode_1(int val, ListNode_1 next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode_1 oddEvenList(ListNode_1 head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode_1 head1 = head;
        ListNode_1 head2 = head.next;
        ListNode_1 head3 = head.next;
        ListNode_1 node = head.next.next;
        while (node != null) {
            head1.next = node;
            head2.next = node.next;
            head1 = head1.next;
            head2 = head2.next;
            if (node.next == null) {
                break;
            }
            node = node.next.next;
        }
        head1.next = head3;
        return head;
    }

    public ListNode_1 oddEvenList_1(ListNode_1 head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode_1 node1 = head;
        ListNode_1 node2 = head.next;
        ListNode_1 node3 = head.next;
        while (node1.next != null && node2.next != null) {
            node1.next = node2.next;
            node1 = node1.next;
            node2.next = node1.next;
            node2 = node2.next;
        }
        node1.next = node3;
        return head;
    }

    // 0 1,3
    public ListNode_1 getIntersectionNode(ListNode_1 headA, ListNode_1 headB) {
        ListNode_1 A = headA;
        ListNode_1 B = headB;
        while (headA != headB) {
            if (headA == null) {
                headA = B;
            } else {
                headA = headA.next;
            }
            if (headB == null) {
                headB = A;
            } else {
                headB = headB.next;
            }
        }
        return headA;
    }

    public void kerge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n - 1;
        m--;
        n--;
        while (k > 0) {
            if (m < 0) {
                nums1[--k] = nums2[n--];
            } else if (n < 0) {
                nums1[--k] = nums1[m--];
            } else {
                nums1[--k] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
            }
        }
    }

    public int missingNumber(int[] nums) {
        int weight = 1000;
        switch (weight) {
            case 112:
            case 113:
                System.out.println("23");
                break;
            case 115:
            case 116:
            case 117:
                System.out.println("as");
                break;
        }
        int number = 0;
        for (int i = 0; i < nums.length; i++) {
            number ^= i ^ nums[i];
        }
        return number;
    }

    public int countGoodRectangles(int[][] rectangles) {
        int count = 0;
        int maxLen = 0;
        for (int[] rectangle : rectangles) {
            int max = Math.min(rectangle[0], rectangle[1]);
            if (max == maxLen) {
                count++;
            } else if (max > maxLen) {
                count = 1;
                maxLen = max;
            }
        }
        return count;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int m0 = -1;
        int n0 = -1;
        int x = 0;
        int y = 0;
        while (x > m0 && x < m && y > n0 && y < n) {
            while (y < n) {
                list.add(matrix[x][y++]);
            }
            y--;
            m0++;
            if (y <= n0 || y >= n) {
                break;
            }
            while (++x < m) {
                list.add(matrix[x][y]);
            }
            x--;
            n--;
            if (x <= m0 || x >= m) {
                break;
            }
            while (--y > n0) {
                list.add(matrix[x][y]);
            }
            y++;
            m--;
            if (y <= n0 || y >= n) {
                break;
            }
            while (--x > m0) {
                list.add(matrix[x][y]);
            }
            x++;
            y++;
            n0++;
        }
        return list;
    }

    public int strStr(String haystack, String needle) {
        int hLen = haystack.length();
        int nLen = needle.length();
        int mLen = hLen - nLen + 1;
        for (int i = 0; i < mLen; i++) {
            int index = 0;
            while (index < nLen && haystack.charAt(index + i) == needle.charAt(index)) {
                index++;
            }
            if (index == nLen) {
                return i;
            }
        }
        return -1;
    }

    public static int intBitCount(int n) {
        n -= (n >>> 1) & 0x55555555;
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n + (n >>> 4)) & 0x0f0f0f0f;
        n += n >>> 8;
        n += n >>> 16;
        return n & 0x3f;

    }

    public int search(int[] nums, int target) {
        int left = borderSearch(nums, 0, target, false);
        if (left == nums.length || nums[left] != target) {
            return 0;
        }
        return borderSearch(nums, left, target, true) - left;
    }

    private int borderSearch(int[] nums, int start, int target, boolean f) {
        int end = nums.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] < target || (f && nums[mid] == target)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    public int minimumSwitchingTimes(int[][] source, int[][] target) {
        int count = source.length * source[0].length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] arr : source) {
            for (int val : arr) {
                map.put(val, map.getOrDefault(val, 0) + 1);
            }
        }
        for (int[] arr : target) {
            for (int val : arr) {
                if (map.containsKey(val) && map.get(val) > 0) {
                    count--;
                    map.put(val, map.get(val) - 1);
                }
            }
        }
        return count;
    }

    HashMap<Integer, Integer> map = new HashMap<>();
    char d = 'X';
    char b = 'O';
    char u = '.';
    int max = 0;

    public int flipChess(String[] chessboard) {
        map = new HashMap<>();
        int n = chessboard.length;
        int m = chessboard[0].length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (chessboard[i].charAt(j) == d) {
                    check(chessboard, i, j, n, m);

                }
            }
        }
        return max;
    }

    private void check(String[] chessboard, int i, int j, int n, int m) {


        int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
        for (int k = 0; k < 8; k++) {
            int count = 0;
            int i0 = i + dx[k];
            int j0 = j + dy[k];
            while (i0 > -1 && i0 < n && j0 > -1 && j0 < m) {
                char c = chessboard[i0].charAt(j0);
                if (c == d || c == u && count == 0) {
                    break;
                }
                if (c == b) {
                    count++;
                } else if (c == u && count > 0) {
                    int dKey = (i0 - '0') * 10 + j0 - '0';
                    map.put(dKey, map.getOrDefault(dKey, 0) + count);
                    max = Math.max(max, map.get(dKey));
                    break;
                }
                i0 += dx[k];
                j0 += dy[k];
            }
        }
    }

    public int[] volunteerDeployment(int[] finalCnt, long totalNum, int[][] edges, int[][] plans) {
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            HashSet<Integer> setA = map.getOrDefault(a, new HashSet<>());
            setA.add(b);
            map.put(a, setA);
            HashSet<Integer> setB = map.getOrDefault(b, new HashSet<>());
            setB.add(a);
            map.put(b, setB);
        }
        int len = finalCnt.length + 1;
        long[] x = new long[len];
        long[] t = new long[len];
        for (int i = 0; i < len - 1; i++) {
            t[i + 1] = finalCnt[i];
        }
        x[0] = 1;
        for (int i = plans.length - 1; i > -1; i--) {
            int num = plans[i][0];
            int idx = plans[i][1];
            if (num == 1) {
                x[idx] <<= 1;
                t[idx] <<= 1;
            } else {
                HashSet<Integer> set = map.get(idx);
                if (set == null) {
                    continue;
                }
                if (num == 2) {
                    for (int id : set) {
                        x[id] -= x[idx];
                        t[id] -= t[idx];
                    }
                } else {
                    for (int id : set) {
                        x[id] += x[idx];
                        t[id] += t[idx];
                    }
                }
            }
        }
        long h = (totalNum - Arrays.stream(t).sum()) / Arrays.stream(x).sum();
        int[] y = new int[len];
        for (int i = 0; i < len; i++) {
            y[i] = (int) (h * x[i] + t[i]);
        }
        return y;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] rst = new int[len1];
        for (int i = 0; i < len1; i++) {
            int val = -1;
            for (int j = i + 1; j < len2; j++) {
                if (nums2[j] > nums1[i]) {
                    val = nums2[j];
                    break;
                }
            }
            rst[i] = val;
        }
        return rst;
    }

    public boolean reorderedPowerOf2(int n) {
        int t = 1;
        int max = 1000000000;
        HashSet<String> set = new HashSet<>();
        while (t < max) {
            char[] chars = String.valueOf(t).toCharArray();
            Arrays.sort(chars);
            set.add(new String(chars));
            t <<= 1;
        }
        char[] chars = String.valueOf(n).toCharArray();
        Arrays.sort(chars);
        return set.contains(new String(chars));
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> collect = Stream.generate(new Random()::nextInt).limit(3).collect(Collectors.toList());
        Integer[] intList = Stream.iterate(1, x -> x + 2).filter(x -> x % 3 == 0).limit(5).unordered().toArray(Integer[]::new);
        Arrays.stream(intList).parallel().mapToInt(x -> x).forEach(System.out::println);
        int[] a = new int[]{0, 1};
        int[] b = new int[2];
        int sum = Stream.iterate(a, arr -> {
                    int i = arr[1];
                    int j = arr[0] + arr[1];
                    b[0] = i;
                    b[1] = j;
                    return b;
                }
        ).limit(11).map(arr -> arr[0]).mapToInt(x -> x).sum();
        System.out.println(sum);
        HashMap<Object, Object> map = new HashMap<>();
        System.out.println(intBitCount(11));

        long ts = System.currentTimeMillis();
        System.out.println(ts);
        ts += Math.random() * 10;
        System.out.println(ts);
        String s3 = "//File|name.txt6998";
        System.out.println(s3);
        String s = s3.replaceFirst(File.separator, "");
        System.out.println(s);
        int h = 0;
        for (int i = 0; i < 10; i++)
            h++;
        System.out.println(h);

        int z = 0;
        int x = 0;
        do {
            z++;
            x++;
        } while (x <= 10);
        System.out.println(z);
        LocalDate parse = LocalDate.parse("2022-03-25");
        System.out.println(parse);
        double d = 43.78;
        int ddd = (int) d;
        System.out.println(ddd);

    }
}

