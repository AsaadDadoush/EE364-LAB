package LAB6;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Gpane extends GridPane implements EventHandler<MouseEvent> {
   
	 private enum Try {
	        USER,
	        COMPUTER
	    }
	
	private Button btExit;
    private Button btPlayAgain;
    private Button[][] btGameField;
    private boolean finish;

    public Gpane(){
        setPadding(new Insets(5,5,5,5));
        setAlignment(Pos.CENTER);
        makeButtons();
        setStyle("-fx-base: BLACK");
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Button){
            Button btClicked = (Button) mouseEvent.getSource();
            if (btClicked.getText().equalsIgnoreCase("-")){
                btClicked.setText("X");
                play();
                try {
                    checkIfWin();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void reSet() {
        for (Button[] bts : btGameField) {
            for (Button button : bts) {
                button.setText("-");
                setStyle("-fx-base: BLACK");
            }
        }
        finish = false;
    }

    private void play() {
        
        boolean Start = false;
        while (!Start && Empty()){
            int values1 = getRandom(0,3);
            int values2 = getRandom(0,3);
            Button btn = btGameField[values2][values1];
            if (btn.getText().equalsIgnoreCase("-")) {
                btn.setText("O");
                Start = true;
            }
        }
    }

    private void checkIfWin() {
        for (int i = 0; i < 3; i++){
            int x = 0;
            int o = 0;
            for (int j = 0; j < 3; j++) {
                if (btGameField[i][j].getText().equalsIgnoreCase("X")) x++;
                else if (btGameField[i][j].getText().equalsIgnoreCase("O")) o++;
            }
            if (x == 3 && !finish) {
                displayWin(Try.USER);
            } else if (o == 3 && !finish) {
                displayWin(Try.COMPUTER);
            }
        }
        for (int i = 0; i < 3; i++){
            int x = 0;
            int o = 0;
            for (int j = 0; j < 3; j++) {
                if (btGameField[j][i].getText().equalsIgnoreCase("X"))  x++;
                
                else if (btGameField[j][i].getText().equalsIgnoreCase("O")) o++;
            }
            if (x == 3 && !finish) {
                displayWin(Try.USER);
            }
            else if (o == 3 && !finish) {
                displayWin(Try.COMPUTER);
            }
        }

        for (int i = 0; i < 3; i++) {
            int x = 0;
            int o = 0;
            if (btGameField[i][i].getText().equalsIgnoreCase("X")) x++;
            else if (btGameField[i][i].getText().equalsIgnoreCase("O")) o++;
            if (x == 3 && !finish) {
                displayWin(Try.USER);
            }
            else if (o == 3 && !finish){
                displayWin(Try.COMPUTER);
            }
        }

        for (int i = 0; i < 3; i++) {
            int x = 0;
            int o = 0;
            if (btGameField[2-i][i].getText().equalsIgnoreCase("X")) x++;
            else if (btGameField[2-i][i].getText().equalsIgnoreCase("O")) o++;
            if (x == 3 && !finish){
                displayWin(Try.USER);
            }
            else if (o == 3 && !finish){
                displayWin(Try.COMPUTER);
            }
        }
    }

    private void displayWin(Try player) {
        finish = true;
        notifyWinner w = new notifyWinner(player);
        w.start(new Stage());
    }

    private void makeButtons() {
        btExit = new Button("Exit");
        btExit.setOnMouseClicked(e -> System.exit(0));
        btExit.setPrefSize(100,100);

        btPlayAgain = new Button("Play\nAgain");
        btPlayAgain.setOnMouseClicked(e -> reSet());
        btPlayAgain.setPrefSize(100,100);

        btGameField = new Button[3][3];
        for (int i = 0; i < btGameField.length; i++) {
            for (int j = 0; j < btGameField[i].length; j++) {
                btGameField[i][j] = new Button("-");
                btGameField[i][j].setOnMouseClicked(this);
                btGameField[i][j].setPrefSize(100,100);
                add(btGameField[i][j], i, j);
            }
        }
        add(btPlayAgain, 0,btGameField.length+1 );
        add(btExit, 1,btGameField.length+1);
    }

    private int getRandom(double min, double max) {
        return (int)(Math.random() * (max - min) + min);
    }

    private boolean Empty() {
        for (Button[] bt : btGameField)
            for (Button btn : bt)
                if (btn.getText().equalsIgnoreCase("-"))
                    return true;
        return false;
    }

   

    private static class notifyWinner extends Application {

        private final Try winner;

        public notifyWinner(Try player){
            this.winner = player;
        }

        @Override
        public void start(Stage primaryStage) {
            VBox root = new VBox();
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(5,5,5,5));
            root.setSpacing(5);
            Button bt = new Button("Close");
            bt.setOnMouseClicked(e -> {
                primaryStage.close();
                
               
            });

            root.getChildren().add(new Text(this.winner.name() + " WON!"));
            root.getChildren().add(bt);
            primaryStage.setScene(new Scene(root,250,150));
            primaryStage.show();
            root.setStyle("-fx-base: MIDNIGHTBLUE");
        }
    }
}