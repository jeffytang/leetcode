package com.twq.queue;

import java.util.*;

/**
 * 单词接龙
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典，
 * 找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *  1. 每次转换只能改变一个字母。
 *  2. 转换过程中的中间单词必须是字典中的单词。
 *
 * 说明：
 *  1. 如果不存在这样的转换序列，返回 0。
 *  2. 所有单词具有相同的长度。
 *  3. 所有单词只由小写字母组成。
 *  4. 字典中不存在重复的单词。
 *  5. 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出: 5
 *
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: 0
 *
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 *
 * 链接：https://leetcode-cn.com/problems/word-ladder
 */
public class _127_WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        int wordLength = beginWord.length();

        // 预处理
        Map<String, List<String>>allComboDict = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < wordLength; i++) {
                char[] chars = word.toCharArray().clone();
                chars[i] = '*';
                String key = String.valueOf(chars);
                List<String> value = allComboDict.getOrDefault(key, new ArrayList<>());
                value.add(word);
                allComboDict.put(key, value);
            }
        }

        // BFS
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.remove();
                if (word.equals(endWord))
                    return step + 1;
                for (int j = 0; j < wordLength; j++) {
                    char[] chars = word.toCharArray().clone();
                    chars[j] = '*';
                    String key = String.valueOf(chars);
                    if (allComboDict.containsKey(key)) {
                        for (String combo : allComboDict.get(key)) {
                            if (!visited.contains(combo)) {
                                queue.add(combo);
                                visited.add(combo);
                            }
                        }
                    }
                }
            }
            step++;
        }

        return 0;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(new _127_WordLadder().ladderLength("hit", "cog", list));
    }
}
