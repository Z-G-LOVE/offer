package 二叉树.递归专题.数据结构;

/**
 * @作者: one者天下
 * @时间: 2021/4/30 13:28
 * @描述: 二叉树结构
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left,TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
