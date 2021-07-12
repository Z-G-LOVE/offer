package 二叉树.遍历专题;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者: one者天下
 * @时间: 2021/4/26 23:31
 * @描述: 二叉树的所有路径
 */
public class Solution257 {
    private final List<String> list = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return list;
        help2(root,"");
        return list;
    }
    private void help(TreeNode root,StringBuilder s){
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            list.add(s.toString());
        }
        s.append(root.val).append("->");
        help(root.left,s);
        String a = root.val+"->";
        int length = a.length();
        length = s.length()-length;
        s.delete(length,s.length());

        s.append(root.val).append("->");
        help(root.right,s);
        a = root.val+"->";
        length = a.length();
        length = s.length()-length;
        s.delete(length,s.length());
    }

    public void help1(TreeNode root,String path,StringBuilder paths){
        if (root != null){
            paths = new StringBuilder(path);
            paths.append(root.val);
            path = paths.toString();
            if (root.left == null && root.right == null){
                list.add(path);
                return;
            }else {
                paths.append("->");
                help1(root.left,path,paths);
                help1(root.right,path,paths);
            }
        }
    }

    public void help2(TreeNode root , String path){
        if (root == null) return;
        if (root.left == null && root.right == null) {
            list.add(path + root.val);
            return;
        }
        help2(root.left,path + root.val + "->");
        help2(root.right,path + root.val + "->");
    }
    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        binaryTreePaths(root);
    }
}
