package org.example;

public class SudokuColumn extends SudokuSection  {

    public SudokuColumn() {
        this.fields = new SudokuField[9];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new SudokuField();
        }
    }
}
