package others;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ZhouJie
 * @date 2019年11月24日 下午10:29:46 
 * @Description: 二叉树的前中后序遍历及层次遍历 
 *
 */
public class BinaryTree {

	/**
	 * @author ZhouJie
	 * @date 2019年12月1日 下午8:21:27 
	 * @Description: 构造树
	 * @return Node 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2019年12月1日 下午8:21:27]  
	 * @UpdateRemark:[本次修改内容]  
	 *
	 */
	public static Node bulidBinaryTree(int[] arr) {
		if (arr == null) {
			return null;
		}
		int len = arr.length;
		ArrayList<Node> tree = new ArrayList<Node>(len);
		for (int i = 0; i < len; i++) {
			tree.add(new Node(arr[i]));
		}

		for (int i = 0; i < len / 2 - 1; i++) {
			Node node = tree.get(i);
			node.left = tree.get(i * 2 + 1);
			node.right = tree.get(i * 2 + 2);
		}

		int lastNode = len / 2 - 1;
		Node node = tree.get(lastNode);
		node.left = tree.get(lastNode * 2 + 1);
		if (len % 2 != 0) {
			node.right = tree.get(lastNode * 2 + 2);
		}
		Node root = tree.get(0);
		tree.clear();
		tree = null;
		return root;
	}

	/**
	 * @author ZhouJie
	 * @date 2019年12月1日 下午8:29:22 
	 * @Description: which=1前序遍历；which=2中序遍历；which=3后序遍历
	 * @return void 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2019年12月1日 下午8:29:22]  
	 * @UpdateRemark:[本次修改内容]  
	 *
	 */
	public static void binaryTreePrint(Node root, int which) {
		if (root == null) {
			return;
		}
		Node left = root.left;
		Node right = root.right;
		if (which == 1) {
			System.out.print(root.value + "\t");
		}
		if (left != null) {
			binaryTreePrint(left, which);
		}
		if (which == 2) {
			System.out.print(root.value + "\t");
		}
		if (right != null) {
			binaryTreePrint(right, which);
		}
		if (which == 3) {
			System.out.print(root.value + "\t");
		}
	}

	public static void binaryTreeLevelPrint(Node root) {
		if (root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			System.out.print(node.value + "\t");
			Node left = node.left;
			Node right = node.right;
			if (left != null) {
				queue.offer(left);
			}
			if (right != null) {
				queue.offer(right);
			}
		}
	}

	public static void main(String[] args) {
		int[] test = new int[] { 1, 2, 3, 4, 5, 6 };
		Node root = bulidBinaryTree(test);
		System.out.println("\n层次遍历：");
		binaryTreeLevelPrint(root);
		System.out.println("\n前序遍历：");
		binaryTreePrint(root, 1);
		System.out.println("\n中序遍历：");
		binaryTreePrint(root, 2);
		System.out.println("\n后序遍历：");
		binaryTreePrint(root, 3);
	}
}

/**
 * @author ZhouJie
 * @date 2019年12月1日 下午8:21:11 
 * @Description: 树节点
 *
 */
class Node {
	public int value;
	public Node left;
	public Node right;

	public Node(int value) {
		this.value = value;
	}

}
