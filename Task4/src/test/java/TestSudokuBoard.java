import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestSudokuBoard {
    private SudokuBoard board;
    private SudokuSolver solver;

    @BeforeEach
    void setUp() {
        solver = new BacktrackingSudokuSolver();
        board = new SudokuBoard(solver);
    }

    @Test
    void testInitialization() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(0, board.get(i, j), "Board should be initialized with all zeros.");
            }
        }
    }

    @Test
    void testValidSetAndGet() {
        board.set(0, 0, 5);
        assertEquals(5, board.get(0, 0), "Setting a value should correctly update the board.");
    }

    @Test
    void testSolveEmptyBoard() {
        assertTrue(board.solveGame(), "Solver should successfully solve an empty board.");
    }


}
