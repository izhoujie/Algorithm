package leetcode;

import java.util.*;

/**
 * @author zhoujie
 * @date 2020/12/22 上午9:27
 * @description: 103. 二叉树的锯齿形层序遍历
 * <p>
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层序遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0103 {
}


//  Definition for a binary tree node.
class TreeNode_0103 {
    int val;
    TreeNode_0103 left;
    TreeNode_0103 right;

    TreeNode_0103(int x) {
        val = x;
    }
}

class Solution_0103 {
    /**
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author zhoujie
     * @date 2020/12/22 上午9:29
     * @param: root
     * @description: 使用双端队列，逐层处理/BFS，注意使用LinkedList，前插效率比ArrayList要高
     */
    public List<List<Integer>> zigzagLevelOrder_1(TreeNode_0103 root) {
        List<List<Integer>> lists = new ArrayList<>();
        Deque<TreeNode_0103> deque = new LinkedList<>();
        if (root != null) {
            deque.addFirst(root);
        }
        int level = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            level++;
            List<Integer> list = new LinkedList<>();
            while (size-- > 0) {
                TreeNode_0103 poll = deque.pollLast();
                if (poll != null) {
                    list.add((level & 1) == 0 ? 0 : list.size(), poll.val);
                    if (poll.left != null) {
                        deque.offerFirst(poll.left);
                    }
                    if (poll.right != null) {
                        deque.offerFirst(poll.right);
                    }
                }
            }
            lists.add(list);
        }
        return lists;
    }

    /**
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author zhoujie
     * @date 2020/12/22 上午10:14
     * @param: root
     * @description: 递归解法/BFS
     */
    public List<List<Integer>> zigzagLevelOrder_2(TreeNode_0103 root) {
        List<List<Integer>> lists = new ArrayList<>();
        deal(root, lists, 0);
        return lists;
    }

    private void deal(TreeNode_0103 root, List<List<Integer>> lists, int level) {
        if (root == null) {
            return;
        }
        if (lists.size() == level) {
            lists.add(new LinkedList<>());
        }
        if ((level & 1) == 1) {
            lists.get(level).add(0, root.val);
        } else {
            lists.get(level).add(root.val);
        }
        deal(root.left, lists, level + 1);
        deal(root.right, lists, level + 1);
    }

}