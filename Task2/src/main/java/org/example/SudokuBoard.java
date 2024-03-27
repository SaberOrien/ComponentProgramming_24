package org.example;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuBoard {
    private int[][] board;
    private static final int SIZE = 9;
    private static final int EMPTY = 0;

    public SudokuBoard() {
        board = new int[SIZE][SIZE];
    }

    public int[][] getBoard() {
        int[][] copyBoard = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(board[i], 0, copyBoard[i], 0, SIZE);
        }
        return copyBoard;
    }


    private boolean isSafe(int row, int col, int num) {
        // Check row and column
        for (int d = 0; d < SIZE; d++) {
            if (board[row][d] == num || board[d][col] == num) {
                return false;
            }
        }
        // Check 3x3 box
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int r = boxRowStart; r < boxRowStart + 3; r++) {
            for (int c = boxColStart; c < boxColStart + 3; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean fillBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    List<Integer> numbers = IntStream.rangeClosed(1, SIZE).boxed().collect(Collectors.toList());
                    Collections.shuffle(numbers);
                    for (int number : numbers) {
                        if (isSafe(row, col, number)) {
                            board[row][col] = number;
                            if (fillBoard()) {
                                return true;
                            } else {
                                board[row][col] = EMPTY;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
