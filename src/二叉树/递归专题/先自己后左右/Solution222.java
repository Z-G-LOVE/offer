package 二叉树.递归专题.先自己后左右;

import org.junit.Test;
import 二叉树.递归专题.数据结构.TreeNode;

/**
 * @作者: one者天下
 * @时间: 2021/5/3 16:50
 * @描述: 完全二叉树的节点个数
 */
public class Solution222 {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        countNodes(root);
    }
}
