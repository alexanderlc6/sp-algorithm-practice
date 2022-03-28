package com.sp.algorithm.linkedlist;

/**
 * @description: 给定一个排序链表，删除重复出现的元素，使得每个元素只出现一次。
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 * @author: luchao
 * @date: Created in 3/7/22 6:21 PM
 */
public class RemoveDuplicate {

    public MyLinkedList.ListNode removeDupElement(MyLinkedList.ListNode head){
        MyLinkedList.ListNode dummy = new MyLinkedList.ListNode();
        MyLinkedList.ListNode tail = dummy;
        MyLinkedList.ListNode p = head;

        while (p != null){
            MyLinkedList.ListNode back = p.next;

            if(tail == dummy || tail.val != p.val){
                tail.next = p;
                tail = p;
            }

            p = back;
        }

        tail.next = null;
        return dummy.next;
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList().initList(new int[] { 1,1,2,3,3});
        list.printValues(new RemoveDuplicate().removeDupElement(list.dummy));

    }
}
