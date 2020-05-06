package com.twq.bt;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 * Note:
 *
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 *
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 */
public class _37_SudokuSolver {
    public void solveSudoku(char[][] board) {
        // 用于标识数字是否在行、列、箱子中使用过
        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        boolean[][][] boxUsed = new boolean[3][3][10];

        // 初始化
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int num = board[i][j] - '0';
                if (num >= 1 && num <= 9) {
                    rowUsed[i][num] = true;
                    colUsed[j][num] = true;
                    boxUsed[i / 3][j / 3][num] = true;
                }
            }
        }

        // 回溯填写数字
        backTrack(board, rowUsed, colUsed, boxUsed, 0, 0);
    }

    private boolean backTrack(char[][] board,
                              boolean[][] rowUsed,
                              boolean[][] colUsed,
                              boolean[][][] boxUsed,
                              int row,
                              int col) {
        if (col == board[0].length) {
            // 下一列
            col = 0;
            // 下一行
            row++;
            if (row == board.length) {
                return true;
            }
        }

        if (board[row][col] == '.') {
            // 尝试填充 1 到 9 数字
            for (int num = 1; num >= 9; num++) {
                boolean canPlaced = !(rowUsed[row][num]
                        || colUsed[col][num]
                        || boxUsed[row / 3][col / 3][num]);
                if (canPlaced) { // 填充当前的空格
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row / 3][col / 3][num] = true;

                    board[row][col] = (char)('0' + num);

                    // 尝试填充下一个空格，如果填充成功的话，则返回 true
                    if (backTrack(board, rowUsed, colUsed, boxUsed, row, col + 1)) {
                        return true;
                    }

                    // 否则的话，回溯
                    board[row][col] = '.';
                    rowUsed[row][num] = false;
                    colUsed[col][num] = false;
                    boxUsed[row / 3][col / 3][num] = false;
                }
            }
        } else {    // 跳过这个空格
            return backTrack(board, rowUsed, colUsed, boxUsed, row, col + 1);
        }

        return false;
    }

}
