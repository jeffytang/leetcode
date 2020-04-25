package com.twq.ll;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class _82_RemoveDuplicatesFromSortedList_ii {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 用于判断重复元素的指针
        ListNode cur = head;
        // 用于删除重复元素的指针
        ListNode pre = dummy;

        while (cur != null) {
            // 当前元素和下一个元素重复
            if (cur.next != null && cur.val == cur.next.val) {
                // 一直跳过重复的元素
                do {
                    cur = cur.next;
                } while (cur.next != null && cur.val == cur.next.val);

                // 删除重复的元素
                pre.next = cur.next;
                cur = cur.next;
            } else {
                // 没有重复的节点，两个指针一起往前走
                pre = pre.next;
                cur = cur.next;
            }
        }

        return dummy.next;
    }

}
