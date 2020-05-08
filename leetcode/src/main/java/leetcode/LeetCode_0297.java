package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午11:42:01 
 * @Description: 297. 二叉树的序列化与反序列化
 *
	序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
	
	请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
	
	示例: 
	
	你可以将以下二叉树：
	
	    1
	   / \
	  2   3
	     / \
	    4   5
	
	序列化为 "[1,2,3,null,null,4,5]"
	提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
	
	说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0297 {

}

//Definition for a binary tree node.
class TreeNode_0297 {
	int val;
	TreeNode_0297 left;
	TreeNode_0297 right;

	TreeNode_0297(int x) {
		val = x;
	}
}

/**
* @author ZhouJie
* @date 2020年5月3日 下午10:48:39 
* @Description: 1-层次遍历/BFS，使用LinkedList记录节点；
*
*/
class Codec_297_1 {

// Encodes a tree to a single string.
	public String serialize(TreeNode_0297 root) {
		if (root == null) {
			return "";
		}
		LinkedList<TreeNode_0297> queue = new LinkedList<TreeNode_0297>();
		StringBuilder sb = new StringBuilder("[");
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode_0297 node = queue.poll();
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
	public TreeNode_0297 deserialize(String data) {
		if (data == null || data.isEmpty()) {
			return null;
		}
		String[] nodeArray = data.substring(1, data.length() - 1).split(",");
		int i = 0;
		TreeNode_0297 root = buildeNode(nodeArray[i++]);
		LinkedList<TreeNode_0297> queue = new LinkedList<TreeNode_0297>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode_0297 node = queue.poll();
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

	private TreeNode_0297 buildeNode(String s) {
		if (Objects.equals(s, "null")) {
			return null;
		} else {
			return new TreeNode_0297(Integer.valueOf(s));
		}
	}
}

/**
* @author ZhouJie
* @date 2020年5月3日 下午11:24:23 
* @Description: 2-DFS
*
*/
class Codec_297_2 {

// Encodes a tree to a single string.
	public String serialize(TreeNode_0297 root) {
		return serialize(root, new StringBuilder());
	}

	private String serialize(TreeNode_0297 root, StringBuilder sb) {
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
	public TreeNode_0297 deserialize(String data) {
		return deserialize(new LinkedList<String>(Arrays.asList(data.split(","))));
	}

	private TreeNode_0297 deserialize(LinkedList<String> list) {
		String s = list.get(0);
		list.remove(0);
		if (Objects.equals(s, "null")) {
			return null;
		} else {
			TreeNode_0297 root = buildeNode(s);
			root.left = deserialize(list);
			root.right = deserialize(list);
			return root;
		}
	}

	private TreeNode_0297 buildeNode(String s) {
		if (Objects.equals(s, "null")) {
			return null;
		} else {
			return new TreeNode_0297(Integer.valueOf(s));
		}
	}
}
//Your Codec object will be instantiated and called as such:
//Codec codec = new Codec();
//codec.deserialize(codec.serialize(root));