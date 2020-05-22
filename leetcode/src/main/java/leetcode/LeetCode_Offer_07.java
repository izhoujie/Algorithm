package leetcode;

import java.util.Arrays;

/**
 * @author ZhouJie
 * @date 2020年5月22日 下午3:33:07 
 * @Description: 面试题07. 重建二叉树
 *
	输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
	
	例如，给出
	
	前序遍历 preorder = [3,9,20,15,7]
	中序遍历 inorder = [9,3,15,20,7]
	返回如下的二叉树：
	
	    3
	   / \
	  9  20
	    /  \
	   15   7
	 
	
	限制：
	
	0 <= 节点个数 <= 5000
	
	 
	
	注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_07 {

}

//Definition for a binary tree node.
class TreeNode_Offer_07 {
	int val;
	TreeNode_Offer_07 left;
	TreeNode_Offer_07 right;

	TreeNode_Offer_07(int x) {
		val = x;
	}
}

class Solution_Offer_07 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月22日 下午1:29:25 
	 * @param: @param preorder
	 * @param: @param inorder
	 * @param: @return
	 * @return: TreeNode_Offer_07
	 * @Description: 1-分割数组对应到子树递归处理；
	 *
	 */
	public TreeNode_Offer_07 buildTree_1(int[] preorder, int[] inorder) {
		if (preorder.length == 0 || inorder.length == 0) {
			return null;
		} else {
			TreeNode_Offer_07 root = new TreeNode_Offer_07(preorder[0]);
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
	 * @return: TreeNode_Offer_07
	 * @Description: 2-直接在数组上递归，不分割数组，效率高；
	 *
	 */
	private int indexPreorder = 0;
	private int indexInorder = 0;

	public TreeNode_Offer_07 buildTree_2(int[] preorder, int[] inorder) {
		return buildHelper(preorder, inorder, null);
	}

	private TreeNode_Offer_07 buildHelper(int[] preorder, int[] inorder, TreeNode_Offer_07 node) {
		if (node != null && node.val == inorder[indexInorder] || indexInorder == preorder.length) {
			return null;
		}
		// 开始构建左子树
		TreeNode_Offer_07 root = new TreeNode_Offer_07(preorder[indexPreorder]);
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