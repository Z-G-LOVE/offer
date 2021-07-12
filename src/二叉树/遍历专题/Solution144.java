package 二叉树.遍历专题;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @作者: one者天下
 * @时间: 2021/4/24 20:04
 * @描述: 二叉树的前序遍历
 */
public class Solution144 {
    private List<Integer> list = new ArrayList<>();
    // 递归实现
    public List<Integer> preorderTraversal(TreeNode root) {
        help(root);
        return list;
    }
    public void help(TreeNode root){
        if (root == null){
            return;
        }else {
            list.add(root.val);
            help(root.left);
            help(root.right);
        }
    }

    // 非递归实现：使用栈遍历
    public List<Integer> nonPreorderTraversal(TreeNode root){
        if (root == null)return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
        return list;
    }
    // 使用节点遍历
    public List<Integer> nonPreorderTraversal2(TreeNode root){
        if (root == null) return list;
        TreeNode cur1 = root;// 指向当前节点
        TreeNode cur2 ;// 指向当前节点的左子树
        while (cur1 != null){
            cur2 = cur1.left;
            if (cur2 != null){
                while (cur2.right != null && cur2.right != cur1) cur2 = cur2.right;
                if (cur2.right == null){
                    cur2.right = cur1;
                    list.add(cur1.val);
                    cur1 = cur1.left;
                    continue;
                }else {
                    cur2.right = null;
                }
            }else {
                list.add(cur1.val);// 自身不能创建连接的叶子结点
            }
            cur1 = cur1.right;
        }
        return list;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
