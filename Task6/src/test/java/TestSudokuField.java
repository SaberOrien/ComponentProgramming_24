import org.example.sudoku.SudokuField;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

public class TestSudokuField {
    @Test
    void sudokuField_SetAndGet() {
        SudokuField field = new SudokuField();
        field.setFieldValue(5);
        assertEquals(5, field.getFieldValue());
    }

    @Test
    void propertyChangeListener_ShouldNotReceiveUpdatesAfterRemoval() {
        SudokuField field = new SudokuField();
        final Boolean[] wasNotified = {false};

        PropertyChangeListener mockListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                wasNotified[0] = true;
            }
        };

        field.addPropertyChangeListener(mockListener);
        field.removePropertyChangeListener(mockListener);

        field.setFieldValue(5);

        assertFalse(wasNotified[0], "The removed listener should not receive property change notifications.");
    }

    //NEW TEST FOR OVERRIDES
    @Test
    void equals_WithSelf() {
        SudokuField field = new SudokuField();
        field.setFieldValue(5);
        assertTrue(field.equals(field), "An object must be equal to itself.");
    }

    @Test
    void equals_SameValue() {
        SudokuField field1 = new SudokuField();
        field1.setFieldValue(5);
        SudokuField field2 = new SudokuField();
        field2.setFieldValue(5);

        assertTrue(field1.equals(field2) && field2.equals(field1), "Equality must be symmetric.");
    }

    @Test
    void hashCode_ConsistencyWithEquals() {
        SudokuField field1 = new SudokuField();
        field1.setFieldValue(5);
        SudokuField field2 = new SudokuField();
        field2.setFieldValue(5);

        assertEquals(field1.hashCode(), field2.hashCode(), "Equal objects must have equal hash codes.");
    }

    @Test
    void equals_InequalityWithDifferentValues() {
        SudokuField field1 = new SudokuField();
        field1.setFieldValue(5);
        SudokuField field3 = new SudokuField();
        field3.setFieldValue(6);

        assertFalse(field1.equals(field3), "Objects with different states should not be equal.");
    }

    @Test
    void equals_NonEqualityWithNull() {
        SudokuField field1 = new SudokuField();
        field1.setFieldValue(5);

        assertFalse(field1.equals(null), "An object should not be equal to null.");
    }

    @Test
    void equals_NonEqualityWithDifferentClassObjects() {
        SudokuField field1 = new SudokuField();
        field1.setFieldValue(5);
        String notASudokuField = "Not a SudokuField";

        assertFalse(field1.equals(notASudokuField), "A SudokuField should not be equal to an object of a different class.");
    }

    @Test
    void toString_returnsCorrectValue() {
        SudokuField field = new SudokuField();
        field.setFieldValue(9);

        assertEquals(field.toString(), "9", "They should be equal");
    }
}
