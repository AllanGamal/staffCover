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
public void start(Stage primaryStage) {

    Parent root;
    try {
        root = FXMLLoader.load(getClass().getResource("Test.fxml"));
        Scene scene = new Scene(root);
    primaryStage.setTitle("Hello World");
    primaryStage.setScene(scene);
    primaryStage.show();
    } catch (IOException e) {
        // TODO Auto-generated catch block
       
    }
    

}

public static void main(String[] args) {
    launch(args);
    /************ TESTING PURPOSES *************/
    Department department = new Department("Johan");
    Team team1 = new Team("Team1");
    Team team2 = new Team("Team2");
    Team team3 = new Team("Team3");

    
    department.addTeam(team1);
    department.addTeam(team2);
    
    Fitter fitter1 = new Fitter(department, "Daniel");
    Fitter fitter2 = new Fitter(department, "Johan");
    Fitter fitter3 = new Fitter(department, "Kalle");
    Fitter fitter4 = new Fitter(department, "Sven");
    Fitter fitter5 = new Fitter(department, "Kalle");

    fitter1.addCompetency("Station1");

    fitter2.addCompetency("Station1");
    fitter2.addCompetency("Station2");
    fitter2.addCompetency("Station3");

    fitter3.addCompetency("Station1");
    fitter3.addCompetency("Station2");
    fitter3.addCompetency("Station3");

    fitter4.addCompetency("Station2");
    fitter4.addCompetency("Station3");

    team1.addStation(department, "Station1");
    team1.addStation(department, "Station2");
    team1.addStation(department, "Station3");

    team1.addFitter(fitter1);
    team1.addFitter(fitter2);
    team1.addFitter(fitter3);
    team1.addFitter(fitter4);
    team1.addFitter(fitter5);

    
    /*
    LinkedList<String> []test = new LinkedList[2];
    for ( int i = 0; i < test.length; i++) {
        test[i] = new LinkedList<String>();
    }
    test[0].add("test");
    test[0].add("test2");
    
    test[1].add("test2");
    test[1].add("test3");
    test[1].add("test4");
    department.printCombinations(test);
     */      

    LinkedList<String> []test = new LinkedList[3];
    for ( int i = 0; i < test.length; i++) {
        test[i] = new LinkedList<String>();
    }

    test[0].add("Johan");
    test[0].add("Daniel");
    test[0].add("Kalle");
    

    test[1].add("Johan");
    test[1].add("Sven");
    test[1].add("Kalle");

    test[2].add("Johan");
    test[2].add("Sven");
    test[2].add("Kalle");
    

  
    ArrayList<String> testy = department.comboWombo(test);
    System.out.println(testy);
    // print length of the array
    System.out.println(department.comboWombo(test).size());


    
    System.out.println(department.getCombo());
    System.out.println(department.getCombo().size());
    
    

    
}
    

}