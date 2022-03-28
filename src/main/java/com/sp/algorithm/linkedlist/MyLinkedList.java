package com.sp.algorithm.linkedlist;

/**
 * @description:
 * @author: luchao
 * @date: Created in 3/4/22 12:54 AM
 */
public class MyLinkedList {
    MyLinkedList list;

    public MyLinkedList() {
        dummy = new ListNode();
    }

    /**
     * 初始化一个链表
     * @param source
     * @return
     */
    public MyLinkedList initList(int[] source){
        if(source == null || source.length == 0){
            return null;
        }

        MyLinkedList result = new MyLinkedList();
        for (int i = 0; i < source.length; i++) {
            result.add(new ListNode(source[i]));
        }

        this.list = result;
        return result;
    }

    /**
     * 假头
     */
    public ListNode dummy;

    /**
     * 尾元素
     */
    public ListNode tail;

    /**
     * 链表长度
     */
    public int length = 0;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void add(ListNode node){
        if(dummy == null){
            dummy = node;
            tail = node;
            return;
        }

        tail.next = node;
        tail = node;
    }

    /**
     * 打印链表
     * @param head
     */
    public void printValues(MyLinkedList.ListNode head){
        if(head == null){
            return;
        }

        ListNode curNode = head;
        while (curNode != null){
            System.out.printf(curNode.val + " ");
            curNode = curNode.next;
        }

        System.out.println();
    }

    /**
     * 节点
     */
    public static class ListNode {
        public int val;

        public ListNode next;

        public ListNode() {
        }

        public ListNode(int val){
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

}
