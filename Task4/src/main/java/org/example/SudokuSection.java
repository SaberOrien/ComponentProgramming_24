package org.example;

import java.util.HashSet;
import java.util.Set;

public abstract class SudokuSection {
    protected SudokuField[] fields;

    public SudokuSection(SudokuField[] fields) {
        this.fields = new SudokuField[fields.length];
        for (int i = 0; i < fields.length; i++) {
            this.fields[i] = new SudokuField(fields[i]);
        }
    }

    public boolean verify() {
        Set<Integer> presentValues = new HashSet<>();
        for (SudokuField field : fields) {
            int value = field.getFieldValue();
            if (value != 0 && !presentValues.add(value)) {
                return false;
            }
        }
        return true;
    }

    public SudokuField[] getFields() {
        SudokuField[] fieldsCopy = new SudokuField[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldsCopy[i] = new SudokuField(fields[i]);
        }
        return fieldsCopy;
    }

}
