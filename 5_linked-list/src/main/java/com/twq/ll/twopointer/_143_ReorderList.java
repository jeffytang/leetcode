package com.twq.ll.twopointer;

import com.twq.ll.ListNode;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 */
public class _143_ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return;

        // 1. 将链表从中点拆开
        // 使用快慢指针找到链表的中点
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode rightNode = slow.next;
        slow.next = null;

        // 2. 将后半部分的链表倒转
        rightNode = reverse(rightNode);

        // 3. 依次链接两个部分的链表
        while (rightNode != null) {
            ListNode rightNext = rightNode.next;

            rightNode.next = head.next;
            head.next = rightNode;

            head = rightNode.next;
            rightNode = rightNext;
        }

    }

    private ListNode reverse(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        while (cur != null) {
            ListNode next = cur.next;

            cur.next = pre;
            pre = cur;

            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode node = ListNode.create(1, 2, 3, 4, 5);
        _143_ReorderList e = new _143_ReorderList();
        e.reorderList(node);
    }
}
