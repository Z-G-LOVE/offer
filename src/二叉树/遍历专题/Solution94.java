package 二叉树.遍历专题;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* *
 * @作者: one者天下
 * @时间: 2021/4/24 20:47
 * @描述: 二叉树的中序遍历
 */
public class Solution94 {
    private List<Integer> list = new ArrayList<>();
    // 递归解决
    public List<Integer> inorderTraversal(TreeNode root) {
        help(root);
        return list;
    }
    public void help(TreeNode root){
       if (root == null)return;
       else {
          if (root.left != null)help(root.left);
          list.add(root.val);
          if (root.right != null)help((root.right));
       }
    }
    // 非递归解决方案一:使用栈的中序遍历
    public List<Integer> nonInorderTraversal1(TreeNode root){
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null){
                cur = node.right;
            }
        }
     return list;
    }
    // 使用节点遍历
    public List<Integer> nonInorderTraversal2(TreeNode root){
        if (root == null) return list;
        TreeNode cur1 = root;// 指向当前的节点
        TreeNode cur2 ;// 指向当前节点的左子树
        while (cur1 != null){
            cur2 = cur1.left;
            if (cur2 != null){
                while (cur2.right != null && cur2.right != cur1) cur2 = cur2.right;
                if (cur2.right  == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }else {
                    cur2.right = null;
                }
            }
            list.add(cur1.val);
            cur1 = cur1.right;
        }
        return list;
    }
}


