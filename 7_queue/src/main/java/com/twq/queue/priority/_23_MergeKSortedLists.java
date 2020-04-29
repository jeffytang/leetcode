package com.twq.queue.priority;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并K个排序链表
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
public class _23_MergeKSortedLists {
    // 使用优先队列
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        PriorityQueue<ListNode> queue
                = new PriorityQueue<>(Comparator.comparingInt(l -> l.val));
        for (ListNode node : lists) {
            if (node != null ) queue.add(node);
        }

        while (!queue.isEmpty()) {
            ListNode node = queue.remove();
            curr.next = node;
            curr = curr.next;

            if (node.next != null)
                queue.add(node.next);
        }
        return dummy.next;
    }

    // 使用分治思想
    public ListNode mergeKLists1(ListNode[] lists) {
        return sort(lists, 0, lists.length - 1);
    }

    private ListNode sort(ListNode[] lists, int lo, int hi) {
        if (lo >= hi) return lists[lo];

        int mid= lo + (hi - lo) / 2;
        ListNode l1 = sort(lists, lo, mid);
        ListNode l2 = sort(lists, mid + 1, hi);

        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        }
        l2.next = merge(l1, l2.next);
        return l2;
    }
}
