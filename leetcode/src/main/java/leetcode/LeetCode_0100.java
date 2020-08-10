package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月1日 下午7:36:40 
 * @Description: 100. 相同的树
 *
	给定两个二叉树，编写一个函数来检验它们是否相同。
	
	如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
	
	示例 1:
	
	输入:       	   1         1
	          / \       / \
	         2   3     2   3
	
	        [1,2,3],   [1,2,3]
	
	输出: true
	示例 2:
	
	输入:      	   1          1
	          /           \
	         2             2
	
	        [1,2],     [1,null,2]
	
	输出: false
	示例 3:
	
	输入:                  1         1
	          / \       / \
	         2   1     1   2
	
	        [1,2,1],   [1,1,2]
	
	输出: false
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/same-tree
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LeetCode_0100 {

}

//  Definition for a binary tree node.
class TreeNode_0100 {
	int val;
	TreeNode_0100 left;
	TreeNode_0100 right;

	TreeNode_0100(int x) {
		val = x;
	}
}

class Solution_0100 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月1日 下午7:43:01 
	 * @param: @param p
	 * @param: @param q
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-递归；
	 *
	 */
	public boolean isSameTree(TreeNode_0100 p, TreeNode_0100 q) {
		if (p == null && q == null) {
			return true;
		} else if (p != null && q != null && p.val != q.val) {
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		} else {
			return false;
		}
	}
}