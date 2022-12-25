package hellofx;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import hellofx.deparment.Department;
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
    

    
    // launch the application
    launch(args);
    // make a fitter object
    
    
    

    
}
public static void saveData(ArrayList<Department> data) {
    try {
        FileOutputStream fos = new FileOutputStream("savedata.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.close();
        fos.close();
        } catch (Exception e) {
        e.printStackTrace();
        }
}

public static ArrayList<Department> loadData() {
    ArrayList<Department> departmentList = new ArrayList<Department>();
    try {
        FileInputStream fis = new FileInputStream("savedata.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        departmentList = (ArrayList<Department>) ois.readObject();
        ois.close();
        fis.close();
        } catch (IOException ioe) {
        ioe.printStackTrace();
        return departmentList;
        } catch (ClassNotFoundException c) {
        System.out.println("Class not found");
        c.printStackTrace();
        return departmentList;
        }
    return departmentList;


    }
}