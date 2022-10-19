package com.sp.algorithm.linkedlist;

import java.util.List;

/**
 * @description:
 * @author: luchao
 * @date: Created in 3/4/22 12:54 AM
 */
public class MyLinkedList {
    MyLinkedList list;

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

    public MyLinkedList() {
        dummy = new ListNode();
        tail = dummy;
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
     * 头部插入节点
     * @param val
     */
    public void addAtHead(int val){
        ListNode p = new ListNode(val);
        p.next = dummy.next;
        dummy.next = p;

        if(tail == dummy){
            tail = p;
        }

        length++;
    }

    /**
     * 尾部插入节点
     * @param val
     */
    public void addAtTail(int val){
        tail.next = new ListNode(val);
        tail = tail.next;
        length++;
    }

    /**
     * 返回index节点的前一个节点,若index节点不存在则返回dummy
     * @param index
     * @return
     */
    public ListNode getPrevNode(int index){
        //初始化front和back,一前一后
        ListNode front = dummy.next;
        ListNode back = dummy;

        for (int i = 0; i < index && front != null; i++) {
            back = front;
            front = front.next;
        }

        //把back节点作为前一个节点返回
        return back;
    }

    /**
     * 获取index节点
     * @param index
     * @return
     */
    public int get(int index){
        if(index < 0 || index >= length){
            return -1;
        }

        return getPrevNode(index).next.val;
    }

    /**
     * 插入指定位置之前
     * 若index大于链表长度则不会插入节点,若等于则附加插入末尾,若<0则在头部插入,否则在指定位置之前插入节点
     *
     * @param index
     * @return
     */
    public void addAtIndex(int index, int val){
        if(index > length){
            return;
        }

        if(index == length){
            addAtTail(val);
            return;
        }

        if(index <= 0){
            addAtHead(val);
            return;
        }

        ListNode pre = getPrevNode(index);
        ListNode cur = new ListNode(val);
        cur.next = pre.next;
        pre.next = cur;
        length++;
    }

    /**
     * 删除节点
     * @param index
     */
    public void deleteAtIndex(int index){
        if(index < 0 || index >= length){
            return;
        }

        ListNode pre = getPrevNode(index);

        //若删除的是最后一个节点,需要修改尾指针
        if(tail == pre.next){
            tail = pre;
        }

        pre.next = pre.next.next;
        length++;
    }

    /**
     * 打印链表
     * @param head
     */
    public void printValues(MyLinkedList.ListNode head){
        if(head == null){
            return;
        }

        ListNode curNode = head.next;
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
        public int val = 0;

        public ListNode next = null;

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
