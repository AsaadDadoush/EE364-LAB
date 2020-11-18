package LAB5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;


public class Calculator extends Application {
	
	Button bt1 = new Button("1");
	Button bt2 = new Button("2");
	Button bt3 = new Button("3");
	Button bt4 = new Button("4");
	Button bt5 = new Button("5");
	Button bt6 = new Button("6");
	Button bt7 = new Button("7");
	Button bt8 = new Button("8");
	Button bt9 = new Button("9");
	Button bt0 = new Button("0");
	Button btC = new Button("C");
	Button btPlus = new Button("+");
	Button btMinus = new Button("-");
	Button btEqual = new Button("=");
	TextField Screen = new TextField();
	Stage window;
	Float data = 0f;
	int operation = -1;

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		
		//Pane
		VBox root = new VBox();
		root.setStyle("-fx-background-color: BLACK; "
			+ "-fx-padding: 20; -fx-font-size: 20;");
		root.getChildren().add(Screen);
		root.setPadding(new Insets(5));
		root.setAlignment(Pos.CENTER);
		GridPane Gpane = new GridPane();
		Gpane.setAlignment(Pos.CENTER);
		Gpane.setPadding(new Insets(5,5,5,5));
		Gpane.setVgap(5);
		Gpane.setHgap(5);
		Gpane.add(bt1,0,0);
		Gpane.add(bt2,1,0);
		Gpane.add(bt3,2,0);
		Gpane.add(bt4,0,1);
		Gpane.add(bt5,1,1);
		Gpane.add(bt6,2,1);
		Gpane.add(bt7,0,2);
		Gpane.add(bt8,1,2);
		Gpane.add(bt9,2,2);
		Gpane.add(bt0,2,3);
		Gpane.add(btPlus,0,3);
		Gpane.add(btMinus,1,3);
		Gpane.add(btEqual,1,4);	
		Gpane.add(btC,2,4);
		root.getChildren().add(Gpane);
		Screen.setMaxWidth(190);
		Screen.setEditable(false);
		//Buttons
		Gpane.setStyle("-fx-base: DARKGREY");
		bt1.setPrefSize(60,50);
		bt2.setPrefSize(60,50);
		bt3.setPrefSize(60,50);
		bt4.setPrefSize(60,50);
		bt5.setPrefSize(60,50);
		bt6.setPrefSize(60,50);
		bt7.setPrefSize(60,50);
		bt8.setPrefSize(60,50);
		bt9.setPrefSize(60,50);
		bt0.setPrefSize(60,50);
		btC.setPrefSize(60,50);
		btEqual.setPrefSize(60,50);
		btPlus.setPrefSize(60,50);
		btMinus.setPrefSize(60,50);
		//Event
		bt1.setOnAction(e -> handleButtonAction(e));
		bt2.setOnAction(e -> handleButtonAction(e));
		bt3.setOnAction(e -> handleButtonAction(e));
		bt4.setOnAction(e -> handleButtonAction(e));
		bt5.setOnAction(e -> handleButtonAction(e));
		bt6.setOnAction(e -> handleButtonAction(e));
		bt7.setOnAction(e -> handleButtonAction(e));
		bt8.setOnAction(e -> handleButtonAction(e));
		bt9.setOnAction(e -> handleButtonAction(e));
		bt0.setOnAction(e -> handleButtonAction(e));
		btC.setOnAction(e -> handleButtonAction(e));
		btEqual.setOnAction(e -> handleButtonAction(e));
		btMinus.setOnAction(e -> handleButtonAction(e));
		btPlus.setOnAction(e -> handleButtonAction(e));
		//Scene
		Scene scene = new Scene(root,300,300);
		window.initStyle(StageStyle.DECORATED);
		window.setScene(scene);
		window.setTitle("Calculator");
		window.show();
	}
	private void handleButtonAction(ActionEvent event) {
		if(event.getSource() == bt1) {
			Screen.setText(Screen.getText() + "1");
		}else if (event.getSource() == bt2) {
			Screen.setText(Screen.getText() + "2");
		}else if (event.getSource() == bt3) {
			Screen.setText(Screen.getText() + "3");
		}else if (event.getSource() == bt4) {
			Screen.setText(Screen.getText() + "4");
		}else if (event.getSource() == bt5) {
			Screen.setText(Screen.getText() + "5");
		}else if (event.getSource() == bt6) {
			Screen.setText(Screen.getText() + "6");
		}else if (event.getSource() == bt7) {
			Screen.setText(Screen.getText() + "7");
		}else if (event.getSource() == bt8) {
			Screen.setText(Screen.getText() + "8");
		}else if (event.getSource() == bt9) {
			Screen.setText(Screen.getText() + "9");
		}else if (event.getSource() == bt0) {
			Screen.setText(Screen.getText() + "0");
		}else if (event.getSource() == btC) {
			Screen.setText("");
		}else if (event.getSource() == btMinus) {
			data = Float.parseFloat(Screen.getText());
			operation = 2; //Substraction
			Screen.setText("");
		}else if (event.getSource() == btPlus) {
			data = Float.parseFloat(Screen.getText());
			operation = 1; //Addition
			Screen.setText("");
		}else if (event.getSource() == btEqual) {
			Float secoundOperation = Float.parseFloat(Screen.getText());
			switch (operation) {
			case 1: //Addition
				Float ans = data + secoundOperation;
				Screen.setText(String.valueOf(ans));break;
			case 2: //Substraction
				 ans = data - secoundOperation;
				Screen.setText(String.valueOf(ans));break;
			}
		}
	}
	public static void main (String[] args) {
		launch(args);
	}
}