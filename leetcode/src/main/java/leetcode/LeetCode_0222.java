package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhoujie
 * @date 2020/11/24 上午9:52
 * @description: 222. 完全二叉树的节点个数
 * <p>
 * 给出一个完全二叉树，求出该树的节点个数。
 * <p>
 * 说明：
 * <p>
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * 1
 * / \
 * 2   3
 * / \  /
 * 4  5 6
 * <p>
 * 输出: 6
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0222 {
}

//  Definition for a binary tree node.
class TreeNode_0222 {
    int val;
    TreeNode_0222 left;
    TreeNode_0222 right;

    TreeNode_0222(int x) {
        val = x;
    }
}

class Solution_0222 {
    /**
     * @return int
     * @author zhoujie
     * @date 2020/11/24 上午9:54
     * @param: root
     * @description: 使用了双端队列，BFS
     */
    public int countNodes1(TreeNode_0222 root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode_0222> deque = new LinkedList<>();
        deque.offerFirst(root);
        return deal(deque, 0);
    }

    private int deal(Deque<TreeNode_0222> deque, int count) {
        if (deque.isEmpty()) {
            return count;
        } else {
            // BFS递归处理
            int size = deque.size();
            while (size-- > 0) {
                TreeNode_0222 poll = deque.pollLast();
                if (poll != null) {
                    count++;
                    deque.offerFirst(poll.left);
                    deque.offerFirst(poll.right);
                }
            }
            return deal(deque, count);
        }
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2020/11/24 上午10:04
     * @param: root
     * @description: 不使用额外辅助数据结构，自递归
     */
    public int countNodes2(TreeNode_0222 root) {
        return root == null ? 0 : countNodes2(root.left) + countNodes2(root.right) + 1;
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2020/11/24 上午11:20
     * @param: root
     * @description: 利用完全二叉树的特性，递归处理左右子树是否是完全满二叉树并统计节点数
     */
    public int countNodes3(TreeNode_0222 root) {
        if (root == null) {
            return 0;
        } else {
            // 计算左右子树的层高
            int leftHigh = getHigh(root.left);
            int rightHigh = getHigh(root.right);
            // 左右子树必有一个是完全满二叉树，统计满二叉树的节点总数+根节点数，然后递归处理非满二叉树的子树
            // 完全满二叉树的根节点总数是 1<<sonHigh-1，再加根节点就是1<<sonHigh
            if (leftHigh == rightHigh) {
                return countNodes3(root.right) + (1 << leftHigh);
            } else {
                return countNodes3(root.left) + (1 << rightHigh);
            }
        }
    }

    private int getHigh(TreeNode_0222 root) {
        // 统计完全二叉树的层高，直接递归左子树的即可
        int high = 0;
        while (root != null) {
            high++;
            root = root.left;
        }
        return high;
    }

}