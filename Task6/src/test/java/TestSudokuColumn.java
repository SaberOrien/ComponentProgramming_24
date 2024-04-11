import org.example.sudoku.BacktrackingSudokuSolver;
import org.example.sudoku.SudokuBoard;
import org.example.sudoku.SudokuColumn;
import org.example.sudoku.SudokuSolver;
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

    //NEW TESTS
    @Test
    void equals_WithSelf() {
        SudokuColumn column = sudokuBoard.getColumn(0);
        assertTrue(column.equals(column), "A column should be equal to itself.");
    }

    @Test
    void equals_SameColumn() {
        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(0, 1, 2);

        SudokuColumn column1 = sudokuBoard.getColumn(0);
        SudokuColumn column2 = sudokuBoard.getColumn(0);

        assertTrue(column1.equals(column2), "Two columns with the same values should be equal.");
    }

    @Test
    void equals_DifferentColumns() {
        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(0, 1, 2);

        SudokuColumn column1 = sudokuBoard.getColumn(0);
        SudokuColumn column2 = sudokuBoard.getColumn(1);

        assertFalse(column1.equals(column2), "Two different columns should not be equal.");
    }

    @Test
    void equals_NullAndOtherClass() {
        SudokuColumn column = sudokuBoard.getColumn(0);
        assertFalse(column.equals(null), "A column should not be equal to null.");
        String differentClass = new String("Not a column");
        assertFalse(column.equals(differentClass), "A column should not be equal to an object of a different class.");
    }

    @Test
    void hashCode_ConsistencyWithEquals() {
        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(0, 1, 2);

        SudokuColumn column1 = sudokuBoard.getColumn(0);
        SudokuColumn column2 = sudokuBoard.getColumn(0);

        assertEquals(column1.hashCode(), column2.hashCode(), "Two equal columns should have the same hash code.");
    }

    @Test
    void toString_CorrectlyDisplaysColumn() {
        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(0, 1, 2);

        SudokuColumn column = sudokuBoard.getColumn(0);

        assertEquals(column.toString(), "1, 2, 0, 0, 0, 0, 0, 0, 0", "The column should match");
    }
}
