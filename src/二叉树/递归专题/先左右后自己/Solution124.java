package 二叉树.递归专题.先左右后自己;

import 二叉树.递归专题.数据结构.TreeNode;

/**
 * @作者: one者天下
 * @时间: 2021/5/1 09:23
 * @描述: 二叉树的最大路径和
 */
public class Solution124 {
    // 最终结果
    private int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }
    // 递归调用方法,返回该节点的最大路径和
    private int dfs(TreeNode root){
        if (root == null) return 0;
        int left = Math.max(dfs(root.left),0);// 左子树的最大路径和
        int right = Math.max(dfs(root.right),0);// 右子树的最大路径和
        res = Math.max(res,root.val+left+right);// 更新历史最佳结果
        return root.val + Math.max(left,right);
    }
}
