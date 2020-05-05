package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月2日 下午11:11:17 
 * @Description: 面试题27. 二叉树的镜像
 *
	请完成一个函数，输入一个二叉树，该函数输出它的镜像。
	
	例如输入：
	
	     4
	   /   \
	  2     7
	 / \   / \
	1   3 6   9
	镜像输出：
	
	     4
	   /   \
	  7     2
	 / \   / \
	9   6 3   1
	
	 
	
	示例 1：
	
	输入：root = [4,2,7,1,3,6,9]
	输出：[4,7,2,9,6,3,1]
	 
	
	限制：
	
	0 <= 节点个数 <= 1000
	
	注意：本题与主站 226 题相同：https://leetcode-cn.com/problems/invert-binary-tree/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_27 {

}

// Definition for a binary tree node.
class TreeNode_Offer_27 {
	int val;
	TreeNode_Offer_27 left;
	TreeNode_Offer_27 right;

	TreeNode_Offer_27(int x) {
		val = x;
	}
}

class Solution_Offer_27 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月2日 下午11:14:26 
	 * @param: @param root
	 * @param: @return
	 * @return: TreeNode_Offer_27
	 * @Description: 1-递归互换；
	 *
	 */
	public TreeNode_Offer_27 mirrorTree(TreeNode_Offer_27 root) {
		if (root != null) {
			TreeNode_Offer_27 l = root.left;
			TreeNode_Offer_27 r = root.right;
			root.left = mirrorTree(r);
			root.right = mirrorTree(l);
		}
		return root;
	}
}