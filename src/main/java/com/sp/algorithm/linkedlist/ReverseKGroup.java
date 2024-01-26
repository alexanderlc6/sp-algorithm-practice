package com.sp.algorithm.linkedlist;

/**
 * 按K个元素一组反转链表
 */
public class ReverseKGroup {
    //临时链表,存放K个元素单组数据的反转
    MyLinkedList.ListNode tmp = new MyLinkedList.ListNode();
    MyLinkedList.ListNode tmpTail = tmp;

    MyLinkedList.ListNode ans = new MyLinkedList.ListNode();
    MyLinkedList.ListNode ansTail = ans;
    int len = 0;

    public MyLinkedList.ListNode reverse(MyLinkedList.ListNode head){
        MyLinkedList.ListNode dummy = new MyLinkedList.ListNode();
        MyLinkedList.ListNode p = head;

        while (p != null){
            MyLinkedList.ListNode back = p.next;
            p.next = dummy.next;
            dummy.next = p;

            p = back;
        }

        return dummy.next;
    }

    public void append(MyLinkedList.ListNode p, int k){
        // 将进来的结点添加到tmp链表中
        tmpTail.next = p;
        tmpTail = tmpTail.next;
        len++;

        // 如果tmp链表中的结点个数 == k,那么就需要把tmp 链表进行反转，然后再加入到ans链表中
        if(len == k) {
            MyLinkedList.ListNode tail = tmp.next;
            MyLinkedList.ListNode head = reverse(tmp.next);
            ansTail.next = head;
            ansTail = tail;

            len = 0;
            tmp.next = null;
            tmpTail = tmp;
        }
    }

    /**
     * 给定一个链表，要求将链表 k 个一组进行反转，如果最后一组不足 k 个，那么不反转。返回反转之后的链表。
     * @param head
     * @param k
     * @return
     */
    public MyLinkedList.ListNode reverseKByNormalGroup(MyLinkedList.ListNode head, int k){
        MyLinkedList.ListNode p = head;
        while (p != null){
            MyLinkedList.ListNode back = p.next;
            p.next = null;
            append(p, k);

            p = back;
        }

        if(len > 0){
            ansTail.next = tmp.next;
            ansTail = tmpTail;
        }

        ansTail.next = null;
        return ans.next;
    }

    /**
     *  给定一个链表，将链表从尾部开始，k个一组进行反转。 如果最左边的那个片段不足k个，那么不去翻转。
     *  A = [1, 2, 3, 4, 5], k = 3
     *  输出：[1, 2, 5, 4, 3]
     *  从尾部开始分组,K个一组
     * @param head
     * @param k
     * @return
     */
    public MyLinkedList.ListNode reverseKBySortGroup(MyLinkedList.ListNode head, int k){
        //先翻转一次,就可以按正常翻转进行了
        head = reverse(head);
        MyLinkedList.ListNode p = head;

        while (p != null){
            MyLinkedList.ListNode back = p.next;
            p.next = null;
            append(p, k);
            p = back;
        }

        if(len > 0){
            ansTail.next = tmp.next;
            ansTail = tmpTail;
        }

        ansTail.next = null;
        return reverse(ans.next);
    }

    /**
     递归法
     *
     三段式模版：
     终止条件： 当进行到最后一个分组，即不足k次遍历到链表尾（0次也算），就将剩余的部分直接返回。
     返回值： 每一级要返回的就是翻转后的这一分组的头，以及连接好它后面所有翻转好的分组链表。
     本级任务： 对于每个子问题，先遍历k次，找到该组结尾在哪里，然后从这一组开头遍历到结尾，依次翻转，结尾就可以作为下一个分组的开头，而先前指向开头的元素已经跑到了这一分组的最后，可以用它来连接它后面的子问题，即后面分组的头。
     *
     * @param head ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public MyLinkedList.ListNode reverseKGroup (MyLinkedList.ListNode head, int k) {
        MyLinkedList.ListNode tail = head;
        for(int i=0; i<k;i++){
            if(tail == null){
                return head;
            }

            tail = tail.next;
        }

        MyLinkedList.ListNode pre = null;
        MyLinkedList.ListNode cur = head;

        while(cur != tail){
            MyLinkedList.ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        head.next = reverseKGroup(tail, k);
        return pre;
    }

    public static void main(String[] args) {
        ReverseKGroup reverseKGroup = new ReverseKGroup();
        int[] data =  {1,3,5,4,2,8,2};

        //场景1
        MyLinkedList list1 = new MyLinkedList().initList(data);
        System.out.println("=====以K个元素组反转链表(右侧不满足k个则不反转)=====");
        list1.printValues(reverseKGroup.reverseKByNormalGroup(list1.dummy.next, 2));

        //场景2
        System.out.println("=====以K个元素组反转链表(左侧不满足k个则不反转)=====");
        MyLinkedList list2 = new MyLinkedList().initList(data);
        list2.printValues(reverseKGroup.reverseKBySortGroup(list2.dummy.next, 3));
    }
}
