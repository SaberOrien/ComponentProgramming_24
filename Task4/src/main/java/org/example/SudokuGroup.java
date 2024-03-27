package org.example;

import java.util.HashSet;
import java.util.Set;

public abstract class SudokuGroup {
    protected SudokuField[] fields;

    public boolean verify() {
        Set<Integer> seenValues = new HashSet<>();
        for (SudokuField field : fields) {
            int value = field.getFieldValue();
            if (value != 0 && !seenValues.add(value)) {
                return false;
            }
        }
        return true;
    };

    public SudokuField getField(int index) {
        return fields[index];
    }

    public void setField(int index, SudokuField field) {
        fields[index] = field;
    }
}
