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


    //NEW OVERRIDE TESTS
    @Test
    void toString_ShouldCorrectlyRepresentSudokuBoardState() {
        sudokuSolver.solve(sudokuBoard);
        String boardString = sudokuBoard.toString();
        assertNotNull(boardString, "toString() should not return null.");
        assertFalse(boardString.isEmpty(), "toString() should not return an empty string.");
    }

    @Test
    void equals_SelfEquality() {
        assertTrue(sudokuBoard.equals(sudokuBoard), "A board should always be equal to itself.");
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
    void equals_DeepInequality() {
        SudokuBoard board1 = new SudokuBoard(sudokuSolver);
        SudokuBoard board2 = new SudokuBoard(sudokuSolver);

        sudokuSolver.solve(board1);
        sudokuSolver.solve(board2);

        assertFalse(board1.equals(board2), "Two boards with different states should not be considered equal.");
    }

    @Test
    void hashCode_ShouldBeEqualForIdenticallySetUpBoards() {
        SudokuBoard board1 = new SudokuBoard(sudokuSolver);
        SudokuBoard board2 = new SudokuBoard(sudokuSolver);

        for (int i = 0; i < 9; i++) {
            board1.set(i, 0, i + 1);
            board2.set(i, 0, i + 1);
        }

        assertEquals(board1.hashCode(), board2.hashCode(), "Hash codes should be equal for identically set up boards.");
    }

    @Test
    void hashCode_ShouldChangeWhenBoardIsModified() {
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        int originalHashCode = board.hashCode();

        board.set(0, 0, 1);

        assertNotEquals(originalHashCode, board.hashCode(), "Hash code should change when the board's state changes.");
    }




}
