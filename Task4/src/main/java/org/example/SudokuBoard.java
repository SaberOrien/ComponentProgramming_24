package org.example;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SudokuBoard implements PropertyChangeListener {
    private SudokuField[] board;
    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver solver) {
        board = new SudokuField[81];
        for (int i = 0; i < board.length; i++) {
            board[i] = new SudokuField();
            board[i].addPropertyChangeListener(this);
        }
        this.sudokuSolver = solver;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //System.out.println("Field value changed. New value: " + evt.getNewValue());
        checkBoard();
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
        SudokuField[] rowFields = new SudokuField[9];
        System.arraycopy(board, y * 9, rowFields, 0, 9);
        return new SudokuRow(rowFields);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] columnFields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            columnFields[i] = new SudokuField(board[x + i * 9]);
        }
        return new SudokuColumn(columnFields);
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] boxFields = new SudokuField[9];
        int startX = x / 3 * 3;
        int startY = y / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Use the copy constructor to ensure encapsulation
                boxFields[i * 3 + j] = new SudokuField(board[(startY + i) * 9 + startX + j]);
            }
        }
        return new SudokuBox(boxFields);
    }

    public boolean solveGame() {
        return sudokuSolver.solve(this);
    }
}
