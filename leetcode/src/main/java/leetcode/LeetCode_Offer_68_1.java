package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月13日 下午12:17:30 
 * @Description: 面试题68 - I. 二叉搜索树的最近公共祖先
 *
	给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
	
	百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
	
	例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]

	示例 1:
	
	输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
	输出: 6 
	解释: 节点 2 和节点 8 的最近公共祖先是 6。
	示例 2:
	
	输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
	输出: 2
	解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
	 
	
	说明:
	
	所有节点的值都是唯一的。
	p、q 为不同节点且均存在于给定的二叉搜索树中。
	注意：本题与主站 235 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_68_1 {

}

//  Definition for a binary tree node.
class TreeNode_Offer_68_1 {
	int val;
	TreeNode_Offer_68_1 left;
	TreeNode_Offer_68_1 right;

	TreeNode_Offer_68_1(int x) {
		val = x;
	}
}

class Solution_Offer_68_1 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月13日 下午12:18:56 
	 * @param: @param root
	 * @param: @param p
	 * @param: @param q
	 * @param: @return
	 * @return: TreeNode_Offer_68_1
	 * @Description: 1-二叉搜索树的特性，父节点大于左节点小于右节点
	 *
	 */
	public TreeNode_Offer_68_1 lowestCommonAncestor_1(TreeNode_Offer_68_1 root, TreeNode_Offer_68_1 p,
			TreeNode_Offer_68_1 q) {
		if (root == null) {
			return root;
			// pq值均大于root值，则祖节点在左子树中
		} else if (root.val > p.val && root.val > q.val) {
			return lowestCommonAncestor_1(root.left, p, q);
			// pq值均小于root值，则祖节点在右子树中
		} else if (root.val < p.val && root.val < q.val) {
			return lowestCommonAncestor_1(root.right, p, q);
			// pq值其一等于root值
		} else {
			return root;
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月13日 下午12:21:43 
	 * @param: @param root
	 * @param: @param p
	 * @param: @param q
	 * @param: @return
	 * @return: TreeNode_Offer_68_1
	 * @Description: 2-直接递归校验节点；
	 *
	 */
	public TreeNode_Offer_68_1 lowestCommonAncestor_2(TreeNode_Offer_68_1 root, TreeNode_Offer_68_1 p,
			TreeNode_Offer_68_1 q) {
		if (root == null || root == p || root == q) {
			return root;
		} else {
			TreeNode_Offer_68_1 left = lowestCommonAncestor_2(root.left, p, q);
			TreeNode_Offer_68_1 right = lowestCommonAncestor_2(root.right, p, q);
			// 可以一行返回，但是可读性不好
			// return left == null ? right : (right == null ? left : root);
			if (left == null) {
				return right;
			} else if (right == null) {
				return left;
			} else {
				return root;
			}
		}
	}
}
