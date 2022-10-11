package hellofx;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import hellofx.deparment.Department;
import hellofx.deparment.Fitter;
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
        teamName.setCellValueFactory(new PropertyValueFactory<Team, String>("teamName"));
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

            int t = 0;
            // print the number of fitters in the department
            for (int i = 0; i < department.getTeamList().length; i++) {
                department.getTeamList()[i].getFitterList();
                t += department.getTeamList()[i].getFitterList().length;
            }
           
            System.out.println(t);

            team.removeFitter(fitter5);


            // print the number of fitters in the department
            for (int i = 0; i < department.getTeamList().length; i++) {
                department.getTeamList()[i].getFitterList();
                t += department.getTeamList()[i].getFitterList().length;
            }
           
            System.out.println(t);

            /*
            // add a column to the table
            TableColumn<Team, Integer> teamFitters = new TableColumn<Team, Integer>("Fitters");
            tableView.getColumns().add(teamFitters);

            // add a sub column to the table
            TableColumn<Team, Integer> teamStation = new TableColumn<Team, Integer>("Station");
            teamFitters.getColumns().add(teamStation);
 */
            // add a column to the table
            TableColumn<Team, Integer> teamy = new TableColumn<Team, Integer>(dialog.getEditor().getText());
            tableView.getColumns().add(teamy);

            // add a sub column to the table
            TableColumn<Team, String> teamStation = new TableColumn<Team, String>("Stationer");
            teamy.getColumns().add(teamStation);

            // add a sub column to the table
            TableColumn<Team, String> teamFitters = new TableColumn<Team, String>("Personal");
            teamy.getColumns().add(teamFitters);

            
        } catch(Exception e) {
            e.printStackTrace();
            
            
        } 

    }

    

}
