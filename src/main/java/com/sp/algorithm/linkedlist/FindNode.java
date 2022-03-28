package com.sp.algorithm.linkedlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * https://www.jianshu.com/p/aa729b805059
 * @author: luchao
 * @date: Created in 3/4/22 12:49 AM
 */
public class FindNode {
    private final static int [] data = {2,1,5,6,23,15};

    /**
     * 前缀前一个节点
     * @param list
     * @param index
     * @return
     */
    public MyLinkedList.ListNode findPreviousNode(MyLinkedList list, int index){
        if(list == null || index == 0){
            return null;
        }

        MyLinkedList.ListNode current = list.dummy.getNext();
        MyLinkedList.ListNode prev = list.dummy;

        for (int i = 1; i < index && current != null; i++){
            prev = current;
            current = current.getNext();
        }

        return prev;
    }

    /**
     * 查找指定索引的节点
     * @param list
     * @param index
     * @return
     */
    public MyLinkedList.ListNode getNode(MyLinkedList list, int index){
        if(index <= 0 || index > data.length){
            return null;
        }

        MyLinkedList.ListNode prevNode = findPreviousNode(list, index);
        if(prevNode != null){
            return prevNode.getNext();
        }

        return null;
    }

    public int getLength(MyLinkedList.ListNode head){
        MyLinkedList.ListNode tmp = head;
        int len = 0;
        while (head.next != null){
            len++;
            tmp = tmp.next;
        }

        return len;
    }

    public void addNodeAtIndex(MyLinkedList.ListNode head, int index, Object data){
        if(index < 1 || index > getLength(head)){
            return;
        }

        int count = 1;
        MyLinkedList.ListNode newNode = new MyLinkedList.ListNode();
        MyLinkedList.ListNode tail = head;

        while (tail.next != null) {
            if (index == count++) {
                newNode.next = tail.next;
                tail.next = newNode;
                return;
            }

            tail = tail.next;
        }
    }

    public void printListFromHead(MyLinkedList.ListNode head){
        MyLinkedList.ListNode tmp = head;
        while (tmp.next != null){
            System.out.println("{" + tmp.next.val + "}");
            tmp = tmp.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        FindNode findNode = new FindNode();
        MyLinkedList list = new MyLinkedList().initList(data);
        System.out.println(findNode.findPreviousNode(list, 3).getVal());

        //依次打印结果
        list.printValues(findNode.getNode(list, 3));
    }
}
