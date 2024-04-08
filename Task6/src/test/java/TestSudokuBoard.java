import org.example.*;
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
        SudokuBoard firstGameBoard = new SudokuBoard(sudokuSolver);
        firstGameBoard.copyFrom(sudokuBoard); // Assuming there's a method to copy board state

        setUp(); // Reset board and solver
        assertTrue(sudokuBoard.solveGame());
        SudokuBoard secondGameBoard = new SudokuBoard(sudokuSolver);
        secondGameBoard.copyFrom(sudokuBoard);

        assertFalse(firstGameBoard.equals(secondGameBoard), "Subsequent game layouts should be unique.");
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

    @Test
    void checkBoardStateWithToString() {
        sudokuSolver.solve(sudokuBoard);
        // Use toString() for debugging purposes, to check the state of the board
        System.out.println(sudokuBoard.toString());
        assertTrue(sudokuBoard.checkBoard(), "A solved board should be valid.");
    }

    @Test
    void equals_Reflexivity() {
        assertTrue(sudokuBoard.equals(sudokuBoard), "A board should be equal to itself for reflexivity.");
    }

    @Test
    void equals_WithNull() {
        assertFalse(sudokuBoard.equals(null), "Comparing a board with null should return false.");
    }

    @Test
    void equals_WithDifferentClassObject() {
        String notASudokuBoard = "This is not a SudokuBoard";
        assertFalse(sudokuBoard.equals(notASudokuBoard), "A SudokuBoard should not be equal to an object of a different class.");
    }

    @Test
    void equals_EqualAndNonEqualBoards() {
        sudokuSolver.solve(sudokuBoard);
        SudokuBoard anotherBoardWithSameState = new SudokuBoard(sudokuSolver);
        anotherBoardWithSameState.copyFrom(sudokuBoard);

        SudokuBoard differentBoard = new SudokuBoard(sudokuSolver);
        // Assuming a method to alter the board state differently from solve, for a guaranteed different state
        differentBoard.set(0, 0, 9); // Set a value explicitly, assuming this makes the board different

        assertTrue(sudokuBoard.equals(anotherBoardWithSameState), "Two boards with the same state should be equal.");
        assertFalse(sudokuBoard.equals(differentBoard), "Two boards with different states should not be equal.");
    }
}
