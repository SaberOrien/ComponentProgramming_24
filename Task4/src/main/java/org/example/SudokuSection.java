package org.example;

import java.util.HashSet;
import java.util.Set;

public abstract class SudokuSection {
    protected SudokuField[] fields;

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
        return fields;
    }
}