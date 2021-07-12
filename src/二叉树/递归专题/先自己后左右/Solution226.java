package 二叉树.递归专题.先自己后左右;

import 二叉树.递归专题.数据结构.TreeNode;

/**
 * @作者: one者天下
 * @时间: 2021/5/3 21:11
 * @描述: 翻转二叉树
 */
public class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        dfs(root);
        return root;
    }
    private void dfs(TreeNode root){
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        dfs(root.left);
        dfs(root.right);
    }
}
