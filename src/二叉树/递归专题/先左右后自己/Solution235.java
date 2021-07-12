package 二叉树.递归专题.先左右后自己;

import 二叉树.递归专题.数据结构.TreeNode;

/**
 * @作者: one者天下
 * @时间: 2021/5/1 12:13
 * @描述: 二叉搜索树节点的最近祖先
 */
public class Solution235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left,q,p);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right,q,p);
        return root;
    }
}
