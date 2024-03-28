package org.example;


public class SudokuBox extends SudokuSection {

    public SudokuBox() {
        fields = new SudokuField[9];
        //        for (int i = 0; i < 3; i++) {
        //            for (int j = 0; j < 3; j++) {
        //                fields[i * 3 + j] = new SudokuField();
        //                //fields[i * 3 + j].addPropertyChangeListener(this);
        //            }
        //        }
    }
}
