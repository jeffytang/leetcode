package com.twq.ll;

import java.util.ArrayDeque;

/**
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 进阶：
 *      如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 */
public class _445_AddTwoNumbers_ii {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 将 l1 所有节点放到栈中
        ArrayDeque<ListNode> stack1 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        // 将 l2 所有节点放到栈中
        ArrayDeque<ListNode> stack2 = new ArrayDeque<>();
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode lastNode = null; // 记录计算出来的第一个节点
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            // 先处理栈顶元素，也就是处理链表表尾元素
            int x = 0, y = 0;
            if (!stack1.isEmpty())
                x = stack1.pop().val;
            if (!stack2.isEmpty())
                y = stack2.pop().val;

            int sum = x + y + carry;
            ListNode currNode = new ListNode(sum % 10);
            currNode.next = lastNode;
            lastNode = currNode;

            carry = sum / 10;
        }

        if (carry != 0) {
            ListNode currNode = new ListNode(carry);
            currNode.next = lastNode;
            lastNode = currNode;
        }

        return lastNode;

    }
}
