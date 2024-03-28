package org.example;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

public abstract class SudokuSection implements PropertyChangeListener {
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

    public void setField(int position, SudokuField field) {
        if (position >= 0 && position < fields.length) {
            if (fields[position] != null) {
                fields[position].removePropertyChangeListener(this);
            }
            fields[position] = field;
            field.addPropertyChangeListener(this);
        } else {
            throw new IllegalArgumentException("Position out of bounds");
        }
    }

    public SudokuField[] getFields() {
        return fields;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //System.out.println("Field value changed. New value: " + evt.getNewValue());
        verify();
    }
}