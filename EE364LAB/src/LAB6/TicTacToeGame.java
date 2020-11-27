package LAB6;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicTacToeGame extends Application {
	
	Stage window;
	
    @Override
    public void start(Stage primaryStage) {
    	window = primaryStage;
        Gpane tictac = new Gpane();
        Scene scene = new Scene(tictac);
        window.setTitle("TicTacToe Game");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}