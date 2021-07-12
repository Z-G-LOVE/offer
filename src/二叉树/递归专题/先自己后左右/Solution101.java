package 二叉树.递归专题.先自己后左右;

import 二叉树.递归专题.数据结构.TreeNode;

import java.util.List;

/**
 * @作者: one者天下
 * @时间: 2021/5/3 21:23
 * @描述: 对称二叉树
 */
public class Solution101 {
    public boolean isSymmetric(TreeNode root) {
        if (root  == null) return true;
        return dfs(root.left,root.right);
    }
    // 中序遍历
    private void inorder(TreeNode root, List<Integer> list){
        if (root == null) return;
        inorder(root.left,list);
        list.add(root.val);
        inorder(root.right,list);
    }
    // 翻转二叉树
    private void flip(TreeNode root){
        if (root == null) return;
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        flip(root.left);
        flip(root.right);
    }
    private boolean dfs(TreeNode left,TreeNode right){
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return dfs(left.left,right.right) && dfs(left.right,right.left) && left.val == right.val;
    }
}
