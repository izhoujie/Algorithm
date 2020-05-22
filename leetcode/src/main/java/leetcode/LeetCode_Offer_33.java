package leetcode;

import java.util.Stack;

/**
 * @author ZhouJie
 * @date 2020年5月22日 下午6:31:34 
 * @Description: 面试题33. 二叉搜索树的后序遍历序列
 *
	输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
	
	参考以下这颗二叉搜索树：
	
	     5
	    / \
	   2   6
	  / \
	 1   3
	示例 1：
	
	输入: [1,6,3,2,5]
	输出: false
	示例 2：
	
	输入: [1,3,2,6,5]
	输出: true
	 
	提示：
	
	数组长度 <= 1000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_33 {

}

class Solution_Offer_33 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月22日 下午6:32:04 
	 * @param: @param postorder
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-后序遍历：left->right->root；
	 * 				数组最后一个元素是根元素，数组靠前一段是左子树，靠后一段是右子树；
	 * 				二叉搜索树的特性，根元素大于左子树小于右子树，以此校验；
	 * 				初始的左右子树分割后的部分也符合校验模式，故可递归校验处理；
	 *
	 */
	public boolean verifyPostorder_1(int[] postorder) {
		return verifyHelper(postorder, 0, postorder.length - 1);
	}

	private boolean verifyHelper(int[] postorder, int start, int end) {
		if (start >= end) {
			return true;
		} else {
			int i = start;
			// 左子树都小于根节点
			while (i < end && postorder[i] < postorder[end]) {
				i++;
			}
			int j = i;
			// 右子树都大于根节点，一旦小于则直接返回
			while (j < end) {
				if (postorder[j] < postorder[end]) {
					return false;
				}
				j++;
			}
			// 以i分割左右子树递归校验
			return verifyHelper(postorder, start, i - 1) && verifyHelper(postorder, i, end - 1);
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月22日 下午7:18:15 
	 * @param: @param postorder
	 * @param: @return
	 * @return: boolean
	 * @Description: 2-单调辅助栈校验；
	 * 				后序遍历：left->right->root；
	 * 				若对数组逆序遍历，则对应节点顺序为：root->right->left；
	 * 				root作为栈最底的元素，先校验右子树都大于root的部分，当遇到小于栈顶的元素时，表明到了左子树，此时清空栈开始处理左子树；
	 *
	 */
	public boolean verifyPostorder_(int[] postorder) {
		Stack<Integer> stack = new Stack<Integer>();
		// 初始化根节点
		int root = Integer.MAX_VALUE;
		for (int i = postorder.length - 1; i > -1; i--) {
			// 若左子树中有大于根节点的值，直接返回false
			if (postorder[i] > root) {
				return false;
			}
			// 若当前值小于栈顶的值，说明右子树已经遍历完毕，栈中都是右子树，清空栈，但记录根元素，然后左子树入栈
			while (!stack.isEmpty() && postorder[i] < stack.peek()) {
				root = stack.pop();
			}
			// 若栈为空是右子树入栈，否则为左子树入栈
			stack.push(postorder[i]);
		}
		return true;
	}

}
