package hellofx;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import hellofx.deparment.Department;
import hellofx.deparment.Team;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

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
        // allow only one selection

       
        
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

            // if there is spacing in the team name, show an alert
            if (dialog.getEditor().getText().contains(" ")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Felaktigt namn");
                alert.setContentText("Team namn får inte innehålla mellanslag");
                alert.showAndWait();
                // stop the method
                return;
            }

            Team team = new Team(dialog.getEditor().getText());
            department.addTeam(team);

            // make a borderpane
            BorderPane bp = new BorderPane();

            // make a title for the borderpane
            Label title = new Label(dialog.getEditor().getText());
            title.setMinWidth(300);
            bp.setTop(title);
            bp.setAlignment(title, Pos.CENTER);

            // add id to the title
            title.setId(dialog.getEditor().getText());
            // add class to title
            title.getStyleClass().add("title");

            // add a listview left and right of the borderpane
            ListView<String> lvL = new ListView<String>();
            ListView<String> lvR = new ListView<String>();
            lvL.setId(dialog.getEditor().getText() + "S");
            lvR.setId(dialog.getEditor().getText() + "P");
            lvL.getStyleClass().add("lVS");
            lvR.getStyleClass().add("lVP");
            bp.setLeft(lvL);
            bp.setRight(lvR);
            bp.setPrefHeight(580);
            // add the borderpane to the tilepane
            // make every box of the tilepane at least 300px wide
            tPane.getChildren().add(bp);

            //make the list unselectable
            lvL.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lvR.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            // allow for deselection of item when clicking on it again
            lvL.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (oldSelection != null && newSelection != null && oldSelection.equals(newSelection)) {
                    lvL.getSelectionModel().clearSelection();
                }
            });





        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @FXML
    void addStationClick(ActionEvent event) {

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

        // if the user clicks cancel, return
        if (dialog2.getResult() == null) {
            return;
        }

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

        // search through the tilepane for the team
        for (int i = 0; i < tPane.getChildren().size(); i++) {
            // if the tPane includes a borderpane
            if (tPane.getChildren().get(i) instanceof BorderPane) {
                // if a title is included the borderpane
                if (((BorderPane) tPane.getChildren().get(i)).getLeft() instanceof ListView) {

                    // if the id of the listview is the same as the team name + S
                    if (((ListView) ((BorderPane) tPane.getChildren().get(i)).getLeft()).getId()
                            .equals(dialog.getResult() + "S")) {
                        // add the station to the listview
                        ((ListView) ((BorderPane) tPane.getChildren().get(i)).getLeft()).getItems()
                                .add(dialog2.getEditor().getText());
                    }
                    // add the station to the listview
                    
                }

            }
        }

      
    }

    @FXML
    void removeClick(ActionEvent event) {
        // if listview item is selected, remove it
        for (int i = 0; i < tPane.getChildren().size(); i++) {
            if (tPane.getChildren().get(i) instanceof BorderPane) {
                if (((BorderPane) tPane.getChildren().get(i)).getLeft() instanceof ListView) {
                    if (((ListView) ((BorderPane) tPane.getChildren().get(i)).getLeft()).getSelectionModel()
                            .getSelectedItem() != null) {
                        ((ListView) ((BorderPane) tPane.getChildren().get(i)).getLeft()).getItems().remove(
                                ((ListView) ((BorderPane) tPane.getChildren().get(i)).getLeft()).getSelectionModel()
                                        .getSelectedIndex());
                                        // remove from the team
                        for (int j = 0; j < department.getTeamList().length; j++) {
                            // string s = the selected item
                            String s = (String) ((ListView) ((BorderPane) tPane.getChildren().get(i)).getLeft())
                                    .getSelectionModel().getSelectedItem();
                            // if the team name is the same as the id of the listview
                            if (department.getTeamList()[j].getTeamName()
                                    .equals(((ListView) ((BorderPane) tPane.getChildren().get(i)).getLeft()).getId()
                                            .substring(0, ((ListView) ((BorderPane) tPane.getChildren().get(i)).getLeft())
                                                    .getId().length() - 1))) {
                                // remove the station from the team
                                department.getTeamList()[j].removeStation(s);
                            }
                        }
                    }
                }
            }
        }
    }

        /*
        // print all the team and the station under it
        for (int i = 0; i < department.getTeamList().length; i++) {
            System.out.println(department.getTeamList()[i].getTeamName());
            for (int j = 0; j < department.getTeamList()[i].getStationList().length; j++) {
                System.out.println(department.getTeamList()[i].getStationList()[j]);
            }
        }
         */
    }
