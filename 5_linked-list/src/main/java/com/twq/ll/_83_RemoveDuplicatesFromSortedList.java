package com.twq.ll;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 输入: 1->1->2
 * 输出: 1->2
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class _83_RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            if (pre != null && cur.val == pre.val) {
                // 删除 cur 节点
                pre.next = next;
                cur.next = null;
            } else {
                pre = cur;
            }
            cur = next;
        }

        return head;
    }
}
