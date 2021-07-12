package 二叉树.递归专题.先自己后左右;

import 二叉树.递归专题.数据结构.TreeNode;

/**
 * @作者: one者天下
 * @时间: 2021/5/3 14:11
 * @描述: 二叉树的最小深度
 */
public class Solution111 {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null && root.right != null)
        return 1 + Math.min(minDepth(root.left),minDepth(root.right));
        else if (root.left == null) return 1 + minDepth(root.right);
        else return 1 + minDepth(root.left);
    }
}
