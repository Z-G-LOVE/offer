package com.coding.str;

/**
 * @作者: Administrator
 * @时间: 2021/7/15 17:23
 * @描述: 实现链表
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
