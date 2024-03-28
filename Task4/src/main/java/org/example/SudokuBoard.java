package org.example;

public class SudokuBoard {
    private SudokuField[] board;
    private SudokuRow[] rows = new SudokuRow[9];
    private SudokuColumn[] columns = new SudokuColumn[9];
    private SudokuBox[][] boxes = new SudokuBox[3][3];
    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver solver) {
        board = new SudokuField[81];
        this.sudokuSolver = solver;
        initializeSections();
        initializeBoard();
    }

    private void initializeSections() {
        for (int i = 0; i < 9; i++) {
            rows[i] = new SudokuRow();
            columns[i] = new SudokuColumn();
            boxes[i / 3][i % 3] = new SudokuBox();
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < 81; i++) {
            SudokuField field = new SudokuField();
            board[i] = field;

            int row = i / 9;
            int col = i % 9;
            int boxRow = row / 3;
            int boxCol = col / 3;

            rows[row].setField(col, field);
            columns[col].setField(row, field);
            boxes[boxRow][boxCol].setField(row % 3 * 3 + col % 3, field);
        }
    }

    public int get(int x, int y) {
        return board[y * 9 + x].getFieldValue();
    }

    public void set(int x, int y, int value) {
        board[y * 9 + x].setFieldValue(value);
    }

    public boolean checkBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!boxes[i][j].verify()) {
                    return false;
                }
            }
        }

        for (SudokuRow row : rows) {
            if (!row.verify()) {
                return false;
            }
        }

        for (SudokuColumn column : columns) {
            if (!column.verify()) {
                return false;
            }
        }

        return true;
    }


    public SudokuRow getRow(int y) {
        return rows[y];
    }

    public SudokuColumn getColumn(int x) {
        return columns[x];
    }

    public SudokuBox getBox(int x, int y) {
        return boxes[y / 3][x / 3];
    }

    public boolean solveGame() {
        return sudokuSolver.solve(this);
    }
}