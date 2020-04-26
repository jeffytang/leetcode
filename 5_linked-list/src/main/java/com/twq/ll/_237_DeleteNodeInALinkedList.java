package com.twq.ll;

/**
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
 *
 * 输入: head = [4,5,1,9], node = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 *
 * 输入: head = [4,5,1,9], node = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */
public class _237_DeleteNodeInALinkedList {
    public void deleteNode(ListNode node) {
        // 将下一个节点的值赋值给当前的 node
        node.val = node.next.val;
        // 删除下一个节点
        ListNode delNode = node.next;
        node.next = delNode.next;
        delNode.next = null;
    }

    public void deleteNode1(ListNode node) {
        while (node != null && node.next != null) {
            node.val = node.next.val;
            // 删除最后一个节点
            if (node.next.next == null)
                node.next = null;
            node = node.next;
        }
    }
}
