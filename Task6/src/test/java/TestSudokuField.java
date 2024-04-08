import org.example.SudokuField;
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

    @Test
    void equalsAndHashCode() {
        SudokuField field1 = new SudokuField();
        field1.setFieldValue(5);
        SudokuField field2 = new SudokuField();
        field2.setFieldValue(5);
        SudokuField field3 = new SudokuField();
        field3.setFieldValue(6);

        // Reflexivity
        assertTrue(field1.equals(field1));

        // Symmetry
        assertTrue(field1.equals(field2) && field2.equals(field1));

        // Consistency
        assertEquals(field1.hashCode(), field2.hashCode());

        // Different objects
        assertFalse(field1.equals(field3));

        // Non-nullity
        assertFalse(field1.equals(null));
    }

    @Test
    void equals_WithDifferentClassObject() {
        SudokuField field = new SudokuField();
        field.setFieldValue(5);

        // Create an object of a different class. Using String as an example.
        String notASudokuField = "Not a SudokuField";

        // Test that a SudokuField is not equal to an object of a different class
        assertFalse(field.equals(notASudokuField), "SudokuField should not be equal to an object of a different class.");
    }


}
