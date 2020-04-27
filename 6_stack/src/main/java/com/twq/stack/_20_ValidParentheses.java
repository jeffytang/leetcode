package com.twq.stack;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * 题目：[20]有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *  1. 左括号必须用相同类型的右括号闭合。
 *  2. 左括号必须以正确的顺序闭合。
 *
 * 注意空字符串可被认为是有效字符串。
 *
 * 输入: "()"
 * 输出: true
 *
 * 输入: "()[]{}"
 * 输出: true
 *
 * 输入: "(]"
 * 输出: false
 *
 * 输入: "([)]"
 * 输出: false
 *
 * 输入: "{[]}"
 * 输出: true
 *
 * 链接：https://leetcode-cn.com/problems/valid-parentheses/
 */
public class _20_ValidParentheses {
    public boolean isValid(String s) {
        if (s == null) return true;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == ' ') continue;
            if (c == '(' || c == '{' || c == '[') {
                // 如果是左括号，则直接入栈
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;
                // 判断栈顶元素是否等于左括号对应的右括号
                char topElement = stack.pop();

                char matched = '#';
                if (c == ')')
                    matched = '(';
                else if (c == ']')
                    matched = '[';
                else
                    matched = '{';

                if (matched != topElement)
                    return false;
            }
        }
        // 如果栈不为空，那么也算没有匹配好
        return stack.isEmpty();
    }
}
