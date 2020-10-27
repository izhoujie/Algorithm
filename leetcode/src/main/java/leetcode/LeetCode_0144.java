package leetcode;

import java.util.*;

/**
 * @author zhoujie
 * @date 2020/10/27 17:42
 * @description: 144. 二叉树的前序遍历
 * <p>
 * 给定一个二叉树，返回它的 前序 遍历。
 * <p>
 *  示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0144 {
}

//  Definition for a binary tree node.
class TreeNode_0144 {
    int val;
    TreeNode_0144 left;
    TreeNode_0144 right;

    TreeNode_0144() {
    }

    TreeNode_0144(int val) {
        this.val = val;
    }

    TreeNode_0144(int val, TreeNode_0144 left, TreeNode_0144 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution_0144 {
    /**
     * @return java.util.List<java.lang.Integer>
     * @author zhoujie
     * @date 2020/10/27 17:43
     * @param: root
     * @description: 递归
     */
    public List<Integer> preorderTraversal_1(TreeNode_0144 root) {
        List<Integer> list = new ArrayList<>();
        deal(root, list);
        return list;
    }

    private void deal(TreeNode_0144 root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            deal(root.left, list);
            deal(root.right, list);
        }
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @author zhoujie
     * @date 2020/10/27 17:53
     * @param: root
     * @description: 迭代 模拟递归的流程，使用显示的栈替代递归的隐式栈
     */
    public List<Integer> preorderTraversal_2(TreeNode_0144 root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode_0144> stack = new LinkedList<>();
        TreeNode_0144 node = root;
        while (!stack.isEmpty() || node != null) {
            // 模拟递归处理左子树
            while (node != null) {
                // 先序遍历，先处理头结点
                list.add(node.val);
                stack.push(node);
                node = node.left;
            }
            // 模拟处理右子树
            node = stack.pop().right;
        }
        return list;
    }
}