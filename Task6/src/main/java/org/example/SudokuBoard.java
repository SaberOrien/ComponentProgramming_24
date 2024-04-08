package org.example;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

public class SudokuBoard implements PropertyChangeListener {
    private ArrayList<SudokuField> board;
    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver solver) {
        board = new ArrayList<>(Arrays.asList(new SudokuField[81]));
        for (int i = 0; i < 81; i++) {
            board.set(i, new SudokuField());
            board.get(i).addPropertyChangeListener(this);
        }
        this.sudokuSolver = solver;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //System.out.println("Field value changed. New value: " + evt.getNewValue());
        checkBoard();
    }

    public int get(int x, int y) {
        return board.get(y * 9 + x).getFieldValue();
    }

    public void set(int x, int y, int value) {
        board.get(y * 9 + x).setFieldValue(value);
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
        ArrayList<SudokuField> rowFields = new ArrayList<>(board.subList(y * 9, y * 9 + 9));
        return new SudokuRow(rowFields);
    }

    public SudokuColumn getColumn(int x) {
        ArrayList<SudokuField> columnFields = new ArrayList<>(Arrays.asList(new SudokuField[9]));
        for (int i = 0; i < 9; i++) {
            columnFields.set(i, new SudokuField(board.get(x + i * 9)));
        }
        return new SudokuColumn(columnFields);
    }

    public SudokuBox getBox(int x, int y) {
        ArrayList<SudokuField> boxFields = new ArrayList<>(Arrays.asList(new SudokuField[9]));
        int startX = x / 3 * 3;
        int startY = y / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Use the copy constructor to ensure encapsulation
                boxFields.set(i * 3 + j, new SudokuField(board.get((startY + i) * 9 + startX + j)));
            }
        }
        return new SudokuBox(boxFields);
    }

    public boolean solveGame() {
        return sudokuSolver.solve(this);
    }

    public void copyFrom(SudokuBoard other) {
        for (int i = 0; i < board.size(); i++) {
            this.board.get(i).setFieldValue(other.board.get(i).getFieldValue());
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("board", board)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) obj;
        return new EqualsBuilder()
                .append(board, that.board)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(board)
                .toHashCode();
    }

}
