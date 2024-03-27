package org.example;
public class SudokuColumn extends SudokuGroup {
    private SudokuField[] fields;

    public SudokuColumn() {
        this.fields = new SudokuField[9];
        for(int i = 0; i < fields.length; i++) {
            fields[i] = new SudokuField();
        }
    }

    @Override
    public boolean verify() {
        return super.verify();
    }
}

