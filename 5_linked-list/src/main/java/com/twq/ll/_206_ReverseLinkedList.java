package com.twq.ll;

/**
 * 反转一个单链表。
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 进阶：
 *  你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class _206_ReverseLinkedList {
    // 递归
    // 关键在于反向工作。假设列表的其余部分已经被反转，现在我该如何反转它前面的部分？
    /*
        假设列表为：
            n1→...→nk−1→nk→nk+1→...→nm→∅
        若从节点 nk+1 到 nm 已经被反转，而我们正处于 nk
​	        n1→...→nk−1→nk→nk+1←...←nm
        我们希望 nk+1的下一个节点指向 nk

        所以，nk.next.next = nk
        要小心的是 n1 的下一个必须指向 Ø
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
    // 迭代
    public ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;

            cur.next = pre;
            pre = cur;

            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        _206_ReverseLinkedList l = new _206_ReverseLinkedList();
        System.out.println(l.reverseList(ListNode.create(1, 2, 3, 4, 5)));
    }
}
