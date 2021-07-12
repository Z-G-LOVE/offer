package 二叉树.遍历专题;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @作者: one者天下
 * @时间: 2021/4/28 23:05
 * @描述: 实现二叉树的序列化和反序列化
 */
public class Soluttion297 {
    // Encodes a tree to a single string.
    private final static String DELIMITER = ",";
    private final static String NULL = "#";
    public String serialize(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        String serialize = "";
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur == null){
                    serialize = serialize + NULL + DELIMITER;
                }else {
                    serialize = serialize + cur.val + DELIMITER;
                }
                if (cur == null) continue; // 防止死循环
                // 无论是否为空都要添加
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        return serialize.substring(0,serialize.length()-1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data)) return null;
        String[] splits = data.split(DELIMITER);
        TreeNode root = new TreeNode(Integer.parseInt(splits[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for (int i = 1; i < splits.length; i+=2) {
            TreeNode node = queue.poll();
            if (!NULL.equals(splits[i])){
                node.left = new TreeNode(Integer.parseInt(splits[i]));
                queue.add(node.left);
            }else {
                node.left = null;
            }
            if (!NULL.equals(splits[i+1])){
                node.right = new TreeNode(Integer.parseInt(splits[i+1]));
                queue.add(node.right);
            }else {
                node.right = null;
            }
        }
        return root;
    }
}
