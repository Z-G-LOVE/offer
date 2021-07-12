package 二叉树.递归专题.先自己后左右;

import 二叉树.递归专题.数据结构.TreeNode;

/**
 * @作者: one者天下
 * @时间: 2021/5/1 17:40
 * @描述: 相同数判断
 */
public class Solution100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null){
            if (p == null && q == null) return true;
            else return false;
        }
        if (p.val == q.val) return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        else return false;
    }
}
