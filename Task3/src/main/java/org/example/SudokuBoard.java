package org.example;

public class SudokuBoard {
    private int[][] board;
    private static final int SIZE = 9;
    //private static final int EMPTY = 0;
    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver solver) {
        board = new int[SIZE][SIZE];
        this.sudokuSolver = solver;
    }

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        board[x][y] = value;
    }

    public boolean solveGame() {
        return sudokuSolver.solve(this);
    }
}
