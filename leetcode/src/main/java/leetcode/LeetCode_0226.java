package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月10日 下午10:31:54 
 * @Description: 226. 翻转二叉树
 *
	翻转一棵二叉树。
	
	示例：
	
	输入：
	
	     4
	   /   \
	  2     7
	 / \   / \
	1   3 6   9
	输出：
	
	     4
	   /   \
	  7     2
	 / \   / \
	9   6 3   1
	备注:
	这个问题是受到 Max Howell 的 原问题 启发的 ：
	
	谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/invert-binary-tree
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-递归翻转；
 */
public class LeetCode_0226 {

}

//Definition for a binary tree node.
class TreeNode_0226 {
	int val;
	TreeNode_0226 left;
	TreeNode_0226 right;

	TreeNode_0226(int x) {
		val = x;
	}
}

class Solution_0226 {
	public TreeNode_0226 invertTree(TreeNode_0226 root) {
		// 节点不为null时，左右节点交换，然后左右各自递归
		if (root != null) {
			TreeNode_0226 temp;
			temp = root.left;
			root.left = root.right;
			root.right = temp;
			invertTree(root.left);
			invertTree(root.right);
		}
		// 节点为null返回自身，该层递归结束
		return root;
	}
}
