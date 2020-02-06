package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月12日 上午3:11:43 
 * @Description: 654. 最大二叉树
 *
	给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
	
	二叉树的根是数组中的最大元素。
	左子树是通过数组中最大值左边部分构造出的最大二叉树。
	右子树是通过数组中最大值右边部分构造出的最大二叉树。
	通过给定的数组构建最大二叉树，并且输出这个树的根节点。
	
	 
	
	示例 ：
	
	输入：[3,2,1,6,0,5]
	输出：返回下面这棵树的根节点：
	
	      6
	    /   \
	   3     5
	    \    / 
	     2  0   
	       \
	        1
	 
	
	提示：
	
	给定的数组的大小在 [1, 1000] 之间。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/maximum-binary-tree
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-先找到最大值位置，然后递归构建左右子树；
 */
public class LeetCode_0654 {

}

//  Definition for a binary tree node.
class TreeNode_0654 {
	int val;
	TreeNode_0654 left;
	TreeNode_0654 right;

	TreeNode_0654(int x) {
		val = x;
	}
}

class Solution_0654 {
	/**
	 * @author ZhouJie
	 * @date 2020年1月12日 下午6:18:33 
	 * @Description: TODO(方法简述) 
	 * @return TreeNode_0654 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月12日 下午6:18:33]  
	 * @UpdateRemark:每次找当前区间的最大值构造节点，然后左右子树递归处理； 
	 *
	 */
	public TreeNode_0654 constructMaximumBinaryTree_1(int[] nums) {
		if (nums == null) {
			return null;
		}
		return makeTree_1(nums, 0, nums.length - 1);
	}

	/**
	 * @author ZhouJie
	 * @date 2020年1月12日 下午6:19:43 
	 * @Description: TODO(方法简述) 
	 * @return TreeNode_0654 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月12日 下午6:19:43]  
	 * @UpdateRemark:方法1的递归构造树 
	 *
	 */
	private TreeNode_0654 makeTree_1(int[] nums, int left, int right) {
		int mid = left;
		for (int i = left; i <= right; i++) {
			if (nums[mid] < nums[i]) {
				mid = i;
			}
		}
		TreeNode_0654 root = new TreeNode_0654(nums[mid]);
		if (mid > left) {
			root.left = makeTree_1(nums, left, mid - 1);
		}
		if (mid < right) {
			root.right = makeTree_1(nums, mid + 1, right);
		}
		return root;
	}

	private TreeNode_0654 root;

	/**
	 * @author ZhouJie
	 * @date 2020年1月12日 下午6:34:06 
	 * @Description: TODO(方法简述) 
	 * @return TreeNode_0654 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月12日 下午6:34:06]  
	 * @UpdateRemark: leetcode优秀解法，不用每次for遍历寻找最大值构造节点，而是直接构造节点递归构造调整树，赞；
	 *
	 */
	public TreeNode_0654 constructMaximumBinaryTree_2(int[] nums) {
		if (nums == null) {
			return root;
		}
		makeTree_2(nums, 0);
		return root;
	}

	private void makeTree_2(int[] nums, int index) {
		if (index == nums.length) {
			return;
		}
		TreeNode_0654 now = new TreeNode_0654(nums[index]);
		// 初次构造根节点或后续新节点一旦比当前节点大，则now作为root，原root作为now的左节点[即构造左子树为最大二叉树]
		if (root == null || root.val < now.val) {
			now.left = root;
			root = now;
		} else {
			// 若now小于root，则在root的右节点中寻找now的合适位置[即构造右子树为最大二叉树]
			TreeNode_0654 parent = root;
			TreeNode_0654 right = root.right;
			// 递归寻找第一个小于now的节点
			while (right != null) {
				if (right.val > now.val) {
					parent = right;
					right = right.right;
				} else {
					break;
				}
			}
			// now作为parent的右节点，parent的右节点作为now的左节点，这里搞清楚需要多思考，可以多画画对应关系
			parent.right = now;
			now.left = right;
		}
		makeTree_2(nums, index + 1);
	}
}