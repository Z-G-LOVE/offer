package 二叉树.递归专题.先自己后左右;

import org.junit.Test;
import 二叉树.递归专题.数据结构.TreeNode;

/**
 * @作者: one者天下
 * @时间: 2021/5/3 13:49
 * @描述: 判断平衡二叉树
 */
public class Solution110 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(treeHeight(root.left) - treeHeight(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int treeHeight(TreeNode node){
        if (node == null) return 0;
        return 1 + Math.max(treeHeight(node.left),treeHeight(node.right));
    }
    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.right = node4;
        node4.left = node5;
        node5.right = node6;

        int left = treeHeight(root.left);
        int right = treeHeight(root.right);

        System.out.println(left);
        System.out.println(right);
        boolean b = Math.abs(left - right) <= 1;
        System.out.println(b);
    }
}
