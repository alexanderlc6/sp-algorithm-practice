package com.sp.algorithm.linkedlist;

/**
 * 常用链表操作
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
        MyLinkedList.ListNode p = head;

        //遍历旧链表
        while (p != null){
            MyLinkedList.ListNode back = p.next;

            //头插到新链表中
            p.next = dummy.next;
            dummy.next = p;
            p = back;
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

    /**
     * 删除倒数第K个节点(只能遍历一次)
     * Input: 1-2-3, k=3, Output: 1-3
     * 双指针方法
     */
    public MyLinkedList.ListNode removeNodeK(MyLinkedList.ListNode head, int k){
        MyLinkedList.ListNode dummy = new MyLinkedList.ListNode();
        dummy.next = head;

        int preWalkedSteps = 0;

        //front指针从dummy开始走k步
        MyLinkedList.ListNode front = dummy;
        //front指针指向链表最后一个节点
        while (preWalkedSteps < k && front != null && front.next != null){
            front = front.next;
            preWalkedSteps++;
        }

        //back指针指向dummy,然后front和back指针一起走
        MyLinkedList.ListNode back = dummy;
        //front不能为空,需指向最后一个节点
        while (front != null && front.next != null){
            back = back.next;
            front = front.next;
        }

        if(preWalkedSteps == k){
            // 删除倒数第k个节点
            back.next = back.next.next;
        }

        return dummy.next;
    }

    /**
     * 找到链表的中间节点
     * @param head
     * @return
     */
    public MyLinkedList.ListNode findMiddleNode(MyLinkedList.ListNode head){
        MyLinkedList.ListNode dummy = new MyLinkedList.ListNode();
        dummy.next = head;

        MyLinkedList.ListNode s2 = head;
        MyLinkedList.ListNode s1 = head;
        MyLinkedList.ListNode pre = dummy;

        while (s2 != null && s2.next != null){
            //奇数个节点时,s1位于前半部分的最后，pre就是s1
            pre = s1;
            s1 = s1.next;
            s2 = s2.next.next;
        }

        return s2 != null ? s1 : pre;
    }

    /**
     * 从中间拆分链表(只能遍历一次)
     * 输入：1-2-3-4-5，输出：1-2-3,4-5
     */
    public MyLinkedList.ListNode[] split(MyLinkedList.ListNode head){
        MyLinkedList.ListNode mid = findMiddleNode(head);

        //链表后半部分的开头
        MyLinkedList.ListNode back = mid.next;
        mid.next = null;
        return new MyLinkedList.ListNode[] {head, back};
    }

    /**
     * 重排链表
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     *
     * 示例 1:
     * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
     *
     * 示例 2:
     * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
     */
    public MyLinkedList.ListNode reSortList(MyLinkedList.ListNode head){
        if(head == null || head.next == null){
            return null;
        }

        MyLinkedList.ListNode mid = findMiddleNode(head);
        MyLinkedList.ListNode front = head;
        MyLinkedList.ListNode back = mid.next;
        mid.next = null;

        back = reverseList(back);
        boolean isFront = true;
        MyLinkedList.ListNode dummy = new MyLinkedList.ListNode();
        MyLinkedList.ListNode tail = dummy;

        while (front != null || back != null){
            if(back == null || isFront && front != null){
                tail.next = front;
                tail = tail.next;
                front = front.next;
            }else {
                tail.next = back;
                tail = tail.next;
                back = back.next;
            }

            isFront = !isFront;
        }

        tail.next = null;
        return head;
    }

    /**
     * 两数相加:https://leetcode.cn/problems/add-two-numbers/submissions/
     两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     请你将两个数相加，并以相同形式返回一个表示和的链表。
     可以假设除了数字 0 之外，这两个数都不会以 0开头。
     输入：l1 = [2,4,3], l2 = [5,6,4]
     输出：[7,0,8]
     解释：342 + 465 = 807.
     * @param l1
     * @param l2
     * @return
     */
    public MyLinkedList.ListNode addTwoNumbers(MyLinkedList.ListNode l1, MyLinkedList.ListNode l2) {
        MyLinkedList.ListNode result = new MyLinkedList.ListNode();
        result.val = l1.val + l2.val;
        l1 = l1.next;
        l2 = l2.next;

        int carry = 0;
        if(result.val > 9){
            result.val %= 10;
            carry = 1;
        }else{
            carry = 0;
        }

        MyLinkedList.ListNode p = result;

        while(l1 != null || l2 != null || carry != 0){
            MyLinkedList.ListNode newNode = new MyLinkedList.ListNode();
            if(l1 == null && l2 == null){
                newNode.val = carry;
            }else if(l1 == null){
                newNode.val = carry + l2.val;
                l2 = l2.next;
            }else if(l2 == null){
                newNode.val = carry + l1.val;
                l1 = l1.next;
            }else {
                newNode.val = carry + l1.val + l2.val;
                l1 = l1.next;
                l2 = l2.next;
            }

            if(newNode.val > 9){
                newNode.val %= 10;
                carry = 1;
            }else{
                carry = 0;
            }

            p.next = newNode;
            p = p.next;
        }


        return result;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        NormalOpr normalOpr = new NormalOpr();

        //反转链表
        int[] data1 =  {1,3,5};
        System.out.println("=====反转链表=====");
        MyLinkedList list1 = new MyLinkedList().initList(data1);
        list1.printValues(normalOpr.reverseList(list1.dummy.next));

        //删除重复元素
        int[] data2 =  {1,1,3,2,2,5};
        System.out.println("=====删除重复元素=====");
        MyLinkedList list2 = new MyLinkedList().initList(data2);
        list2.printValues(normalOpr.removeElements(list2.dummy.next, 1));

        //删除第K个节点
        System.out.println("=====删除第K个节点=====");
        list2.printValues(normalOpr.removeNodeK(list2.dummy.next, 4));

        //拆分链表
        int[] data3 =  {4,3,9,2,5};
        System.out.println("=====拆分链表=====");
        MyLinkedList list3 = new MyLinkedList().initList(data3);
        MyLinkedList.ListNode[] splitLists = normalOpr.split(list3.dummy.next);
        if(splitLists != null && splitLists.length > 1){
            list3.printValues(splitLists[0]);
            list3.printValues(splitLists[1]);
        }

        //重排链表
        int[] data4 =  {1,2,3,4,5};
        MyLinkedList list4 = new MyLinkedList().initList(data4);
        System.out.println("=====重排链表=====");
        list4.printValues(normalOpr.reSortList(list4.dummy.next));
    }
}
