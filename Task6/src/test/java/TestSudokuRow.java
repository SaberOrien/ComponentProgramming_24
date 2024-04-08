import org.example.BacktrackingSudokuSolver;
import org.example.SudokuBoard;
import org.example.SudokuRow;
import org.example.SudokuSolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSudokuRow {
    @Test
    void getRow_ShouldReflectBoardState() {
        SudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);

        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(1, 0, 2);

        SudokuRow row = sudokuBoard.getRow(0);

        assertEquals(1, row.getFields().get(0).getFieldValue());
        assertEquals(2, row.getFields().get(1).getFieldValue());
    }
}
