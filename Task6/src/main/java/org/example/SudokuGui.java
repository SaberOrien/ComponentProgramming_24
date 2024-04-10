package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuGui extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/SudokuGui.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);

        stage.setTitle("Sudoku Game");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
