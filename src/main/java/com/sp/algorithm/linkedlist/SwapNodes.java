package com.sp.algorithm.linkedlist;

public class SwapNodes {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode newHead = swapPairs(head);
        while(newHead != null){
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = dummy;

        while(p.next != null && p.next.next != null){
            ListNode a = p.next;
            ListNode b = p.next.next;
            p.next = b;
            a.next = b.next;
            b.next = a;

            p = a;
        }

        return dummy.next;
    }
}

