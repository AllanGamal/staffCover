package hellofx;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import hellofx.deparment.Department;
import hellofx.deparment.Fitter;
import hellofx.deparment.Team;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Controller implements Initializable {

    Department department;

    @FXML
    private Button addPersonal;

    @FXML
    private Button addPersonal1;

    @FXML
    private TableView<Team> tableView;

    @FXML
    private TableColumn<?, ?> availability;

    @FXML
    private Button changePersonal;

    @FXML
    private Button changePersonal1;

    @FXML
    private TableColumn<?, ?> competency;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private Button removePersonal;

    @FXML
    private Button removePersonal1;

    @FXML
    private TableColumn<Team, Integer> teamFitters;

    @FXML
    private TableColumn<Team, String> teamName;

    @FXML
    private TableColumn<Team, Integer> teamStation;

    @FXML
    private Label testy;

    @FXML
    private Label testy1;

    

    
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        // TODO Auto-generated method stub
        //teamName.setCellValueFactory(new PropertyValueFactory<Team, String>("teamName"));
        department = new Department("Johan");
        
        tableView.getSelectionModel().setCellSelectionEnabled(true);

    
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
            
            // add to table
            // to string the text

            Fitter fitter1 = new Fitter(department, "Daniel");
            Fitter fitter2 = new Fitter(department, "Johan");
            Fitter fitter3 = new Fitter(department, "Kalle");
            Fitter fitter4 = new Fitter(department, "Sven");
            Fitter fitter5 = new Fitter(department, "Kalle");

            
            
            Team team = new Team(dialog.getEditor().getText());
            ObservableList<Team> teams = tableView.getItems();
            teams.add(team);
            tableView.setItems(teams);
            department.addTeam(team);
           
            TableColumn teamy = new TableColumn(dialog.getEditor().getText());
            TableColumn teamStation = new TableColumn("Stationer");
            TableColumn teamFitters = new TableColumn("Personal");
            
            teamy.getColumns().addAll(teamStation, teamFitters);
            tableView.getColumns().add(teamy);
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

    }

    

}
