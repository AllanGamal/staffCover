package hellofx;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import hellofx.deparment.Department;
import hellofx.deparment.Fitter;
import hellofx.deparment.Team;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;


public class Controller implements Initializable {

    Department department;

    @FXML
    private HBox tPane;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private ScrollPane scrollPlane;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        // TODO Auto-generated method stub
        department = new Department("Johan");
    }
  
    @FXML
    void addTeamClick(ActionEvent event) {
        
        try {
         TextInputDialog dialog = new TextInputDialog("Team name");
            dialog.setTitle("Add team");
            dialog.setHeaderText("Add team");
            dialog.setContentText("Please enter team name:");
            dialog.showAndWait();

            // check if the team already exists
            for (int i = 0; i < department.getTeamList().length; i++) {
                if (department.getTeamList()[i].getTeamName().equals(dialog.getEditor().getText())) {
                    // if the team already exists, show an alert
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Team existerar redan");
                    alert.setContentText("Team med samma namn existerar redan, vänligen välj ett annat namn");
                    alert.showAndWait();
                    return;
                }
            }
    

            Fitter fitter1 = new Fitter(department, "Daniel");
            Fitter fitter2 = new Fitter(department, "Johan");
            Fitter fitter3 = new Fitter(department, "Kalle");
            Fitter fitter4 = new Fitter(department, "Sven");
            Fitter fitter5 = new Fitter(department, "Kalle");

            
            
            Team team = new Team(dialog.getEditor().getText());
            
            department.addTeam(team);
           
            // resize the columns so that the content is not clipped

            team.addFitter(fitter5);
            team.addFitter(fitter4);
            team.addFitter(fitter3);
            team.addFitter(fitter2);
            team.addFitter(fitter1);

            // print all fitters in the team
            for (int i = 0; i < team.getFitterList().length; i++) {
                System.out.println(team.getFitterList()[i].getName());
            }

            // print all team name
            for (int i = 0; i < department.getTeamList().length; i++) {
                System.out.println(department.getTeamList()[i].getTeamName());
            }

            // print the number of fitters in the department
            int t = 0;
            for (int i = 0; i < department.getTeamList().length; i++) {
                t += department.getTeamList()[i].getFitterList().length;
            }
            System.out.println("Totalt antal personal: " + t);
            
        } catch(Exception e) {
            e.printStackTrace();
            
            
        } 

    }

    @FXML
    void addStationClick(ActionEvent event) {
        /*
        // make an arraylist from the team department.getTeamList()
        ArrayList<String> teamList = new ArrayList<String>();
        for (int i = 0; i < department.getTeamList().length; i++) {
            teamList.add(department.getTeamList()[i].getTeamName());
        }

       
        // choice dialog box to choose from the teamList
        ChoiceDialog<String> dialog = new ChoiceDialog<>(teamList.get(0), teamList);
        dialog.setTitle("Välj team");
        dialog.setHeaderText("Välj team för station"); 
        dialog.setContentText("Välj team:");
        dialog.showAndWait();

        // if the user clicks cancel, return
        if (dialog.getResult() == null) {
            return;
        }

        // dialog box to enter the station name
        TextInputDialog dialog2 = new TextInputDialog("Station name");
        dialog2.setTitle("Lägg till station");
        dialog2.setHeaderText("Lägg till station");
        dialog2.setContentText("Ange stationens namn:");
        dialog2.showAndWait();


        // check if the station already exists
        for (int i = 0; i < department.getTeamList().length; i++) {

            for (int j = 0; j < department.getTeamList()[i].getStationList().length; j++) {

                if (department.getTeamList()[i].getStationList()[j]
                        .equals(dialog2.getEditor().getText())) {
                    // if the station already exists, show an alert
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Station existerar redan");
                    alert.setContentText("Station med samma namn existerar redan, vänligen välj ett annat namn");
                    alert.showAndWait();
                    return;
                }
            }
            // add the station to the team
            if (department.getTeamList()[i].getTeamName().equals(dialog.getResult())) {
                department.getTeamList()[i].addStation(department, dialog2.getEditor().getText());
            }
        }

        */
        // add 3 toggle buttons to the tilepane
        ToggleButton tb1 = new ToggleButton("Station 1");
        ToggleButton tb2 = new ToggleButton("Station 2");
        ToggleButton tb3 = new ToggleButton("Station 3");

        // make them 300px wide
        tb1.setPrefWidth(300);
        tb2.setPrefWidth(300);
        tb3.setPrefWidth(300);
        
        // make a borderpane
        BorderPane bp = new BorderPane();
        //bp.setTop(tb1);
        bp.setCenter(tb2);
        bp.setLeft(tb3);

        // make a title for the borderpane
        Label title = new Label("Team x");
        // center the tb1 button in the borderpane
        BorderPane.setAlignment(title, Pos.CENTER);

        bp.setTop(title);

        // add the borderpane to the tilepane
        // make every box of the tilepane at least 300px wide
        tPane.setMinWidth(900);
        tPane.getChildren().add(bp);

        
        // loop through the tilepane and get the 
        
    }    

}
