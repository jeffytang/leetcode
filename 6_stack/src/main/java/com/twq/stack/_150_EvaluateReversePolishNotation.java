package com.twq.stack;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * 题目：[150] 逆波兰表达式求值
 *
 * 根据逆波兰表示法，求表达式的值。
 *
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *  1. 整数除法只保留整数部分。
 *  2. 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 *
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 *
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 *
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 *
 * 链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */
public class _150_EvaluateReversePolishNotation {
    // 栈
    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        Integer op1;
        Integer op2;
        for (String element : tokens) {
            switch (element) {
                case "+" :
                    op1 = stack.pop();
                    op2 = stack.pop();
                    stack.push(op2 + op1);
                    break;
                case "-" :
                    op1 = stack.pop();
                    op2 = stack.pop();
                    stack.push(op2 - op1);
                    break;
                case "*" :
                    op1 = stack.pop();
                    op2 = stack.pop();
                    stack.push(op2 * op1);
                    break;
                case "/" :
                    op1 = stack.pop();
                    op2 = stack.pop();
                    stack.push(op2 / op1);
                    break;
                default :
                    stack.push(Integer.parseInt(element));
            }
        }
        return stack.pop();
    }

    // 使用数组模拟栈
    public int evalRPN2(String[] tokens) {
        int[] stack = new int[tokens.length / 2 + 1];
        int index = 0;
        for (String element : tokens) {
            switch (element) {
                case "+" :
                    stack[index - 2] += stack[--index];
                    break;
                case "-" :
                    stack[index - 2] -= stack[--index];
                    break;
                case "*" :
                    stack[index - 2] *= stack[--index];
                    break;
                case "/" :
                    stack[index - 2] /= stack[--index];
                    break;
                default :
                    stack[index++] = Integer.parseInt(element);
            }
        }
        return stack[0];
    }
}
