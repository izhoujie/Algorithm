package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月12日 上午2:46:33 
 * @Description: 617. 合并二叉树 
 *
	给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
	
	你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
	
	示例 1:
	
	输入: 
		Tree 1                     Tree 2                  
	          1                         2                             
	         / \                       / \                            
	        3   2                     1   3                        
	       /                           \   \                      
	      5                             4   7                  
	输出: 
	合并后的树:
		     3
		    / \
		   4   5
		  / \   \ 
		 5   4   7
	注意: 合并必须从两个树的根节点开始。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/merge-two-binary-trees
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-递归解决，左右子树各自递归；
 */
public class LeetCode_0617 {

}

// Definition for a binary tree node.
class TreeNode_0617 {
	int val;
	TreeNode_0617 left;
	TreeNode_0617 right;

	TreeNode_0617(int x) {
		val = x;
	}
}

class Solution_0617 {
	public TreeNode_0617 mergeTrees(TreeNode_0617 t1, TreeNode_0617 t2) {
		// 为空时返回对方节点，终结条件
		if (t1 == null) {
			return t2;
		}
		if (t2 == null) {
			return t1;
		}
		// 非空时计算节点和到t1上，使用t1作为合并后的树返回
		t1.val += t2.val;
		// 对应左右节点分别递归合并到t1节点上
		t1.left = mergeTrees(t1.left, t2.left);
		t1.right = mergeTrees(t1.right, t2.right);
		return t1;
	}
}
