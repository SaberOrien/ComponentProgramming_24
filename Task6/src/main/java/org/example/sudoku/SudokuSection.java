package org.example.sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;

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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fields.size(); i++) {
            sb.append(fields.get(i).getFieldValue());
            if (i < fields.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof SudokuSection)) {
            return false;
        }
        SudokuSection other = (SudokuSection) o;

        return new EqualsBuilder()
                .append(fields, other.fields)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)   //must be odd
                .append(fields)
                .toHashCode();
    }
}

//@Override
//public String toString() {
//    return new ToStringBuilder(this)
//            .append("fields", fields)
//            .toString();
//}
