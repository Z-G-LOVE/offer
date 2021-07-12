package 二叉树.遍历专题;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @作者: one者天下
 * @时间: 2021/4/28 22:35
 * @描述: 完美二叉树
 */
public class Solution116 {
    public Node connect(Node root) {
        if (root == null) return root;
        Node pre = null;// 表示遍历的前一个节点
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (pre == null) pre = cur;
                else {
                    pre.next = cur;
                    pre = cur;
                }
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            pre = null;
        }
        return root;
    }
}
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
