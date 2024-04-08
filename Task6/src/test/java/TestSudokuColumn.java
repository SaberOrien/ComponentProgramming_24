import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSudokuColumn {

    private SudokuBoard sudokuBoard;
    private SudokuSolver sudokuSolver;

    @BeforeEach
    void setUp() {
        sudokuSolver = new BacktrackingSudokuSolver();
        sudokuBoard = new SudokuBoard(sudokuSolver);
    }

    @Test
    void getColumn_ShouldReflectBoardState() {
        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(0, 1, 2);

        SudokuColumn column = sudokuBoard.getColumn(0);

        assertEquals(1, column.getFields().get(0).getFieldValue());
        assertEquals(2, column.getFields().get(1).getFieldValue());
    }

    @Test
    void toString_ShouldContainCorrectInformation() {
        SudokuColumn column = sudokuBoard.getColumn(0);
        assertTrue(column.toString().contains("fields"), "toString should contain 'fields' information.");
    }

    @Test
    void equals_Reflexivity() {
        SudokuColumn column = sudokuBoard.getColumn(0);
        assertEquals(column, column, "A column should be equal to itself.");
    }

    @Test
    void equals_Symmetry() {
        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(0, 1, 2);

        SudokuColumn column1 = sudokuBoard.getColumn(0);
        SudokuColumn column2 = sudokuBoard.getColumn(0);

        assertEquals(column1, column2, "Two columns with the same values should be equal.");
        assertEquals(column2, column1, "Two columns with the same values should be equal (symmetry).");
    }

    @Test
    void equals_DifferentColumns() {
        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(0, 1, 2);

        SudokuColumn column1 = sudokuBoard.getColumn(0);
        SudokuColumn column2 = sudokuBoard.getColumn(1); // Assuming column1 and column2 have different values

        assertNotEquals(column1, column2, "Two different columns should not be equal.");
    }

    @Test
    void equals_NullAndOtherClass() {
        SudokuColumn column = sudokuBoard.getColumn(0);
        assertNotEquals(column, null, "A column should not be equal to null.");
        assertNotEquals(column, new Object(), "A column should not be equal to an object of a different class.");
    }

    @Test
    void hashCode_ConsistencyWithEquals() {
        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(0, 1, 2);

        SudokuColumn column1 = sudokuBoard.getColumn(0);
        SudokuColumn column2 = sudokuBoard.getColumn(0);

        assertEquals(column1.hashCode(), column2.hashCode(), "Two equal columns should have the same hash code.");
    }
}
