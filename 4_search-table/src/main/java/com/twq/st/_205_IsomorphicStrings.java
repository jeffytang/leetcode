package com.twq.st;

import java.util.HashMap;
import java.util.Map;

/**
 *  给定两个字符串 s 和 t，判断它们是否是同构的。
 *
 *  如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 *
 *  所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。
 *  两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 *  输入: s = "egg", t = "add"
 *  输出: true
 *
 *  输入: s = "foo", t = "bar"
 *  输出: false
 *
 *  输入: s = "paper", t = "title"
 *  输出: true
 *
 *  说明：
 *      你可以假设 s 和 t 具有相同的长度。
 */
public class _205_IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2tCharMap = new HashMap<>();
        Map<Character, Character> t2sCharMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            if (s2tCharMap.containsKey(sChar)
                    && !s2tCharMap.get(sChar).equals(tChar))
                return false;
            else if (t2sCharMap.containsKey(tChar)
                    && !t2sCharMap.get(tChar).equals(sChar))
                return false;
            else {
                s2tCharMap.put(sChar, tChar);
                t2sCharMap.put(tChar, sChar);
            }
        }

        return true;
    }
}
