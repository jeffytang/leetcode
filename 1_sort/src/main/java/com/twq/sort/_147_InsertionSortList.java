package com.twq.sort;

/**
 *  对链表进行插入排序
 */
public class _147_InsertionSortList {

    public ListNode insertionSortList(ListNode head) {
        if (head == null)
            return null;

        ListNode dummy = new ListNode(-1);
        ListNode cur = head;
        ListNode pre = dummy;
        ListNode next = null;

        while (cur != null) {
            // 记录下一个需要处理的节点
            next = cur.next;
            // 找到第一个大于 cur 节点值的节点
            while (pre.next != null && pre.next.val < cur.val)
                pre = pre.next;
            // 将当前节点 cur 插入到 pre 和 pre.next 之间
            cur.next = pre.next;
            pre.next = cur;
            // pre 节点恢复到起点
            pre = dummy;
            // 处理下一个节点
            cur = next;
        }

        return dummy.next;
    }
}
