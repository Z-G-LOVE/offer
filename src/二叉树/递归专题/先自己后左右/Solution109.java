package 二叉树.递归专题.先自己后左右;

import 二叉树.递归专题.数据结构.ListNode;
import 二叉树.递归专题.数据结构.TreeNode;

import java.util.List;


/**
 * @作者: one者天下
 * @时间: 2021/5/3 11:36
 * @描述: 有序链表转换为二叉搜索树
 */
public class Solution109 {
    public TreeNode sortedListToBST(ListNode head) {
        return create(head);
    }
    private TreeNode createTree(int start,int end,List<Integer> list){

        if (start > end) return  null;
        int root_index = (start + end) / 2;
        TreeNode root = new TreeNode(list.get(root_index));

        TreeNode left = createTree(start,root_index-1,list);
        TreeNode right = createTree(root_index+1,end,list);

        root.left = left;
        root.right = right;

        return  root;

    }
    private TreeNode create(ListNode head){
        if (head == null) return null;
        ListNode slow = head;// 慢指针,记录链表的中间节点
        ListNode fast = head;// 快指针
        ListNode pre = null;// 用于表示当前慢指针的前一个节点
        while (fast != null && fast.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        if (pre != null){
            pre.next = null;
            root.left = create(head);// 向左递归创建左子树
        }
        root.right = create(slow.next);// 向右递归创建右子树
        return root;
    }

}
