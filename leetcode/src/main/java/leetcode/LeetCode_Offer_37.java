package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午8:10:03 
 * @Description: 面试题37. 序列化二叉树
 * 
	请实现两个函数，分别用来序列化和反序列化二叉树。
	
	示例: 
	
	你可以将以下二叉树：
	
	    1
	   / \
	  2   3
	     / \
	    4   5
	
	序列化为 "[1,2,3,null,null,4,5]"
	注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LeetCode_Offer_37 {

}

//  Definition for a binary tree node.
class TreeNode_Offer_37 {
	int val;
	TreeNode_Offer_37 left;
	TreeNode_Offer_37 right;

	TreeNode_Offer_37(int x) {
		val = x;
	}
}

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午10:48:39 
 * @Description: 1-层次遍历/BFS，使用LinkedList记录节点；
 *
 */
class Codec_Offer_37_1 {

	// Encodes a tree to a single string.
	public String serialize(TreeNode_Offer_37 root) {
		if (root == null) {
			return "";
		}
		LinkedList<TreeNode_Offer_37> queue = new LinkedList<TreeNode_Offer_37>();
		StringBuilder sb = new StringBuilder("[");
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode_Offer_37 node = queue.poll();
			if (node != null) {
				sb.append(node.val);
				queue.offer(node.left);
				queue.offer(node.right);
			} else {
				sb.append("null");
			}
			sb.append(",");
		}
		return sb.deleteCharAt(sb.length() - 1).append("]").toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode_Offer_37 deserialize(String data) {
		if (data == null || data.isEmpty()) {
			return null;
		}
		String[] nodeArray = data.substring(1, data.length() - 1).split(",");
		int i = 0;
		TreeNode_Offer_37 root = buildeNode(nodeArray[i++]);
		LinkedList<TreeNode_Offer_37> queue = new LinkedList<TreeNode_Offer_37>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode_Offer_37 node = queue.poll();
			node.left = buildeNode(nodeArray[i++]);
			node.right = buildeNode(nodeArray[i++]);
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		return root;
	}

	private TreeNode_Offer_37 buildeNode(String s) {
		if (Objects.equals(s, "null")) {
			return null;
		} else {
			return new TreeNode_Offer_37(Integer.valueOf(s));
		}
	}
}

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午11:24:23 
 * @Description: 2-DFS
 *
 */
class Codec_Offer_37_2 {

	// Encodes a tree to a single string.
	public String serialize(TreeNode_Offer_37 root) {
		return serialize(root, new StringBuilder());
	}

	private String serialize(TreeNode_Offer_37 root, StringBuilder sb) {
		if (root == null) {
			sb.append("null,");
		} else {
			sb.append(root.val).append(",");
			serialize(root.left, sb);
			serialize(root.right, sb);
		}
		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode_Offer_37 deserialize(String data) {
		return deserialize(new LinkedList<String>(Arrays.asList(data.split(","))));
	}

	private TreeNode_Offer_37 deserialize(LinkedList<String> list) {
		String s = list.get(0);
		list.remove(0);
		if (Objects.equals(s, "null")) {
			return null;
		} else {
			TreeNode_Offer_37 root = buildeNode(s);
			root.left = deserialize(list);
			root.right = deserialize(list);
			return root;
		}
	}

	private TreeNode_Offer_37 buildeNode(String s) {
		if (Objects.equals(s, "null")) {
			return null;
		} else {
			return new TreeNode_Offer_37(Integer.valueOf(s));
		}
	}
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));