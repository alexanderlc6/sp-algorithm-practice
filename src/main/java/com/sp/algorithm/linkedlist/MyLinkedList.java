package com.sp.algorithm.linkedlist;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: luchao
 * @date: Created in 3/4/22 12:54 AM
 */
public class MyLinkedList {
    MyLinkedList list;

    /**
     * 假头节点
     */
    public ListNode dummy;

    /**
     * 尾节点
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
        length++;
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
    public ListNode get(int index){
        if(index < 0 || index >= length){
            return null;
        }

        return getPrevNode(index).next;
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
     * 判断链表中是否存在环
     * @param head
     * @return 相遇的节点
     */
    public Boolean hasCircle(ListNode head){
        //当链表为空或只有一个节点时,无环
        if(head == null || head.next == null){
            return null;
        }

        ListNode s1 = head;
        ListNode s2 = head;
        while (s2 != null && s2.next != null){
            s2 = s2.next.next;
            s1 = s1.next;

            if(s1 == s2){
                return true;
            }
        }

        return s1 == s2;
    }

    public ListNode detectCircle(ListNode head){
        //当链表为空或只有一个节点时,无环
        if(head == null || head.next == null){
            return null;
        }

        ListNode s1 = head;
        ListNode s2 = head;
        while (s2 != null && s2.next != null){
            s2 = s2.next.next;
            s1 = s1.next;

            if(s1 == s2){
                break;
            }
        }

        if(s1 != s2){
            return null;
        }

        //s1指回从head出发
        s1 = head;

        //两个指针一起走
        while (s1 != s2){
            s1 = s1.next;
            s2 = s2.next;
        }

        //返回环形入口节点
        return s1;
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

    public static void main(String[] args) {
        System.out.println("=====判断链表中是否存在环=====");
        MyLinkedList list = new MyLinkedList().initList(new int[] { 3,2,0,1,2,-4});

        //Mock:设置环节点
        list.get(list.length - 1).setNext(list.get(3));
        System.out.println(list.hasCircle(list.dummy.next));

        System.out.println("=====获取链表中存在的环节点=====");
        System.out.println((list.detectCircle(list.dummy.next).getVal()));
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
