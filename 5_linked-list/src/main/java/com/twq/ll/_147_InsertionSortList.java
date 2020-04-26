package com.twq.ll;

/**
 *  对链表进行插入排序
 *
 *  插入排序算法：
 *      1. 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 *      2. 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 *      3. 重复直到所有输入数据插入完为止。
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
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
