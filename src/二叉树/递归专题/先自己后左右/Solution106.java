package 二叉树.递归专题.先自己后左右;

import 二叉树.递归专题.数据结构.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者: one者天下
 * @时间: 2021/5/1 23:09
 * @描述: 根据一棵树的中序遍历与后序遍历构造二叉树。
 */
public class Solution106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 存放中序遍历的值的索引
        Map<Integer,Integer> map = new HashMap<>();
        // 存放
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return create(0,inorder.length-1,inorder,0,postorder.length-1,postorder,map);
    }
    private TreeNode create(int iStart,int iEnd,int[] inorder,
                            int pStart,int pEnd,int[] postorder,Map<Integer,Integer> map){
        if (iStart > iEnd ||pStart > pEnd) return null;
        int rootVal = postorder[pEnd];
        TreeNode root = new TreeNode(rootVal);
        int iRootIndex = map.get(rootVal);
        int pCount = iRootIndex - iStart;
        root.left = create(iStart,iRootIndex-1,inorder,pStart,pStart+pCount-1,postorder,map);
        root.right = create(iRootIndex+1,iEnd,inorder,pStart=pCount,pEnd-1,postorder,map);
        return root;
    }
}
