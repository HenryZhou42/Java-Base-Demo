package org.zyh;

import java.util.Scanner;

/**
 *本项目实现了一个基于命令行界面的简单井字棋游戏，支持两名玩家轮流在3x3的游戏板上放置“X”和“O”，
 *目标是成为第一个在一行、一列或对角线上连成一线的玩家。
 *游戏还包括平局检测、结束后的游戏重开选项以及基本的输入验证功能，以提升用户体验。
 */

public class TicTacToe {
    // 定义玩家X的标记
    private static final char PLAYER_X = 'X';
    // 定义玩家O的标记
    private static final char PLAYER_O = 'O';
    // 定义空位的标记
    private static final char EMPTY = '-';

    // 初始化游戏棋盘，3x3的二维字符数组
    private final char[][] board = new char[3][3];
    // 创建Scanner对象用于接收用户输入
    private final Scanner scanner = new Scanner(System.in);

    // 主函数，游戏入口
    public static void main(String[] args) {
        // 创建TicTacToe实例并开始游戏
        new TicTacToe().startGame();
    }

    // 开始游戏的方法
    public void startGame() {
        boolean playAgain = true;
        // 循环让玩家选择是否再玩一局
        while (playAgain) {
            initializeBoard(); // 初始化棋盘
            printBoard(); // 打印当前棋盘状态

            char currentPlayer = PLAYER_X;// 当前玩家默认为X
            // 当没有玩家获胜且棋盘未满时继续游戏循环
            while (true) {
                // 提示当前玩家输入
                System.out.println("Player " + currentPlayer + ", please enter your move (row[1-3] column[1-3]): ");
                if(scanner.hasNextInt()){
                    int row = scanner.nextInt() - 1;
                    if(scanner.hasNextInt()){
                        int col = scanner.nextInt() - 1;
                        scanner.nextLine();

                        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
                            if (isValidMove(row, col)) {
                                board[row][col] = currentPlayer;
                                printBoard();
                                // 判断游戏是否结束（有赢家或平局）
                                if (checkWin(currentPlayer)) {
                                    System.out.println("Player " + currentPlayer + " wins!");
                                    break;
                                } else if (isBoardFull()) {
                                    System.out.println("It's a draw!");
                                    break;
                                }
                                // 玩家交替
                                currentPlayer = currentPlayer == PLAYER_X ? PLAYER_O : PLAYER_X;
                            } else {
                                System.out.println("That position is already taken. Try again.");
                            }
                        } else {
                            System.out.println("Row and column must be between 1 and 3. Try again.");
                        }
                    } else {
                        System.out.println("Please enter both row and column numbers.");
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("Invalid input. Please enter numbers only.");
                    scanner.nextLine();
                }
            }
            // 询问玩家是否想再玩一局
            System.out.println("Do you want to play again? (Y|N)");
            String answer = scanner.next().toLowerCase();
            if (!answer.equalsIgnoreCase("Y")) {
                playAgain = false;
            }
        }
        scanner.close();
        System.out.println("Thanks for playing Tic Tac Toe! Goodbye.");
    }

    // 初始化棋盘，设置所有格子为空
    private void initializeBoard() {
        for (char[] row : board) {
            java.util.Arrays.fill(row, EMPTY);
        }
    }

    // 打印当前棋盘的状态
    private void printBoard() {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " | ");
            }
            System.out.println("\n---------");
        }
    }

    // 检查指定位置是否可以下棋（即是否为空位）
    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY;
    }

    // 检查是否有玩家获胜
    private boolean checkWin(char player) {
        // 检查行
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // 检查列
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }
        // 检查对角线
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    // 判断棋盘是否已满，即是否为平局
    private boolean isBoardFull() {
        for (char[] row : board) {
            for (char c : row) {
                if (c == EMPTY) {
                    // 只要有一个空位就返回false
                    return false;
                }
            }
        }
        return true;  // 没有空位，返回true表示棋盘已满
    }

}
