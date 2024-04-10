package org.example;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BacktrackingSudokuSolver implements SudokuSolver {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;

    private boolean isSafe(SudokuBoard sudokuBoard, int row, int col, int num) {
        // Check row and column
        for (int d = 0; d < SIZE; d++) {
            if (sudokuBoard.get(row, d) == num || sudokuBoard.get(d, col) == num) {
                return false;
            }
        }
        // Check 3x3 box
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int r = boxRowStart; r < boxRowStart + 3; r++) {
            for (int c = boxColStart; c < boxColStart + 3; c++) {
                if (sudokuBoard.get(r, c) == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solve(SudokuBoard sudokuBoard) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (sudokuBoard.get(row, col) == EMPTY) {
                    List<Integer> numbers = IntStream.rangeClosed(1, SIZE).boxed().collect(Collectors.toList());
                    Collections.shuffle(numbers);
                    for (int number : numbers) {
                        if (isSafe(sudokuBoard, row, col, number)) {
                            sudokuBoard.set(row, col, number);
                            if (solve(sudokuBoard)) {
                                return true;
                            } else {
                                sudokuBoard.set(row, col, EMPTY);
                            }
                        }
                    }
                    return false; // backtrack
                }
            }
        }
        return true; // success
    }
}
