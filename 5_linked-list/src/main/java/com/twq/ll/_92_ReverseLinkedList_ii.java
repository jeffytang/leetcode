package com.twq.ll;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明：
 *      1 ≤ m ≤ n ≤ 链表长度。
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class _92_ReverseLinkedList_ii {
    // 迭代
    public ListNode reverseBetween1(ListNode head, int m, int n) {
        if (head == null)
            return null;

        // 找到第 m 个节点以及前一个节点
        ListNode cur = head, pre = null;
        while (m > 1) {
            pre = cur;
            cur = cur.next;
            m--;
            n--;
        }
        // 保存第 m 个节点以及前一个节点
        ListNode mNode = cur, mNodePre = pre;

        // 反转第 m 个节点到第 n 个节点
        // 反转之后，pre 指向第 n 个节点，cur 指向第 n 个节点的下一个节点
        while (n > 0) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            n--;
        }

        // 将第 m 个节点的前一个节点的 next 设置为第 n 个节点 (即 pre 指针指向的节点)
        if (mNodePre != null)
            mNodePre.next = pre;
        else
            head = pre;

        // 将第 m 个节点的 next 指向第 n 个节点的下一个节点
        mNode.next = cur;

        return head;
    }

    // 递归
    // 参考：https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/bu-bu-chai-jie-ru-he-di-gui-di-fan-zhuan-lian-biao/
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // base case
        if (m == 1)
            return reverseN(head, n);

        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);

        return head;
    }

    ListNode successor = null; // 后驱节点
    // 反转以 head 为起点的 n 个节点，返回新的头结点
    private ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;

        return last;
    }
}
