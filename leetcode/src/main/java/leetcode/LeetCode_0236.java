package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月7日 下午6:00:19 
 * @Description: 236. 二叉树的最近公共祖先
 *
	给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
	
	百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
	
	例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
	
	
	
	 
	
	示例 1:
	
	输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
	输出: 3
	解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
	示例 2:
	
	输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
	输出: 5
	解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
	 
	
	说明:
	
	所有节点的值都是唯一的。
	p、q 为不同节点且均存在于给定的二叉树中。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-
 */
public class LeetCode_0236 {

}

//  Definition for a binary tree node.
class TreeNode_0236 {
	int val;
	TreeNode_0236 left;
	TreeNode_0236 right;

	TreeNode_0236(int x) {
		val = x;
	}
}

class Solution_0236 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月7日 下午6:20:23 
	 * @param: @param root
	 * @param: @param p
	 * @param: @param q
	 * @param: @return
	 * @return: TreeNode_0236
	 * @Description: 1-递归校验，走了弯路...
	 *
	 */
	public TreeNode_0236 lowestCommonAncestor_1(TreeNode_0236 root, TreeNode_0236 p, TreeNode_0236 q) {
		if (root == null) {
			return null;
		} else if (root == p || root == q) {
			return root;
		} else {
			boolean f1 = check(root.left, p);
			boolean f2 = check(root.right, q);
			if (f1 == !f2) {
				if (f1) {
					return lowestCommonAncestor_1(root.left, p, q);
				} else {
					return lowestCommonAncestor_1(root.right, p, q);
				}
			} else {
				return root;
			}
		}
	}

	private boolean check(TreeNode_0236 root, TreeNode_0236 node) {
		if (root == null) {
			return false;
		} else if (root == node) {
			return true;
		} else {
			return check(root.left, node) || check(root.right, node);
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月7日 下午6:20:19 
	 * @param: @param root
	 * @param: @param p
	 * @param: @param q
	 * @param: @return
	 * @return: TreeNode_0236
	 * @Description: 2-还是递归，优化后；
	 * 				理解：如果在两侧，则递归的left和right均不为null，返回root，否则，谁不为null则说明谁必为祖节点
	 *
	 */
	public TreeNode_0236 lowestCommonAncestor_2(TreeNode_0236 root, TreeNode_0236 p, TreeNode_0236 q) {
		if (root == null || root == p || root == q) {
			return root;
		} else {
			TreeNode_0236 left = lowestCommonAncestor_2(root.left, p, q);
			TreeNode_0236 right = lowestCommonAncestor_2(root.right, p, q);
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