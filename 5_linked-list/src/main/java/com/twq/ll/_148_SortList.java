package com.twq.ll;


/**
 *  在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * 链接：https://leetcode-cn.com/problems/sort-list/
 */
public class _148_SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 计算链表的长度
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }

        for (int step = 1; step < length; step <<= 1) {
            ListNode prev = dummy;
            ListNode curr = dummy.next;
            while (curr != null) {
                // 左边链表
                ListNode left = curr;
                // 分割得到右边链表
                ListNode right = split(left, step);
                // 分割得到下次处理的链表头
                curr = split(right, step);
                // 合并 left 和 right 链表
                prev = merge(left, right, prev);
            }
        }

        return dummy.next;
    }

    private ListNode split(ListNode node, int step) {
        if (node == null)
            return null;

        // 找到分割点
        for (int i = 1; node.next != null && i < step; i++)
            node = node.next;

        ListNode right = node.next;
        // 切割
        node.next = null;

        return right;
    }

    private ListNode merge(ListNode left, ListNode right, ListNode pre) {
        ListNode curr = pre;
        // 排序
        while (left != null && right != null) {
            if (left.val < right.val) {
                curr.next = left;
                left = left.next;
            } else {
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
        }

        curr.next = left == null ? right : left;
        while (curr.next != null) curr = curr.next;
        return curr;
    }
}
