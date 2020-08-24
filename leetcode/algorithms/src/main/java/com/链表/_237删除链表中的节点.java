package com.链表;

import com.base.ListNode;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * @author WTY
 * @Date 2020/4/28 22:01
 */
public class _237删除链表中的节点 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
