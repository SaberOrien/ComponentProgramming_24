import org.example.sudoku.BacktrackingSudokuSolver;
import org.example.sudoku.SudokuBoard;
import org.example.sudoku.SudokuBox;
import org.example.sudoku.SudokuSolver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestSudokuBox {
    @Test
    void getBox_ShouldReflectBoardState() {
        SudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);

        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(1, 1, 2);

        SudokuBox box = sudokuBoard.getBox(0, 0);

        assertEquals(1, box.getFields().get(0).getFieldValue());
        assertEquals(2, box.getFields().get(4).getFieldValue());
    }
}
