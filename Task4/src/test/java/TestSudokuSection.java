import org.example.*;
import org.junit.jupiter.api.Test;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class TestSudokuSection {
    static class TestableSudokuRow extends SudokuRow {
        boolean wasNotified = false;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            super.propertyChange(evt);
            wasNotified = true;
        }
    }

    @Test
    void setField_ShouldRemoveListenerFromOldField() {
        TestableSudokuRow testRow = new TestableSudokuRow();
        SudokuField oldField = new SudokuField();
        SudokuField newField = new SudokuField();

        testRow.setField(0, oldField);

        testRow.wasNotified = false;

        testRow.setField(0, newField);

        oldField.setFieldValue(10);

        assertFalse(testRow.wasNotified, "The test row should not be notified by changes to the old field after it's replaced.");
    }
}
