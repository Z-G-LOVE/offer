package 二叉树.递归专题.先自己后左右;

import 二叉树.递归专题.数据结构.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者: one者天下
 * @时间: 2021/5/1 21:24
 * @描述: 根据一棵树的前序遍历与中序遍历构造二叉树。
 */
public class Solution105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 记录中序遍历的值对应的索引位置
        Map<Integer,Integer> map = new HashMap<>();
        // 初始化
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return create(0,preorder.length,0,inorder.length,preorder,inorder,map);
    }
    // 递归构造二叉树
    private TreeNode create(int pre_start, int pre_end,
                            int in_start, int in_end,
                            int[] preorder, int[] inorder,
                            /*记录根节点在中序遍历数组的位置*/Map<Integer,Integer> map){
        if (pre_start == pre_end) return null;
        int rootVal = preorder[pre_start];
        TreeNode root = new TreeNode(rootVal);
        int inRootIndex = map.get(rootVal);
        // 获取前序遍历数组的启示位置
        int preCount = inRootIndex - in_start;
        // 构造当前节点的左子树
        root.left = create(pre_start+1,pre_start+preCount,in_start,inRootIndex,preorder,inorder,map);
        // 构造当前节点的右子树
        root.right = create(pre_start+preCount+1,pre_end,inRootIndex+1,in_end,preorder,inorder,map);
        return root;
    }
}
