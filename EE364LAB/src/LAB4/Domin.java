package LAB4;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;


public class Domin extends Application {

    Stage window;
    ComboBox<String> comboBox;
      public static void main(String[] args) {
            launch(args);
          }


    @Override
    public void start(Stage primaryStage) {
       

        //Pane
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(50, 45, 70, 60));
        pane.setHgap(4);
        pane.setVgap(4);

        //Button & node
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                "Saudi Arabia",
                "China",
                "USA"
        );
        Button button1 = new Button("Clear");
        Button button2= new Button("Register");

        pane.add(new Label("First Name:"), 0, 0);
        pane.add(new TextField(), 1, 0);
        pane.add(new Label("Last Name:"), 0, 1); 
        pane.add(new TextField(), 1, 1);
        pane.add(new Label("Enter password:"), 0, 2);
        pane.add(new TextField(), 1, 2);
        pane.add(new Label("Reenter password:"), 0, 3);
        pane.add(new TextField(), 1, 3);
        pane.add(new Label("Choose a country:"), 0, 4);
        pane.add(comboBox , 1 , 4);
        pane.add(new Label("City"), 0, 5);
        pane.add(new TextField(), 1, 5);
        pane.add(button1, 0, 6);
        pane.add(button2, 1, 6);
         GridPane.setHalignment(button1, HPos.RIGHT);
         GridPane.setHalignment(button2, HPos.LEFT);

        //Scene
        Scene scene = new Scene(pane);
        window = primaryStage;
        window.setTitle("Account inforamtion");
        window.setScene(scene);
        window.show();

    }


}