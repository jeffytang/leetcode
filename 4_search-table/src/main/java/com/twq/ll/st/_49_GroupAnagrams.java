package com.twq.ll.st;

import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。
 * 字母异位词指字母相同，但排列不同的字符串。
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * 注意：
 *      1. 所有输入均为小写字母。
 *      2. 不考虑答案输出的顺序。
 */
public class _49_GroupAnagrams {
    // 排序数组分类
    // 当且仅当它们的排序字符串相等时，两个字符串是字母异位词。
    // 时间复杂度：O(nklogk) , k 是 strs 中字符串的长度
    // 空间复杂度：O(nk)
    public List<List<String>> groupAnagrams(String[] strs) {
        // key 是排序后的单词
        // value 是排序后相同单词的所有单词
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        List<List<String>> res = new ArrayList<>(map.values());
        return res;
    }

    // 时间复杂度：O(n * k)
    // 空间复杂度：O(n * k)
    public List<List> groupAnagrams2(String[] strs) {
        // 考察了哈希函数的基本知识，只要 26 个即可
        // （小写字母ACSII 码 - 97 ）以后和质数的对应规则，这个数组的元素顺序无所谓
        // key 是下标，value 就是数值
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
                73, 79, 83, 89, 97, 101};

        // key 是字符串计算出来的 hash 值
        // value 是相同的 hash 值对应的单词
        Map<Long, List<String>> map = new HashMap<>();
        for (String str : strs) {
            long hashValue = 1;
            for (char c : str.toCharArray())
                hashValue *= primes[c - 'a'];

            if (!map.containsKey(hashValue)) map.put(hashValue, new ArrayList<>());
            map.get(hashValue).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
