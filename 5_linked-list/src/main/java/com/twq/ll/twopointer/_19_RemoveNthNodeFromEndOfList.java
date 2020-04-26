package com.twq.ll.twopointer;

import com.twq.ll.ListNode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 说明：
 *      给定的 n 保证是有效的。
 *
 * 进阶：
 *      你能尝试使用一趟扫描实现吗？
 */
public class _19_RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        // fast 指针先走 n + 2 步
        // n = 2
        // n = 1
        // n = 0
        // n = -1
        while (n-- >= 0 && fast != null)
            fast = fast.next;

        if (fast == null) return dummy.next;

        // 快慢指针一起往前走，一直到 fast == null
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // 到现在的话，slow 指向的是倒数第 n + 1 个节点
        // 删除倒数第 n 个节点
        ListNode delNode = slow.next;
        slow.next = delNode.next;
        delNode.next = null;

        return dummy.next;
    }
}
