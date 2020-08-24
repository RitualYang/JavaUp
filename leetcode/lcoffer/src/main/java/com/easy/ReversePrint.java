package com.easy;

import com.base.ListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 从尾到头打印链表
 *
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 * @author wty
 * @create 2020-02-26 20:07
 */
public class ReversePrint {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(2);

        //时间复杂度：O(N)      空间复杂度：O(N)
        int[] ints = reversePrint1(head);//执行用时：1ms,内存消耗：39.4MB
        //时间复杂度：O(N)      空间复杂度：O(N)
        int[] ints1 = reversePrint2(head);//执行用时：2ms,内存消耗：40.8MB

    }

    /**
     * 使用栈先进后出的结构特性
     * @param head
     * @return
     */
    public static int[] reversePrint1(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }
        return print;
    }

    /**
     * 递归打印
     * @param head
     * @return
     */
    public static int[] reversePrint2(ListNode head) {
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        recur(head,tmp);
        int[] res = new int[tmp.size()];
        for(int i = 0; i < res.length; i++) {
            res[i] = tmp.get(i);
        }
        return res;
    }
    static void recur(ListNode head, ArrayList<Integer> tmp) {
        if(head == null) {
            return;
        }
        recur(head.next,tmp);
        tmp.add(head.val);
    }

}
