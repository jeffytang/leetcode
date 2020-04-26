package com.twq.ll.twopointer;

import com.twq.ll.ListNode;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 *
 * 链接：https://leetcode-cn.com/problems/rotate-list/
 */
public class _61_RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;

        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        // 比如长度为 3 的链表
        // 那么旋转 5 个元素和旋转 2 个元素效果是一样的
        k = k % length;

        if (k == 0) return head;

        // slow 表示倒数第 k + 1 个节点
        ListNode slow = head;
        // fast 表示最后一个节点
        ListNode fast = head;
        while (k-- > 0)
            fast = fast.next;

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // 旋转链表
        ListNode newHead = slow.next;
        slow.next = null;

        fast.next = head;

        return newHead;
    }
}
