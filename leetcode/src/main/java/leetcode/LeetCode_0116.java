package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhoujie
 * @date 2020/10/21 09:12
 * @description: 116. 填充每个节点的下一个右侧节点指针
 * <p>
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
 * <p>
 * 输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}
 * <p>
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 *  
 * <p>
 * 提示：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0116 {
}

// Definition for a Node.
class Node_0116 {
    public int val;
    public Node_0116 left;
    public Node_0116 right;
    public Node_0116 next;

    public Node_0116() {
    }

    public Node_0116(int _val) {
        val = _val;
    }

    public Node_0116(int _val, Node_0116 _left, Node_0116 _right, Node_0116 _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

class Solution_0116 {
    /**
     * @return leetcode.Node_0116
     * @author zhoujie
     * @date 2020/10/21 09:14
     * @param: root
     * @description: 按照树的层次遍历逐层处理，空间复杂度为logN
     */
    public Node_0116 connect_1(Node_0116 root) {
        if (root == null) {
            return null;
        }
        Deque<Node_0116> deque = new LinkedList<>();
        deque.add(root);
        // 根节点
        int count = deque.size();
        Node_0116 head = null;
        do {
            while (count-- > 0) {
                Node_0116 node = deque.pollFirst();
                // 所有节点都处理完毕，跳出
                if (node == null) {
                    deque.clear();
                    break;
                }
                // 保存下一层的节点
                deque.add(node.left);
                deque.add(node.right);
                // 每层首个节点处理
                if (head == null) {
                    head = node;
                    continue;
                }
                // 层节点的链接处理
                head.next = node;
                head = node;
            }
            head = null;
            count = deque.size();
        } while (count > 0);
        return root;
    }

    /**
     * @return leetcode.Node_0116
     * @author zhoujie
     * @date 2020/10/21 14:06
     * @param: root
     * @description: 递归处理父节点的左右子节点，以及父节点的右子节点和父节点next节点的左子节点的关联关系
     */
    public Node_0116 connect_2(Node_0116 root) {
        // 根节点为null，或只有根节点，或已处理到叶子节点时
        if (root == null || root.left == null) {
            return root;
        }
        // 当前父节点的左右子节点对应关系处理
        root.left.next = root.right;
        // 当前父节点的右节点和当前父节点的next节点的左节点关系处理
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        // 递归先处理左节点再处理右节点
        connect_1(root.left);
        connect_1(root.right);
        return root;
    }

    /**
     * @return leetcode.Node_0116
     * @author zhoujie
     * @date 2020/10/22 10:27
     * @param: root
     * @description: DFS处理
     */
    public Node_0116 connect_3(Node_0116 root) {
        // 特例判断
        if (root == null || root.left == null) {
            return root;
        }
        Node_0116 dealRoot = root;
        // 哑结点
        Node_0116 head = new Node_0116(0);
        // 每一层的头结点
        Node_0116 first;
        while (dealRoot != null) {
            first = head;
            // 连起每一层节点
            while (dealRoot != null && dealRoot.left != null) {
                first.next = dealRoot.left;
                first = first.next;
                first.next = dealRoot.right;
                first = first.next;
                dealRoot = dealRoot.next;
            }
            // 重置下一层的头结点和哑结点
            dealRoot = head.next;
            head.next = null;
        }
        return root;
    }

}