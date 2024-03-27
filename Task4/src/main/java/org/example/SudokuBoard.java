package org.example;

public class SudokuBoard {
    private SudokuField[][] board;
    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver solver) {
        board = new SudokuField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField();
            }
        }
        this.sudokuSolver = solver;
    }

    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    public boolean solveGame() {
        return sudokuSolver.solve(this);
    }

    private boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            if (!getSudokuRow(i).verify() || !getSudokuColumn(i).verify()) {
                return false;
            }
        }
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                if (!getSudokuBox(row, col).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    public SudokuRow getSudokuRow(int y) {
        SudokuRow row = new SudokuRow();
        for (int i = 0; i < 9; i++) {
            row.setField(i, board[y][i]);
        }
        return row;
    }

    public SudokuColumn getSudokuColumn(int x) {
        SudokuColumn column = new SudokuColumn();
        for (int i = 0; i < 9; i++) {
            column.setField(i, board[i][x]);
        }
        return column;
    }

    public SudokuBox getSudokuBox(int x, int y) {
        SudokuBox box = new SudokuBox();
        int startX = x / 3 * 3;
        int startY = y / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                box.setField(i, j, board[startY + i][startX + j]);
            }
        }
        return box;
    }
}
