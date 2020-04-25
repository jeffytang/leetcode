package com.twq.ll;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static ListNode create(int... eles) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (int ele : eles) {
            ListNode node = new ListNode(ele);
            curr.next = node;
            curr = curr.next;
        }

        return dummy.next;
    }
}
