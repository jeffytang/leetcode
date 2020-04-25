package com.twq.ll;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
 * 请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，
 * 时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 *
 * 说明：
 *      1. 应当保持奇数节点和偶数节点的相对顺序。
 *      2. 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 */
public class _328_OddEvenLinkedList {
    public ListNode oddEventList(ListNode head) {
        // 用于存储奇数节点
        ListNode oddHead = new ListNode(-1);
        ListNode odd = oddHead;
        // 用于存储偶数节点
        ListNode eventHead = new ListNode(-1);
        ListNode event = eventHead;

        int flag = 1;
        while (head != null) {
            if (flag % 2 != 0) {
                odd.next = head;
                odd = odd.next;
            } else {
                event.next = head;
                event = event.next;
            }
            head = head.next;
            flag++;
        }

        event.next = null;
        odd.next = eventHead.next;
        return oddHead.next;
    }
}
