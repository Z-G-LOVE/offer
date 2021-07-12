package 二叉树.遍历专题;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者: one者天下
 * @时间: 2021/4/25 00:02
 * @描述: 二叉树的后序遍历
 */
public class Solution145 {
    private final List<Integer> list = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        help(root);
        return list;
    }
    public void help(TreeNode root){
        if (root == null)
            return;
        else {
            if (root.left != null)
                help(root.left);
            if (root.right != null)
                help(root.right);
            list.add(root.val);
        }
    }
}
