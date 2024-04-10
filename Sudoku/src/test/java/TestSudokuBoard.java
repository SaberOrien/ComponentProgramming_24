import org.example.sudoku.BacktrackingSudokuSolver;
import org.example.sudoku.SudokuBoard;
import org.example.sudoku.SudokuSolver;
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
