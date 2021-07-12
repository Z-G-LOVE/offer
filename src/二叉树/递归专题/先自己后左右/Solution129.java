package 二叉树.递归专题.先自己后左右;

import 二叉树.递归专题.数据结构.TreeNode;

/**
 * @作者: one者天下
 * @时间: 2021/5/3 16:29
 * @描述: 根节点到叶节点之和
 */
public class Solution129 {
    public int sumNumbers(TreeNode root) {
        return dfs(root,0);
    }
    private int dfs(TreeNode root,int i/*记录当前节点加上父节点的值*/){
        if (root == null) return 0;
        int temp = i * 10 + root.val;
        if (root.left == null && root.right == null) return temp;
        return dfs(root.left,temp) + dfs(root.right,temp);
    }
}
