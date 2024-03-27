package org.example;

import java.util.HashSet;
import java.util.Set;

public class SudokuBox extends SudokuGroup {
    private SudokuField[][] fields;

    public SudokuBox() {
        this.fields = new SudokuField[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j] = new SudokuField();
            }
        }
    }

    public boolean verify() {
        return super.verify();
    }
    public void setField(int row, int col, SudokuField field) {
        fields[row][col] = field;
    }
}
