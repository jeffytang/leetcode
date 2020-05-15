package com.twq.design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * LFU缓存
 *
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。它应该支持以下操作：get 和 put。
 *      1. get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 *      2. put(key, value) - 如果键已存在，则变更其值；如果键不存在，请插入键值对。
 *      当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。
 *      在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除最久未使用的键。
 *
 * 「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。
 *
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 *
 * 示例：
 *
 * LFUCache cache = new LFUCache( 2  capacity (缓存容量)  );
        *
        *cache.put(1,1);
        *cache.put(2,2);
        *cache.get(1);       // 返回 1
        *cache.put(3,3);    // 去除 key 2
        *cache.get(2);       // 返回 -1 (未找到key 2)
        *cache.get(3);       // 返回 3
        *cache.put(4,4);    // 去除 key 1
        *cache.get(1);       // 返回 -1 (未找到 key 1)
        *cache.get(3);       // 返回 3
        *cache.get(4);       // 返回 4
        *

        *链接：https://leetcode-cn.com/problems/lfu-cache
 */
public class _460_LfuCache {
    // 每个 key 对应的 value 的映射
    private Map<Integer, Integer> keyToVal;
    // 每个 key 使用的次数
    private Map<Integer, Integer> keyToCount;
    // 每个 count 对应的所有的 keys (按照最近使用的顺序组织)
    private Map<Integer, LinkedHashSet<Integer>> countToLFUKeys;

    private int capacity;

    // 记录整个缓存中所有 key 中使用最小的次数
    private int minCount;

    public _460_LfuCache(int capacity) {
        keyToVal = new HashMap<>();
        keyToCount = new HashMap<>();
        countToLFUKeys = new HashMap<>();

        this.capacity = capacity;
        this.minCount = 0;
    }

    public int get(int key) {
        Integer value = keyToVal.get(key);
        if (value == null) {
            return -1;
        }

        // 维护这个 key 对应的 count
        // 1. 从这个 key 目前对应的 count 的集合中删除掉这个 key
        int count = keyToCount.get(key);
        countToLFUKeys.get(count).remove(key);

        // 2. 更新最小使用的次数
        // 如果当前的 count 等于最小次数，并且当前的 count 没有的 key，那么将最小次数加 1
        if (count == minCount && countToLFUKeys.get(count).size() == 0) {
            minCount++;
        }

        // 3. 将 key 记录到 count + 1 中的集合中
        putCount(key, count + 1);

        return value;
    }

    private void putCount(int key, int count) {
        keyToCount.put(key, count);
        countToLFUKeys.computeIfAbsent(count, i -> new LinkedHashSet<>());
        countToLFUKeys.get(count).add(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) return;

        if (keyToVal.containsKey(key)) {
            // 更新 key 对应的 value 值
            keyToVal.put(key, value);
            // 更新 key 对应的 count 值
            get(key);
            return;
        }

        if (keyToVal.size() >= capacity) {
            // 删除最少使用的 key
            int removeKey = countToLFUKeys.get(minCount).iterator().next();
            countToLFUKeys.get(minCount).remove(removeKey);
            keyToVal.remove(removeKey);
            keyToCount.remove(removeKey);
        }

        // 新增一个缓存中不存在的 key
        keyToVal.put(key, value);
        minCount = 1;
        putCount(key, minCount);
    }
}
