package com.twq.st;

import java.util.HashMap;
import java.util.Map;

/**
 *  给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 *  输入: s = "anagram", t = "nagaram"
 *  输出: true
 *
 *  输入: s = "rat", t = "car"
 *  输出: false
 *
 *  说明：
 *      1. 你可以假设字符串只包含小写字母。
 */
public class _242_ValidAnagram {

    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> counter = new HashMap<>();
        for (char c : s.toCharArray())
            counter.put(c, counter.getOrDefault(c, 0) + 1);

        for (char c : t.toCharArray()) {
            if (!counter.containsKey(c) || counter.get(c) <= 0)
                return false;
            counter.put(c, counter.get(c) - 1);
        }

        return true;
    }

    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] counter = new int[26];
        for (char c : s.toCharArray())
            counter[c - 'a']++;

        for (char c : t.toCharArray()) {
            if (counter[c - 'a'] <= 0)
                return false;
            counter[c - 'a']--;
        }

        return true;
    }


    public static void main(String[] args) {
        _242_ValidAnagram v = new _242_ValidAnagram();
        System.out.println(v.isAnagram1("anagram", "nagaram"));
    }
}
