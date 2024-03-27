import org.example.SudokuBoard;
import org.example.SudokuSolver;
import org.example.BacktrackingSudokuSolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void solveGame_ShouldGenerateValidSudoku() {
        assertTrue(sudokuBoard.solveGame());
        assertTrue(isValidSudoku(sudokuBoard));
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


    private int[][] deepCopyBoard(SudokuBoard sudokuBoard) {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copy[i][j] = sudokuBoard.get(i, j);
            }
        }
        return copy;
    }

    private boolean isValidSudoku(SudokuBoard sudokuBoard) {
        for (int i = 0; i < 9; i++) {
            boolean[] rowCheck = new boolean[9];
            boolean[] colCheck = new boolean[9];
            boolean[] boxCheck = new boolean[9];

            for (int j = 0; j < 9; j++) {
                // Check row
                int rowVal = sudokuBoard.get(i, j);
                if (rowVal != 0) {
                    if (rowCheck[rowVal - 1]) {
                        return false;
                    }
                    rowCheck[rowVal - 1] = true;
                }

                // Check column
                int colVal = sudokuBoard.get(j, i);
                if (colVal != 0) {
                    if (colCheck[colVal - 1]) {
                        return false;
                    }
                    colCheck[colVal - 1] = true;
                }

                // Check 3x3 subgrid
                int rowIndex = 3 * (i / 3);
                int colIndex = 3 * (i % 3);
                int boxRow = rowIndex + j / 3;
                int boxCol = colIndex + j % 3;
                int boxVal = sudokuBoard.get(boxRow, boxCol);
                if (boxVal != 0) {
                    if (boxCheck[boxVal - 1]) {
                        return false;
                    }
                    boxCheck[boxVal - 1] = true;
                }
            }
        }
        return true;
    }
}
