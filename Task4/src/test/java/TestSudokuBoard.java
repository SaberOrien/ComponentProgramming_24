import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TestSudokuBoard {
    private SudokuBoard sudokuBoard;
    private SudokuSolver sudokuSolver;

    @BeforeEach
    void setUp() {
        sudokuSolver = new BacktrackingSudokuSolver();
        sudokuBoard = new SudokuBoard(sudokuSolver);
    }

    @Test
    void checkBoard_ShouldReturnTrueForValidBoard() {
        sudokuSolver.solve(sudokuBoard);
        assertTrue(sudokuBoard.checkBoard(), "A solved board should be valid.");
    }

    @Test
    void checkBoard_ShouldReturnFalseForInvalidRowOnEmptyBoard() {
        for (int i = 0; i < 9; i++) {
            sudokuBoard.set(i, 0, (i % 9) + 1);
        }

        sudokuBoard.set(0, 0, 7);
        assertFalse(sudokuBoard.checkBoard(), "A board with an invalid row should be invalid.");
    }

    @Test
    void checkBoard_ShouldReturnFalseForInvalidColumnOnEmptyBoard() {
        for (int i = 0; i < 9; i++) {
            sudokuBoard.set(0, i, (i % 9) + 1);
        }
        sudokuBoard.set(0, 0, 7);
        assertFalse(sudokuBoard.checkBoard(), "A board with an invalid column should be invalid.");
    }

    @Test
    void checkBoard_ShouldReturnFalseForInvalidBox() {
        sudokuSolver.solve(sudokuBoard);
        sudokuBoard.set(0, 0, sudokuBoard.get(1, 1));
        assertFalse(sudokuBoard.checkBoard(), "A board with an invalid box should be invalid.");
    }

    @Test
    void solveGame_ShouldGenerateUniqueLayoutsOnSubsequentCalls() {
        assertTrue(sudokuBoard.solveGame());
        int[][] firstGameBoard = deepCopyBoard(sudokuBoard);

        setUp();
        assertTrue(sudokuBoard.solveGame());
        int[][] secondGameBoard = deepCopyBoard(sudokuBoard);

        assertFalse(Arrays.deepEquals(firstGameBoard, secondGameBoard));
    }

    @Test
    void setAndGetMethods_ShouldCorrectlyAssignAndRetrieveValues() {
        int expectedValue = 5;
        int x = 4, y = 3;

        sudokuBoard.set(x, y, expectedValue);
        int actualValue = sudokuBoard.get(x, y);

        assertEquals(expectedValue, actualValue, "The get method should return the value set by the set method.");
    }

    @Test
    void getRow_ShouldReflectBoardState() {
        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(1, 0, 2);

        SudokuRow row = sudokuBoard.getRow(0);

        assertEquals(1, row.getFields()[0].getFieldValue());
        assertEquals(2, row.getFields()[1].getFieldValue());
    }

    @Test
    void getColumn_ShouldReflectBoardState() {
        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(0, 1, 2);

        SudokuColumn column = sudokuBoard.getColumn(0);

        assertEquals(1, column.getFields()[0].getFieldValue());
        assertEquals(2, column.getFields()[1].getFieldValue());
    }

    @Test
    void getBox_ShouldReflectBoardState() {
        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(1, 1, 2);

        SudokuBox box = sudokuBoard.getBox(0, 0);

        assertEquals(1, box.getFields()[0].getFieldValue());
        assertEquals(2, box.getFields()[4].getFieldValue());
    }

    @Test
    void setField_InvalidPosition_ShouldThrowException() {
        SudokuRow row = new SudokuRow();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            row.setField(9, new SudokuField());
        });

        String expectedMessage = "Position out of bounds";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        exception = assertThrows(IllegalArgumentException.class, () -> {
            row.setField(-1, new SudokuField());
        });

        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

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

    private int[][] deepCopyBoard(SudokuBoard sudokuBoard) {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copy[i][j] = sudokuBoard.get(i, j);
            }
        }
        return copy;
    }
}
