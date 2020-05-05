package com.twq.bt;

/**
 * 单词搜索
 *
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 *  
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *  
 *
 * 提示：
 *      board 和 word 中只包含大写和小写英文字母。
 *      1 <= board.length <= 200
 *      1 <= board[i].length <= 200
 *      1 <= word.length <= 10^3
 *
 * 链接：https://leetcode-cn.com/problems/word-search
 */
public class _79_WordSearch {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m;
    private int n;
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;

        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (searchWord(board, word, 0, i, j))
                    return true;
            }
        }

        return false;
    }

    private boolean searchWord(char[][] board,
                               String word,
                               int index,
                               int row,
                               int col) {
        if (index == word.length() - 1) {
            return board[row][col] == word.charAt(index);
        }

        if (board[row][col] == word.charAt(index)) {
            visited[row][col] = true;
            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (inArea(newRow, newCol) && !visited[newRow][newCol]) {
                    if (searchWord(board, word, index + 1, newRow, newCol))
                        return true;
                }
            }
            // 重置，因为当前的元素可能被其他的路径选择
            // 这里用的是回溯的思想
            visited[row][col] = false;
        }


        return false;
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }
}
