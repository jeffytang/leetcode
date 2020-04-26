package com.twq.ll;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 进阶：
 *      1. 你的算法只能使用常数的额外空间。
 *      2. 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class _25_ReverseNodesInkGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy; // 表示 k 个待反转节点的前一个节点
        ListNode next = dummy; // 表示 k 个待反转节点的后一个节点

        while (next.next != null) {
            // 找到下一个 next 指针
            for (int i = 0; i < k; i++)
                if (next != null) next = next.next;
            if (next == null) break;

            // 拿到 k 个节点
            ListNode kStart = pre.next;
            ListNode nextKStart = next.next;
            next.next = null; // 将前 k 个节点和后 k 个节点截断

            // 反转 k 个节点
            ListNode newKStart = reverse(kStart);

            // 将反转之后的 k 个节点与原链表节点链接
            pre.next = newKStart;
            kStart.next = nextKStart;

            // 维护 pre 和 next 指针
            pre = kStart;
            next = pre;
        }

        return dummy.next;
    }

    private ListNode reverse(ListNode start) {
        ListNode pre = null;
        ListNode cur = start;
        while (cur != null) {
            ListNode next = cur.next;

            cur.next = pre;
            pre = cur;

            cur = next;
        }

        return pre;
    }
}
