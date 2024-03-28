import org.example.*;
import org.junit.jupiter.api.Test;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class TestSudokuSection {
    // Custom subclass for testing
    static class TestableSudokuRow extends SudokuRow {
        boolean wasNotified = false;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            super.propertyChange(evt);
            wasNotified = true; // Flag set when notification is received
        }
    }

    @Test
    void setField_ShouldRemoveListenerFromOldField() {
        TestableSudokuRow testRow = new TestableSudokuRow();
        SudokuField oldField = new SudokuField();
        SudokuField newField = new SudokuField();

        // Set old field in the test row
        testRow.setField(0, oldField);

        // Reset notification flag before replacing the field
        testRow.wasNotified = false;

        // Replace old field with new field at the same position
        testRow.setField(0, newField);

        // Change the value of the old field
        oldField.setFieldValue(10);

        // Assert that the test row was not notified by the old field change
        assertFalse(testRow.wasNotified, "The test row should not be notified by changes to the old field after it's replaced.");
    }
}
