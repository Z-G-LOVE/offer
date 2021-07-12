package 二叉树.遍历专题;

import java.util.Stack;

/**
 * @作者: one者天下
 * @时间: 2021/4/27 17:24
 * @描述: 二叉树展开
 */
public class Solution114 {
    public void flatten(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;// 表示展开后链表的前一个节点
        TreeNode head = null;
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            TreeNode node = new TreeNode(cur.val);
            if (pre == null) {
                pre = node;
                head = pre;
            }
            else {
                pre.right = node;
                pre = node;
            }
            if (root.right != null) stack.push(root.right);
            if (root.left != null) stack.push(root.left);
        }
        if (head != null){
            root.left = null;
            root.right = head.right;
        }
    }
}
