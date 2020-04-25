package com.twq.ll.st;

import java.util.*;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 输入:
 * "tree"
 *
 * 输出:
 * "eert"
 *
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 *
 * 输入:
 * "cccaaa"
 *
 * 输出:
 * "cccaaa"
 *
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 *
 * 输入:
 * "Aabb"
 *
 * 输出:
 * "bbAa"
 *
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 */
public class _451_SortCharactersByFrequency {

    // 使用 Map 来实现
    public String frequencySort1(String s) {
        // 1. 统计每个字符出现的次数
        Map<Character, Integer> counter = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars)
            counter.put(c, counter.getOrDefault(c, 0) + 1);

        // 2. 统计出现的每个次数对应的字符，以及按照次数进行降序排列
        Map<Integer, List<Character>> orderedByCountMap = new TreeMap<>((o1, o2) -> o2 - o1);
        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            Character c = entry.getKey();
            Integer cnt = entry.getValue();
            List<Character> list = orderedByCountMap.getOrDefault(cnt, new ArrayList<>());
            list.add(c);
            orderedByCountMap.put(cnt, list);
        }

        // 3. 输出结果
        int i = 0;
        for (Map.Entry<Integer, List<Character>> entry : orderedByCountMap.entrySet()) {
            Integer cnt = entry.getKey();
            List<Character> list = entry.getValue();
            for (Character c : list)
                for (int j = 0; j < cnt; j++)
                    chars[i++] = c;
        }

        return new String(chars);
    }

    // 使用桶排序实现
    public String frequencySort2(String s) {
        // 1. 统计每个字符出现的次数
        Map<Character, Integer> counter = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars)
            counter.put(c, counter.getOrDefault(c, 0) + 1);

        // 2. 【桶排序】统计出现的每个次数对应的字符，以及按照次数进行升序排列
        List<Character>[] buckets = new ArrayList[chars.length + 1];
        for (Character key : counter.keySet()) {
            int frequency = counter.get(key);
            if (buckets[frequency] == null) buckets[frequency] = new ArrayList<>();
            buckets[frequency].add(key);
        }

        // 3. 输出结果
        int i = 0;
        for (int pos = buckets.length - 1; pos >= 0; pos--) {
            if (buckets[pos] == null) continue;
            for (Character c : buckets[pos])
                for (int j = 0; j < pos; j++)
                    chars[i++] = c;
        }

        return new String(chars);
    }

    // 使用大顶堆实现
    public String frequencySort3(String s) {
        // 1. 统计每个字符出现的次数
        Map<Character, Integer> counter = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars)
            counter.put(c, counter.getOrDefault(c, 0) + 1);

        // 2. 【大顶堆】将每个字符出现的次数插入堆，按照出现的次数进行降序排列
        PriorityQueue<Map.Entry<Character, Integer>> pq =
                new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        pq.addAll(counter.entrySet());

        // 3. 输出结果
        int i = 0;
        while (!pq.isEmpty()){
            Map.Entry<Character, Integer> entry = pq.poll();
            for (int j = 0; j < entry.getValue(); j++)
                chars[i++] = entry.getKey();
        }

        return new String(chars);
    }


    public static void main(String[] args) {
        _451_SortCharactersByFrequency e = new _451_SortCharactersByFrequency();
        System.out.println(e.frequencySort3("tree"));
    }

}
