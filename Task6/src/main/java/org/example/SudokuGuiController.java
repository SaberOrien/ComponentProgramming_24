package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.example.sudoku.BacktrackingSudokuSolver;
import org.example.sudoku.SudokuBoard;
import org.example.sudoku.SudokuSolver;

public class SudokuGuiController {

    @FXML
    public GridPane sudokuGrid;

    private SudokuBoard sudokuBoard;
    private SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

    @FXML
    public void initialize() {
        sudokuBoard = new SudokuBoard(sudokuSolver);
        sudokuBoard.solveGame();
        populateGrid();
    }

    private void populateGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField cell = new TextField();
                cell.setPrefWidth(50);
                cell.setPrefHeight(50);
                cell.setText(String.valueOf(sudokuBoard.get(row, col)));
                sudokuGrid.add(cell, col, row + 1);
            }
        }
    }
}
