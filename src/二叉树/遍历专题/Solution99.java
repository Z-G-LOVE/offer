package 二叉树.遍历专题;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @作者: one者天下
 * @时间: 2021/4/26 20:27
 * @描述: 恢复二叉搜索树
 */
public class Solution99 {
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            if (list.size() > 0 && list.get(list.size()-1).val > node.val){
                TreeNode tag = node;
              int i = list.size()-1;
              while (i >= 0 && list.get(i).val > node.val){
                  TreeNode treeNode = list.get(i);
                  int temp = treeNode.val;
                  treeNode.val = node.val;
                  node.val = temp;
                  node = treeNode;
                  i--;
              }
              node = tag;
            }
            list.add(node);
            if (node.right != null){
                cur = node.right;
            }
        }
    }

    public void recoverTree2(TreeNode root){
        Stack<TreeNode> stack =  new Stack<>();
        TreeNode node1 = null;// 第一个出错的节点
        TreeNode node2 = null;// 第二个出错的节点
        TreeNode per = null;// 当前遍历的前一个节点
        TreeNode cur = root;// 当前遍历的节点
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode treeNode = stack.pop();
            if (per == null) per = treeNode;
            else {
                if (per.val > treeNode.val) {
                    if (node1 == null) node1 = per;
                    else node2 = treeNode;
                }
                per = treeNode;
            }
            if (treeNode.right != null){
                cur = treeNode.right;
            }
        }
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    private TreeNode node1 = null;
    private TreeNode node2 = null;
    private TreeNode pre = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree3(TreeNode root){
        help(root);
    }
    private void help(TreeNode root){
        if (root == null) return;
        if (root.left != null) help(root.left);
        if (node1 == null && pre.val > root.val) node1 = pre;
        if (node1 != null && pre.val > root.val) node2 = root;
        pre = root;
        if (root.right != null) help(root.right);
    }
    @Test
    public void test(){
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(1);
        root.left = left;
        root.right = right;
        recoverTree2(root);
    }
}
