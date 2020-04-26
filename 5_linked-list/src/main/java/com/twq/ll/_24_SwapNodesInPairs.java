package com.twq.ll;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class _24_SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 定义 4 个指针
        // p 指针表示待交换两个节点的前一个节点
        ListNode p = dummy;

        while (p != null && p.next != null && p.next.next != null) {
            // node1 表示待交换两个节点的第一个节点
            ListNode node1 = p.next;
            // node2 表示待交换两个节点的第二个节点
            ListNode node2 = p.next.next;
            // next 表示待交换两个节点的后一个节点
            ListNode next = p.next.next.next;

            node2.next = node1;
            node1.next = next;
            p.next = node2;

            p = node1;
        }

        return dummy.next;
    }
}
