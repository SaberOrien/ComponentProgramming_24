package org.example;

public class SudokuRow extends SudokuSection {

    public SudokuRow() {
        fields = new SudokuField[9];
        //        for (int i = 0; i < fields.length; i++) {
        //            fields[i] = new SudokuField();
        //            //fields[i].addPropertyChangeListener(this);
        //        }
    }
}
