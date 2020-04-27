package com.twq.stack;

import java.util.*;

/**
 * 题目：扁平化嵌套列表迭代器
 *
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 *
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 *
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 *
 * 输入: [1,[4,[6]]]
 * 输出: [1,4,6]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 *
 */
public class _341_FlattenNestedListIterator {

}

class NestedIterator implements Iterator<Integer> {
    List<Integer> data = new ArrayList<>();
    Iterator<Integer> it ;

    public NestedIterator(List<NestedInteger> nestedIntegerList) {
        for (NestedInteger nestedInteger : nestedIntegerList) {
            flatten(nestedInteger);
        }
        it = data.iterator();
    }

    private void flatten(NestedInteger nestedInteger) {
        ArrayDeque<NestedInteger> stack = new ArrayDeque<>();
        stack.push(nestedInteger);
        while (!stack.isEmpty()) {
            NestedInteger ni = stack.pop();
            if (ni.isInteger()) {
                data.add(ni.getInteger());
            } else {
                List<NestedInteger> nis = ni.getList();
                for (int i = nis.size() - 1; i >= 0; i--) {
                    stack.add(nis.get(i));
                }
            }
        }
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public Integer next() {
        return it.next();
    }

    interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
