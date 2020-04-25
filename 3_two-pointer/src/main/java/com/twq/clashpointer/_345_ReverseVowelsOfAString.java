package com.twq.clashpointer;

import java.util.HashSet;
import java.util.Set;

/**
 *  编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 *  输入: "hello"
 *  输出: "holle"
 *
 *  输入: "leetcode"
 *  输出: "leotcede"
 */
public class _345_ReverseVowelsOfAString {

    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');

        char[] chars = s.toCharArray();

        int left = 0, right = chars.length - 1;
        while (left < right) {
            char cLeft = chars[left];
            char cRight = chars[right];

            if (!vowels.contains(cLeft)) left++;
            if (!vowels.contains(cRight)) right++;

            if (vowels.contains(cLeft) && vowels.contains(cRight)) {
                chars[left] = cRight;
                chars[right] = cLeft;
                left++;
                right--;
            }
        }
        return new String(chars);
    }
}
