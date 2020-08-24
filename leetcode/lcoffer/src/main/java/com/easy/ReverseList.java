package com.easy;

import com.base.ListNode;

/**
 * 反转链表
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * @author wty
 * @create 2020-03-03 21:32
 */
public class ReverseList {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        for (int i = 2;i< 6;i++){
            ListNode listNode = new ListNode(i);
            listNode.next = listNode1;
            listNode1 = listNode;
        }
        ListNode listNode = new ReverseList().reverseList(listNode1);



    }
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head, next = null;
        while(cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
