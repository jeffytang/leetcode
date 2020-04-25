package com.twq.ll;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，
 * 使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 */
public class _86_PartitionList {
    public ListNode partition(ListNode head, int val) {
        // before 链表用于存储小于 val 的节点
        ListNode beforeHead = new ListNode(0);
        ListNode before = beforeHead;

        // after 节点用于存储大于等于 val 的节点
        ListNode afterHead = new ListNode(0);
        ListNode after = afterHead;

        while (head != null) {
            if (head.val < val) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        // 将 after 的 next 设置为 null，这样就不会导致形成环
        after.next = null;
        before.next = afterHead.next;
        return beforeHead.next;
    }
}
