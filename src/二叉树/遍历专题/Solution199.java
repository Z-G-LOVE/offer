package 二叉树.遍历专题;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @作者: one者天下
 * @时间: 2021/4/27 09:52
 * @描述: 二叉树的右视图
 */
public class Solution199 {
    private final List<Integer> list = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) list.add(node.val);
                if (node.right != null) queue.add(node.right);
                if (node.left != null) queue.add(node.left);
            }
        }
        return list;
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        node2.left = node4;
        rightSideView(root);
    }
}
