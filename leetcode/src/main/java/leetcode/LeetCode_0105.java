package leetcode;

import java.util.Arrays;

/**
 * @author ZhouJie
 * @date 2020年5月22日 下午1:28:22 
 * @Description: 105. 从前序与中序遍历序列构造二叉树
 *
	根据一棵树的前序遍历与中序遍历构造二叉树。
	
	注意:
	你可以假设树中没有重复的元素。
	
	例如，给出
	
	前序遍历 preorder = [3,9,20,15,7]
	中序遍历 inorder = [9,3,15,20,7]
	返回如下的二叉树：
	
	    3
	   / \
	  9  20
	    /  \
	   15   7
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0105 {

}

//  Definition for a binary tree node.
class TreeNode_0105 {
	int val;
	TreeNode_0105 left;
	TreeNode_0105 right;

	TreeNode_0105(int x) {
		val = x;
	}
}

class Solution_0105 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月22日 下午1:29:25 
	 * @param: @param preorder
	 * @param: @param inorder
	 * @param: @return
	 * @return: TreeNode_0105
	 * @Description: 1-分割数组对应到子树递归处理；
	 *
	 */
	public TreeNode_0105 buildTree_1(int[] preorder, int[] inorder) {
		if (preorder.length == 0 || inorder.length == 0) {
			return null;
		} else {
			TreeNode_0105 root = new TreeNode_0105(preorder[0]);
			for (int i = 0; i < inorder.length; i++) {
				if (preorder[0] == inorder[i]) {
					root.left = buildTree_1(Arrays.copyOfRange(preorder, 1, i + 1), Arrays.copyOfRange(inorder, 0, i));
					root.right = buildTree_1(Arrays.copyOfRange(preorder, i + 1, preorder.length),
							Arrays.copyOfRange(inorder, i + 1, inorder.length));
					break;
				}
			}
			return root;
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月22日 下午2:02:20 
	 * @param: @param preorder
	 * @param: @param inorder
	 * @param: @return
	 * @return: TreeNode_0105
	 * @Description: 2-直接在数组上递归，不分割数组，效率高；
	 *
	 */
	private int indexPreorder = 0;
	private int indexInorder = 0;

	public TreeNode_0105 buildTree_2(int[] preorder, int[] inorder) {
		return buildHelper(preorder, inorder, null);
	}

	private TreeNode_0105 buildHelper(int[] preorder, int[] inorder, TreeNode_0105 node) {
		if (node != null && node.val == inorder[indexInorder] || indexInorder == preorder.length) {
			return null;
		}
		// 开始构建左子树
		TreeNode_0105 root = new TreeNode_0105(preorder[indexPreorder]);
		// 先从preorder开始处理，当处理到等于inorder[0]的节点时，表明根的左子树已经构建完成，可以开始递归构建根的右子树了
		if (preorder[indexPreorder] == inorder[indexInorder]) {
			indexPreorder++;
			indexInorder++;
		} else {
			indexPreorder++;
			root.left = buildHelper(preorder, inorder, root);
			indexInorder++;
		}
		// 对于根而言，开始右子树的递归构建
		root.right = buildHelper(preorder, inorder, node);
		return root;
	}
}