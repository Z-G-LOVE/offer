package 二叉树.递归专题.先左右后自己;

import 二叉树.递归专题.数据结构.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者: one者天下
 * @时间: 2021/4/29 23:06
 * @描述: 不同的二叉搜索树II
 */
public class Solution95 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        return  createTree(1,n);
    }

    private List<TreeNode> createTree(int start,int end){
        List<TreeNode> list = new ArrayList<>();;
        if (start > end){
            list.add(null);
            return list;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = createTree(start,i-1);
            List<TreeNode> right = createTree(i+1,end);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    list.add(root);
                }
            }
        }
        return list;
    }


}

