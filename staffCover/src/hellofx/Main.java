package hellofx;

import java.io.IOException;
import java.util.*;

import hellofx.deparment.Department;
import hellofx.deparment.Fitter;
import hellofx.deparment.Team;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application{

   
/************ TESTING PURPOSES *************/
@Override
public void start(Stage primaryStage) throws Exception  {

    Parent root;
   
    root = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("css/styling.css").toExternalForm());
   // min width and height
    primaryStage.setMinWidth(1280);
    primaryStage.setMinHeight(720);
    primaryStage.setTitle("Hello World");
    primaryStage.setScene(scene);
    primaryStage.show();
   
    
    

}

public static void main(String[] args) {
    


    launch(args);
    

    
}
    

}