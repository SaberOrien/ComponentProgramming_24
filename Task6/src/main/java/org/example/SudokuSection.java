package org.example;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class SudokuSection {
    protected ArrayList<SudokuField> fields;

    public SudokuSection(ArrayList<SudokuField> fields) {
        this.fields = new ArrayList<>(Arrays.asList(new SudokuField[fields.size()]));
        for (int i = 0; i < fields.size(); i++) {
            this.fields.set(i, new SudokuField(fields.get(i)));
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

    public ArrayList<SudokuField> getFields() {
        ArrayList<SudokuField> fieldsCopy = new ArrayList<>(Arrays.asList(new SudokuField[fields.size()]));
        for (int i = 0; i < fields.size(); i++) {
            fieldsCopy.set(i, new SudokuField(fields.get(i)));
        }
        return fieldsCopy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fields", fields)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SudokuSection that = (SudokuSection) obj;
        return new EqualsBuilder()
                .append(fields, that.fields)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(fields)
                .toHashCode();
    }
}
