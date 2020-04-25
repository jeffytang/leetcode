package com.twq.ll;

/**
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class _203_RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        // 都头结点进行特殊处理
        while (head != null && head.val == val) {
            ListNode removeNode = head;
            head = removeNode.next;
            removeNode = null;
        }

        if (head == null) return null;

        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                // 删除 cur.next
                ListNode removeNode = cur.next;
                cur.next = removeNode.next;
                removeNode = null;
            } else
                cur = cur.next;
        }

        return head;
    }

    /**
     *  使用哨兵节点简化编程
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements1(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                // 删除 cur.next
                ListNode removeNode = cur.next;
                cur.next = removeNode.next;
                removeNode = null;
            } else
                cur = cur.next;
        }

        return dummy.next;
    }
}
