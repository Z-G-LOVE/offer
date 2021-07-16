package com.coding;

import com.coding.str.ListNode;
import com.coding.str.TreeNode;

import java.util.*;

/**
 * @作者: Administrator
 * @时间: 2021/7/12 22:42
 * @描述: Leetcode算法题
 */
public class LeetcodeSolution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        Map<Integer,Integer> map = new HashMap<>();
        // 遍历链表
        ListNode temp = head;
        while (temp != null){
            // 没出现
            if (!map.containsKey(temp.val)){
                map.put(temp.val, 1);
            }else { // 出现过
                map.put(temp.val, map.get(temp.val)+1);
            }
            temp = temp.next;
        }
        /* // 遍历Map，创建链表
        index = 0;
        temp = new ListNode();
        ListNode node = temp;
        ListNode tag = null;
        while (index < map.size()){
            tag = new ListNode(map.get(index++));
            node.next = tag;
            node = tag;
        }
        return temp.next;*/
        temp = head;
        ListNode headNew = new ListNode();
        ListNode tempNow = headNew;
        ListNode node = null;
        while (temp != null){
            if (map.get(temp.val) == 1) {
                node = new ListNode(temp.val);
                tempNow.next = node;
                tempNow = node;
            }
            temp = temp.next;
        }
        return headNew.next;
    }

    /**
     * 迭代实现二叉树的前序遍历
     * @param root 根节点
     * @return 返回遍历的列表
     */
    public List<Integer> pre(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            // 先遍历根节点
            list.add(node.val);
            // 添加左子树
            if (node.left != null) stack.push(node.left);
            // 添加右子树
            if (node.right != null) stack.push(node.right);
        }
        return list;
    }

    /**
     * 实现二叉树的中序遍历
     * @param root 根节点
     * @return 返回遍历的列表
     */
    public List<Integer> inoder(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        do {
            // 将根节点和左子树压入栈
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()){
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }while (!stack.isEmpty() || root != null);
        return list;
    }

    /**
     * 迭代实现二叉树的后序遍历
     * @param root 根节点
     * @return 返回遍历后的列表
     */
    public List<Integer> after(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
            list.add(0,node.val);
        }
        return list;
    }
}
