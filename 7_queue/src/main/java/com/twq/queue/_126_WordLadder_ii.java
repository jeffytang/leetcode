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
public class _126_WordLadder_ii {



    public List<List<String>> ladderLength(String beginWord,
                                           String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();

        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return res;

        // 存储每层添加元素之后的结果："ab","if",{"cd","af","ib","if"}；
        // 1. 第一层：{"ab"}
        // 2. 第二层：{"ab","af"}、{"ab","ib"}
        // 3. 第三层：{"ab","af","if"}、{"ab","ib","if"}
        Queue<List<String>> queue = new LinkedList<>();
        List<String> list = Arrays.asList(beginWord);
        queue.add(list);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        boolean isFoundEndWord = false;
        while (!queue.isEmpty() && !isFoundEndWord) {
            int size = queue.size();
            // 该层添加的所有元素：每层必须在所有结果都添加完新的单词之后，再将这些单词统一添加到已使用单词集合
            // 如果直接添加到 visited 中，会导致该层本次结果添加之后的相同添加行为失败
            // 如：该层遇到目标单词，有两条路径都可以遇到，但是先到达的将该单词添加进 visited 中，会导致第二条路径无法添加
            Set<String> subVisited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                List<String> path = queue.remove();
                // 拿到上一层的单词
                String word = path.get(path.size() - 1);
                // 寻找该单词的下一个符合条件的单词
                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char temp = chars[j];
                    // 改变当前 word 中的一个字符
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[j] = ch;
                        if (temp == ch) continue;
                        String changedWord = new String(chars);
                        // 符合条件：在 wordList 中 && 之前的层没有使用过
                        if (wordSet.contains(changedWord)
                                && !visited.contains(changedWord)) {
                            // 生成新的路径
                            List<String> newPath = new ArrayList<>(path);
                            newPath.add(changedWord);

                            // 如果该单词是目标单词：将该路径添加到结果集中，查询截止到该层
                            if (changedWord.equals(endWord)) {
                                isFoundEndWord = true;
                                res.add(newPath);
                            }

                            queue.add(newPath);
                            subVisited.add(changedWord);
                        }
                    }
                    chars[j] = temp;
                }

            }
            visited.addAll(subVisited);
        }

        return res;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(new _126_WordLadder_ii().ladderLength("hit", "cog", list));
    }
}
