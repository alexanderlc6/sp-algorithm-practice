package com.sp.algorithm.linkedlist;

import java.util.List;

/**
 * [Add comments here]
 *
 * @author luchao Created on 2022/10/19
 */
public class NormalOpr {
    /**
     * 链表反转, O(n)
     * 1-3-5 ==>  5-3-1
     * @param head
     * @return
     */
    public MyLinkedList.ListNode reverseList(MyLinkedList.ListNode head){
        //建立一个新的带假头的新链表
        MyLinkedList.ListNode dummy = new MyLinkedList.ListNode();

        //遍历旧链表
        while (head != null){
            MyLinkedList.ListNode tmp = head.next;

            //头插到新链表中
            head.next = dummy.next;
            dummy.next = head;

            head = tmp;
        }

        //返回新链表的头
        return dummy.next;
    }

    /**
     * 删除重复元素,O(n)
     * @param head
     * @param val
     * @return
     */
    public MyLinkedList.ListNode removeElements(MyLinkedList.ListNode head, int val){
        MyLinkedList.ListNode dummy = new MyLinkedList.ListNode();
        MyLinkedList.ListNode tail = dummy;

        MyLinkedList.ListNode p = head;
        while (p != null){
            MyLinkedList.ListNode back = p.next;

            if(p.val != val){
                tail.next = p;
                tail = p;
            }

            p = back;
        }

        tail.next = null;
        return dummy.next;
    }
}
