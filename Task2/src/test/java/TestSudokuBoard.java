import org.example.SudokuBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TestSudokuBoard {
    private SudokuBoard sudokuBoard;

    @BeforeEach
    void setUp() {
        sudokuBoard = new SudokuBoard();
    }

    @Test
    void fillBoard_ShouldGenerateValidSudoku() {
        sudokuBoard.fillBoard();
        int[][] gameBoard = sudokuBoard.getBoard();
        assertTrue(isValidSudoku(gameBoard));
    }

    @Test
    void fillBoard_ShouldGenerateUniqueLayoutsOnSubsequentCalls() {
        sudokuBoard.fillBoard();
        int[][] firstGameBoard = sudokuBoard.getBoard();

        setUp();
        sudokuBoard.fillBoard();
        int[][] secondGameBoard = sudokuBoard.getBoard();

        assertFalse(Arrays.deepEquals(firstGameBoard, secondGameBoard));
    }


    private boolean isValidSudoku(int[][] board) {
        for (int i = 0; i < 9; i++) {
            boolean[] rowCheck = new boolean[9];
            boolean[] colCheck = new boolean[9];
            boolean[] boxCheck = new boolean[9];

            for (int j = 0; j < 9; j++) {
                // Check row
                if (board[i][j] != 0) {
                    if (rowCheck[board[i][j] - 1]) {
                        return false;
                    }
                    rowCheck[board[i][j] - 1] = true;
                }

                // Check column
                if (board[j][i] != 0) {
                    if (colCheck[board[j][i] - 1]) {
                        return false;
                    }
                    colCheck[board[j][i] - 1] = true;
                }

                // Check 3x3 subgrid
                int rowIndex = 3 * (i / 3);
                int colIndex = 3 * (i % 3);
                int boxRow = rowIndex + j / 3;
                int boxCol = colIndex + j % 3;
                if (board[boxRow][boxCol] != 0) {
                    if (boxCheck[board[boxRow][boxCol] - 1]) {
                        return false;
                    }
                    boxCheck[board[boxRow][boxCol] - 1] = true;
                }
            }
        }
        return true;
    }
}
