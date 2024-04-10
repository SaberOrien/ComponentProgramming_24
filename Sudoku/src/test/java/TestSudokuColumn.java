import org.example.sudoku.BacktrackingSudokuSolver;
import org.example.sudoku.SudokuBoard;
import org.example.sudoku.SudokuColumn;
import org.example.sudoku.SudokuSolver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestSudokuColumn {
    @Test
    void getColumn_ShouldReflectBoardState() {
        SudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);

        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(0, 1, 2);

        SudokuColumn column = sudokuBoard.getColumn(0);

        assertEquals(1, column.getFields().get(0).getFieldValue());
        assertEquals(2, column.getFields().get(1).getFieldValue());
    }
}
