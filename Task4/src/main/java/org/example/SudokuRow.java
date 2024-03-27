package org.example;
public class SudokuRow extends SudokuGroup {
    private SudokuField[] fields;

    public SudokuRow() {
        this.fields = new SudokuField[9];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new SudokuField();
        }
    }

    @Override
    public boolean verify() {
        return super.verify();
    }
}
