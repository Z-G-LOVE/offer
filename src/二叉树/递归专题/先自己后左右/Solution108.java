package 二叉树.递归专题.先自己后左右;

import 二叉树.递归专题.数据结构.TreeNode;

/**
 * @作者: one者天下
 * @时间: 2021/5/3 11:15
 * @描述: 将有序数组转换为一棵高度平衡的二叉搜索树
 */
public class Solution108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return createTree(0,nums.length-1,nums);
    }
    private TreeNode createTree(int start,int end,int[] nums){
        if (start > end) return null;
        int root_index = (start + end) / 2;
        int val = nums[root_index];
        TreeNode root = new TreeNode(val);
        TreeNode left = createTree(start,root_index-1,nums);
        TreeNode right = createTree(root_index+1,end,nums);
        root.left = left;
        root.right = right;
        return root;
    }
}
