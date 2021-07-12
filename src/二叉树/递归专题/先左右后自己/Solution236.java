package 二叉树.递归专题.先左右后自己;

import org.junit.Test;
import 二叉树.递归专题.数据结构.TreeNode;

/**
 * @作者: one者天下
 * @时间: 2021/5/1 14:40
 * @描述: 二叉树的最近祖先节点
 */
public class Solution236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q || root == null) return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (left != null && right != null) return root;
        if (left != null && right == null) return left;
        else if (left == null && right != null) return right;
        else return null;
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        lowestCommonAncestor(root,node3,node4);
    }
}
