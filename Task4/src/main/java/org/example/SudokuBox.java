package org.example;

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

    public void setField(int row, int col, SudokuField field) {
        fields[row][col] = field;
    }
}
