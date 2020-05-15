package com.twq.design;

import java.util.HashMap;
import java.util.Map;

/**
 *  LRU缓存机制
 *
 *  运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。
 *  它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 *  获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 *
 *  写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。
 *
 *  当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *  进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 示例：
 *      LRUCache cache = new LRUCache( 2  );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4


        链接：https://leetcode-cn.com/problems/lru-cache
 */
// HashMap + 双向链表实现
// HashMap 用于维护 key 对应的链表节点，用于查找
// 双向链表用于维护每个 key 使用的情况，表头存储经常使用的 key，而表尾则存储最不经常使用的 key
// 所以，每次查询的数据、插入的数据以及修改的数据都应该放在表头，每次缓存满了后，都应该删除队尾的元素
public class _146_LruCache {
    private class Node {
        int key;
        int value;
        Node next;
        Node prev;
    }

    private Map<Integer, Node> cache;
    private int size;
    private Node head;
    private Node tail;
    private int capacity;

    public _146_LruCache(int capacity) {
        cache = new HashMap<>();
        size = 0;
        this.capacity = capacity;

        // 先初始化，作为哨兵节点
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 将查询到的 node 移动到表头
        moveNodeToHead(node);
        return node.value;
    }

    // 删除一个节点
    private void removeNode(Node node) {
        Node preNode = node.prev;
        Node nextNode = node.next;

        preNode.next = nextNode;
        nextNode.prev = preNode;

        node.prev = null;
        node.next = null;
    }

    // 将一个节点添加到头节点
    private void addNodeToHead(Node node) {
        node.next = head.next;
        head.next.prev = node;

        head.next = node;
        node.prev = head;
    }

    private void moveNodeToHead(Node node) {
        removeNode(node);
        addNodeToHead(node);
    }

    public void put(int key, int value) {
        // 先从缓存拿
        Node node = cache.get(key);

        // 缓存没有则创建节点
        if (node == null) {
            // 1. 创建节点
            node = new Node();
            node.key = key;
            node.value = value;
            // 2. 维护链表和缓存
            addNodeToHead(node);
            cache.put(key, node);
            size++;
            // 3. 判断缓存容量大小
            if (size > capacity) {
                Node removedNode = removeTailNode();
                cache.remove(removedNode.key);
                size--;
            }
        } else {
            // 有的话，则将节点放到头结点
            moveNodeToHead(node);
            node.value = value;
        }
    }

    // 删除尾节点
    private Node removeTailNode() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }
}
