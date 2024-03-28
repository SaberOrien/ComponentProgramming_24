package org.example;

public class SudokuBoard {
    private SudokuField[] board;
    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver solver) {
        board = new SudokuField[81];
        for (int i = 0; i < board.length; i++) {
            board[i] = new SudokuField();
        }
        this.sudokuSolver = solver;
    }

    public int get(int x, int y) {
        return board[y * 9 + x].getFieldValue();
    }

    public void set(int x, int y, int value) {
        board[y * 9 + x].setFieldValue(value);
    }

    public boolean checkBoard() {
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                if (!getBox(row, col).verify()) {
                    return false;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            if (!getRow(i).verify() || !getColumn(i).verify()) {
                return false;
            }
        }

        return true;
    }

    public SudokuRow getRow(int y) {
        SudokuRow row = new SudokuRow();
        System.arraycopy(board, y * 9, row.getFields(), 0, 9);
        return row;
    }

    public SudokuColumn getColumn(int x) {
        SudokuColumn column = new SudokuColumn();
        for (int i = 0; i < 9; i++) {
            column.getFields()[i] = board[x + i * 9];
        }
        return column;
    }


    public SudokuBox getBox(int x, int y) {
        SudokuBox box = new SudokuBox();
        int startX = x / 3 * 3;
        int startY = y / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                box.getFields()[i * 3 + j] = board[(startY + i) * 9 + startX + j];
            }
        }
        return box;
    }

    public boolean solveGame() {
        return sudokuSolver.solve(this);
    }
}