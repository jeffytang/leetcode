package com.twq.st;

import java.util.HashMap;
import java.util.Map;

/**
 *  给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *
 *  这里的 遵循 指完全匹配，
 *  例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 *  输入: pattern = "abba", str = "dog cat cat dog"
 *  输出: true
 *
 *  输入:pattern = "abba", str = "dog cat cat fish"
 *  输出: false
 *
 *  输入: pattern = "aaaa", str = "dog cat cat dog"
 *  输出: false
 *
 *  输入: pattern = "abba", str = "dog dog dog dog"
 *  输出: false
 */
public class _290_WordPattern {

    public boolean wordPattern(String pattern, String str) {
        char[] chars = pattern.toCharArray();
        String[] words = str.split(" ");
        if (chars.length != words.length)
            return false;

        Map<Character, String> charWordMap = new HashMap<>();
        Map<String, Character> wordCharMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            char currChar = chars[i];
            String currWord = words[i];
            if (charWordMap.containsKey(currChar)
                    && !charWordMap.get(currChar).equals(currWord)) {
                return false;
            } else if (wordCharMap.containsKey(currWord)
                    && !wordCharMap.get(currWord).equals(currChar)) {
                return false;
            } else {
                charWordMap.put(currChar, currWord);
                wordCharMap.put(currWord, currChar);
            }
        }

        return true;
    }

}
