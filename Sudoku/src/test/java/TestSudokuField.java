import org.example.sudoku.SudokuField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
}
