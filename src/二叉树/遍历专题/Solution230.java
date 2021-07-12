package 二叉树.遍历专题;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @作者: one者天下
 * @时间: 2021/4/26 22:23
 * @描述: 二叉搜索树的第k个最小元素
 */
public class Solution230 {

    private List<Integer> list = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        help(root,k);
        return list.get(list.size()-1);
    }

    public void help(TreeNode root,int k){
        if (root == null || list.size() == k) return;
        if (root.left != null) help(root.left,k);
        list.add(root.val);
        if (root.right != null) help(root.right,k);
    }

    public int kthSmallest1(TreeNode root, int k){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            list.add(node.val);
            if (list.size() == k) break;
            if (node.right != null) cur = node.right;
        }
        return list.get(list.size()-1);
    }
}
