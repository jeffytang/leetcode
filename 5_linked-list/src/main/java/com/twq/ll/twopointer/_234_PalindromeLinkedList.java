package com.twq.ll.twopointer;

import com.twq.ll.ListNode;

/**
 * 请判断一个链表是否为回文链表。
 *
 * 输入: 1->2
 * 输出: false
 *
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 *      你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class _234_PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        // 1. 快慢指针找到链表的中心
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. 反转前半部分链表
        // pre 指针是前半部分链表的表头
        ListNode pre = null;
        while (head != slow) {
            ListNode next = head.next;

            head.next = pre;
            pre = head;

            head = next;
        }

        // 3. 找到后半部分链表的表头
        // 可以使用 slow 来表示后半部分链表表头
        if (fast != null) { // 表示奇数节点
            slow = slow.next;
        }

        // 4. 验证是否是回文链表
        while (pre != null && slow != null) {
            if (pre.val != slow.val)
                return false;
            pre = pre.next;
            slow = slow.next;
        }

        return true;
    }
}
