package hellofx;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import hellofx.deparment.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

// implements Initializable and add initialize method
public class Controller implements Initializable {

    public static Department department;

    @FXML
    private HBox tPane;

    @FXML
    private TableView<Fitter> fitterTable;

    @FXML
    private TableColumn<Fitter, Boolean> availabilityCol;

    @FXML
    private TableColumn<Fitter, String> idCol;

    @FXML
    private TableColumn<Fitter, String> nameCol;

    @FXML
    private ScrollPane scrollPlane;

    ObservableList<Fitter> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO Auto-generated method stub
        department = new Department("Johan");
        fitterTable.setItems(list);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));

        // if row in table is double clicked
        fitterTable.setOnMouseClicked(e -> {
            // get the name of the selected row
            String nameID = fitterTable.getSelectionModel().getSelectedItem().getName();
            if (e.getClickCount() == 2) {
                try {
                    Scene newPopup = newPopup("fxml/FitterCompetencyList.fxml", nameID);
                    addRestOfStations(newPopup, nameID);
                    addCompetencyStations(newPopup, nameID);

                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    System.out.println("Funkar ej");
                }
            }
        });
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
            // make bp heght have a dynamic height
            bp.setPrefHeight(580);

            // add the borderpane to the tilepane
            // make every box of the tilepane at least 300px wide
            tPane.getChildren().add(bp);
            // padding to the right 20px
            tPane.setSpacing(20);

            // add 4 buttons and place them next to each other
            Label button = new Label("+");
            button.setId(dialog.getEditor().getText() + "-addstation");
            button.getStyleClass().add("button2");
            button.getStyleClass().add("button3");
            bp.setBottom(button);
            // add an action to the button that prints the id of the button

            Label button2 = new Label("-");
            button2.setId(dialog.getEditor().getText() + "-removestation");
            button2.getStyleClass().add("button2");
            button2.getStyleClass().add("button3");
            bp.setBottom(button2);

            Label button3 = new Label("+");
            button3.setId(dialog.getEditor().getText() + "-addfitter");
            button3.getStyleClass().add("button2");
            button3.getStyleClass().add("button3");
            bp.setBottom(button3);

            Label button4 = new Label("-");
            button4.setId(dialog.getEditor().getText() + "-removefitter");
            button4.getStyleClass().add("button2");
            button4.getStyleClass().add("button3");
            bp.setBottom(button4);

            // add a label next to the two buttons
            Label label = new Label("Station");
            label.getStyleClass().add("titlelabel");
            bp.setBottom(label);

            // add a label next to the two buttons
            Label label2 = new Label("Personal");
            label2.getStyleClass().add("titlelabel");
            bp.setBottom(label2);

            // make an hbox to place the buttons in with the class "btnbox"
            HBox btnbox1 = new HBox();
            btnbox1.getStyleClass().add("btnboxleft");
            btnbox1.getChildren().addAll(label, button, button2);
            bp.setBottom(btnbox1);
            // set with of the hbox to 300px
            btnbox1.setPrefWidth(240);

            HBox btnbox2 = new HBox();
            btnbox2.getStyleClass().add("btnboxleft");
            btnbox2.getChildren().addAll(label2, button3, button4);
            bp.setBottom(btnbox2);
            btnbox1.setPrefWidth(240);

            HBox btnbox = new HBox();
            btnbox.getStyleClass().add("btnboxleft");
            btnbox.getChildren().addAll(btnbox1, btnbox2);
            bp.setBottom(btnbox);

            // btn action to add a station
            button.setOnMouseClicked(e -> {
                TextInputDialog dialog2 = new TextInputDialog("Station name");
                dialog2.setTitle("Add station");
                dialog2.setHeaderText("Add station");
                dialog2.setContentText("Please enter station name:");
                dialog2.showAndWait();

                // check if the station already exists
                for (int i = 0; i < department.getTeamList().length; i++) {
                    if (department.getTeamList()[i].getTeamName().equals(dialog.getEditor().getText())) {
                        for (int j = 0; j < department.getTeamList()[i].getStationList().length; j++) {
                            if (department.getTeamList()[i].getStationList()[j]
                                    .equals(dialog2.getEditor().getText())) {
                                // if the station already exists, show an alert
                                Alert alert = new Alert(AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("Station existerar redan");
                                alert.setContentText(
                                        "Station med samma namn existerar redan, vänligen välj ett annat namn");
                                alert.showAndWait();
                                return;
                            }
                        }
                    }
                }

                // if there is spacing in the station name, show an alert
                if (dialog2.getEditor().getText().contains(" ")) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Felaktigt namn");
                    alert.setContentText("Station namn får inte innehålla mellanslag");
                    alert.showAndWait();
                    // stop the method
                    return;
                }

                String id = button.getId();
                // remove the "-addstation" from the id
                id = id.substring(0, id.length() - 11);

                String station = dialog2.getEditor().getText();
                // add the station to the team with the same id as the button
                // check if the station already exists
                for (int i = 0; i < department.getTeamList().length; i++) {

                    for (int j = 0; j < department.getTeamList()[i].getStationList().length; j++) {

                        if (department.getTeamList()[i].getStationList()[j]
                                .equals(station)) {
                            // if the station already exists, show an alert
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Station existerar redan");
                            alert.setContentText(
                                    "Station med samma namn existerar redan, vänligen välj ett annat namn");
                            alert.showAndWait();
                            return;
                        }
                    }
                    // add the station to the team
                    if (department.getTeamList()[i].getTeamName().equals(id)) {
                        department.getTeamList()[i].addStation(department, station);
                    }
                }

                // add the station to the listview
                lvL.getItems().add(dialog2.getEditor().getText());

            });

            // btn action to remove station
            button2.setOnMouseClicked(e -> {
                // get the id of the button
                String id = button2.getId();
                // remove the "-removeStation" from the id
                id = id.substring(0, id.length() - 14);

                // remove the selected station from the listview on the left
                // get the listview on the left
                ListView<String> lv = (ListView<String>) bp.getLeft();
                // get the selected item
                String selectedItem = lv.getSelectionModel().getSelectedItem();
                // remove the selected item
                lv.getItems().remove(selectedItem);
                // if the station is in the team, remove it from the team
                for (int j = 0; j < department.getTeamList().length; j++) {
                    if (department.getTeamList()[j].getTeamName().equals(id)) {
                        for (int i = 0; i < department.getTeamList()[j].getStationList().length; i++) {
                            if (department.getTeamList()[j].getStationList()[i].equals(selectedItem)) {
                                department.getTeamList()[j]
                                        .removeStation(department.getTeamList()[j].getStationList()[i]);
                            }
                        }
                    }
                }
            });

            // btn action to add a fitter
            button3.setOnMouseClicked(e -> {
                TextInputDialog dialog2 = new TextInputDialog("Montörens namn");
                dialog2.setTitle("Lägg till montör");
                dialog2.setHeaderText("Add montör");
                dialog2.setContentText("Vänligen ange montörens namn:");
                dialog2.showAndWait();

                String id = button3.getId();
                // remove the "-addfitter" from the id
                id = id.substring(0, id.length() - 10);

                // check if the fitter already exists in any team
                for (int i = 0; i < department.getTeamList().length; i++) {
                    for (int j = 0; j < department.getTeamList()[i].getFitterList().length; j++) {
                        if (department.getTeamList()[i].getFitterList()[j].getName()
                                .equals(dialog2.getEditor().getText())) {
                            // if the fitter already exists, show an alert
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Personen existerar redan");
                            alert.setContentText(
                                    "Personen med samma namn existerar redan, vänligen välj ett annat namn");
                            alert.showAndWait();
                            return;
                        }
                    }
                }

                String fitterName = dialog2.getEditor().getText();
                Fitter fitter = new Fitter(this.department, fitterName);

                // add the fitter to the team with the same id as the button
                for (int i = 0; i < department.getTeamList().length; i++) {
                    if (department.getTeamList()[i].getTeamName().equals(id)) {
                        department.getTeamList()[i].addFitter(fitter);
                    }
                }


                lvR.getItems().add(fitterName);
             
        

                addNameToTable();

            });

            // btn action to remove a fitter
            button4.setOnMouseClicked(e -> {
                // get the id of the button
                String id = button4.getId();
                // remove the "-removefitter" from the id
                id = id.substring(0, id.length() - 13);

                // remove the selected fitter from the listview on the right
                // get the listview on the right
                ListView<String> lv = (ListView<String>) bp.getRight();
                // get the selected item
                String selectedItem = lv.getSelectionModel().getSelectedItem();
                // remove the selected item
                lv.getItems().remove(selectedItem);
                // if the fitter is in the team, remove it from the team
                for (int j = 0; j < department.getTeamList().length; j++) {
                    if (department.getTeamList()[j].getTeamName().equals(id)) {
                        for (int i = 0; i < department.getTeamList()[j].getFitterList().length; i++) {
                            if (department.getTeamList()[j].getFitterList()[i].getName().equals(selectedItem)) {
                                department.getTeamList()[j]
                                        .removeFitter(department.getTeamList()[j].getFitterList()[i]);
                            }
                        }
                    }
                }
                removeNameFromTable();
            });

            // toggle the avialability of the fitter through right click
            lvR.setOnMouseClicked(e -> {
                String id = MouseButton.SECONDARY.toString();
                if (e.getButton() == MouseButton.SECONDARY) {
                    // get the selected item
                    String selectedItem = lvR.getSelectionModel().getSelectedItem();

                    // create a context menu
                    ContextMenu contextMenu = new ContextMenu();
                    // create a menu item
                    MenuItem menuItem = new MenuItem("Byt team");
                    MenuItem menuItem2 = new MenuItem("Tillänglighet");
                    // add the menu item to the context menu
                    contextMenu.getItems().add(menuItem);
                    contextMenu.getItems().add(menuItem2);
                    // set the context menu on the listview
                    lvR.setContextMenu(contextMenu);
                    // set the action for the menu item
                    menuItem.setOnAction(e2 -> {

                        // get teamname of the fitter
                        String teamName = "";
                        for (int i = 0; i < department.getTeamList().length; i++) {
                            for (int j = 0; j < department.getTeamList()[i].getFitterList().length; j++) {
                                if (department.getTeamList()[i].getFitterList()[j].getName().equals(selectedItem)) {
                                    teamName = department.getTeamList()[i].getTeamName();
                                }
                            }
                        }

                        // choice dialog to choose a team among the existing teams
                        // make an arraylist from the team department.getTeamList()
                        ArrayList<String> teamList = new ArrayList<String>();
                        for (int i = 0; i < department.getTeamList().length; i++) {
                            // if teamName is not the same as the teamName of which the fitter is already in
                            if (!department.getTeamList()[i].getTeamName().equals(teamName)) {
                                teamList.add(department.getTeamList()[i].getTeamName());
                            }

                        }

                        // choice dialog box to choose from the teamList
                        ChoiceDialog<String> changeTeam = new ChoiceDialog<>(teamList.get(0), teamList);

                        changeTeam.setTitle("Byt team");
                        changeTeam.setHeaderText("Byt team");
                        changeTeam.setContentText("Välj ett team att byta till:");

                        // Traditional way to get the response value.
                        Optional<String> result = changeTeam.showAndWait();

                        // if the user clicks ok, move the fitter to the selected team
                        if (result.isPresent()) {
                            // get the selected team
                            String selectedTeam = result.get();
                            // get the selected fitter
                            String selectedFitter = lvR.getSelectionModel().getSelectedItem();

                            

                            // move the fitter to the selected team
                            for (int i = 0; i < department.getTeamList().length; i++) {
                                if (department.getTeamList()[i].getTeamName().equals(selectedTeam)) {
                                    for (int j = 0; j < department.getTeamList().length; j++) {
                                        for (int k = 0; k < department.getTeamList()[j].getFitterList().length; k++) {
                                            if (department.getTeamList()[j].getFitterList()[k].getName()
                                                    .equals(selectedFitter)) {
                                                //temp = department.getTeamList()[j].getFitterList()[k];
                                                // make a clone of the fitter to be moved
                                                Fitter temp = (Fitter) department.getTeamList()[j].getFitterList()[k];
                                                // remove the fitter from the team
                                                department.getTeamList()[j].removeFitter(temp);
                                                // add the fitter to the selected team
                                                department.getTeamList()[i].addFitter(temp);
                                            }
                                        }
                                    }
                                }
                            }

                            Parent parent = bp.getParent();
                            for (int i = 0; i < parent.getChildrenUnmodifiable().size(); i++) {
                                // loop through all the children of the parent and count how many listviews
                                // there are
                                for (int j = 0; j < ((Parent) parent.getChildrenUnmodifiable().get(i))
                                        .getChildrenUnmodifiable()
                                        .size(); j++) {

                                    Node node = ((Parent) parent.getChildrenUnmodifiable().get(i))
                                            .getChildrenUnmodifiable().get(j);

                                    // if the child is an instance of listview print "+1"
                                    if (node.getClass().equals(ListView.class)) {

                                        // if the id of the listview is selectedTeam + "P"
                                        if (((Parent) parent.getChildrenUnmodifiable().get(i)).getChildrenUnmodifiable()
                                                .get(j).getId().equals(selectedTeam + "P")) {
                                            // add the fitter to the listview
                                            ((ListView<String>) ((Parent) parent.getChildrenUnmodifiable().get(i))
                                                    .getChildrenUnmodifiable().get(j)).getItems().add(selectedFitter);
                                        }
                                    }
                                }
                            }

                            // remove the fitter from the listview
                            lvR.getItems().remove(selectedFitter);
                            // print the name of the fitter and all the competency of the fitter
                            System.out.println(selectedFitter);
                            for (int i = 0; i < department.getTeamList().length; i++) {
                                System.out.println(department.getTeamList()[i].getTeamName());
                                for (int j = 0; j < department.getTeamList()[i].getFitterList().length; j++) {
                                    System.out.println(department.getTeamList()[i].getFitterList()[j].getName());
                                    if (department.getTeamList()[i].getFitterList()[j].getName().equals(selectedFitter)) {
                                        for (int k = 0; k < department.getTeamList()[i].getFitterList()[j]
                                                .getCompetency().length; k++) {
                                            System.out.println(department.getTeamList()[i].getFitterList()[j]
                                                    .getCompetency()[k]);
                                        }
                                    }
                                }
                            }
                            

                        }

                    });
                    menuItem2.setOnAction(e3 -> {
                        // get the selected fitter
                        String selectedFitter = lvR.getSelectionModel().getSelectedItem();
                        
                        // get the fitter
                        Fitter  fitter = null;
                        for (int i = 0; i < department.getTeamList().length; i++) {
                            for (int j = 0; j < department.getTeamList()[i].getFitterList().length; j++) {
                                if (department.getTeamList()[i].getFitterList()[j].getName().equals(selectedFitter)) {
                                    fitter = department.getTeamList()[i].getFitterList()[j];

                                }
                            }
                        }
                        
                        fitter.toggleAvailability();
                        // give the listview item text red color
                        
                        
                        
                        fitterTable.refresh();
                    });
                    
                }
            });

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @FXML
    void testyytestyy(ActionEvent event) throws IOException {
        /*
        // open new window from the fittercompetencylist.fxml
        Parent root1 = FXMLLoader.load(getClass().getResource("fxml/FitterCompetencyList.fxml"));
        Scene scene = new Scene(root1);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();

        ListView<String> lv;

        // if there is a listview with the class "listview-allcomlist", add a id "abc"
        // to it
        if (scene.lookup(".listview-allcomlist") != null) {
            lv = (ListView<String>) scene.lookup(".listview-allcomlist");
            lv.setId("abc");
        }

        // update the table
        fitterTable.refresh();
         */
    }

    

    @FXML
    void removeClick(ActionEvent event) {
        // choice dialog to choose a team among the existing teams
        // make an arraylist from the team department.getTeamList()
        ArrayList<String> teamList = new ArrayList<String>();
        for (int i = 0; i < department.getTeamList().length; i++) {
            // if teamName is not the same as the teamName of which the fitter is already in

            teamList.add(department.getTeamList()[i].getTeamName());

        }

        // choice dialog box to choose from the teamList
        ChoiceDialog<String> changeTeam = new ChoiceDialog<>(teamList.get(0), teamList);
        changeTeam.setTitle("Ta bort team");
        changeTeam.setHeaderText("Ta bort team");
        changeTeam.setContentText("Team att ta bort:");
        changeTeam.showAndWait();

        // if the user clicks cancel, return
        if (changeTeam.getResult() == null) {
            return;
        }

        // remove the team from the department
        String teamName = changeTeam.getResult();
        for (int i = 0; i < department.getTeamList().length; i++) {
            if (department.getTeamList()[i].getTeamName().equals(teamName)) {
                if (department.getTeamList()[i].getFitterList().length == 0) {
                    department.removeTeam(teamName);
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Teamet har montörer");
                    alert.setContentText("Ta bort eller flytta allan montörer till ett annat team innan du tar bort teamet");
                    alert.showAndWait();
                    return;
                }
            }
        }

        // remove the team from the tilepane
        for (int i = 0; i < tPane.getChildren().size(); i++) {
            // if the tPane includes a borderpane
            if (tPane.getChildren().get(i) instanceof BorderPane) {
                // if a title is included the borderpane
                if (((BorderPane) tPane.getChildren().get(i)).getLeft() instanceof ListView) {

                    // if the id of the listview is the same as the team name + S
                    if (((ListView) ((BorderPane) tPane.getChildren().get(i)).getLeft()).getId()
                            .equals(changeTeam.getResult() + "S")) {
                        // remove the team from the tilepane
                        tPane.getChildren().remove(i);
                    }
                }
            }
        }



    }

    void addNameToTable() {
        // add the fitter to the table if the fitter is not already in the table
        for (int i = 0; i < department.getTeamList().length; i++) {
            for (int j = 0; j < department.getTeamList()[i].getFitterList().length; j++) {
                // if the fitter is not in the tableview
                if (!fitterTable.getItems().contains(department.getTeamList()[i].getFitterList()[j])) {
                    // add the fitter to the tableview
                    fitterTable.getItems().add(department.getTeamList()[i].getFitterList()[j]);
                }
            }
        }

    }

    void removeNameFromTable() {
        // remove the fitter from the table if the row with the name fitter is not in
        // any team
        for (int i = 0; i < fitterTable.getItems().size(); i++) {
            boolean found = false;
            for (int j = 0; j < department.getTeamList().length; j++) {
                for (int k = 0; k < department.getTeamList()[j].getFitterList().length; k++) {
                    if (fitterTable.getItems().get(i).getName()
                            .equals(department.getTeamList()[j].getFitterList()[k].getName())) {
                        found = true;
                    }
                }
            }
            if (!found) {
                fitterTable.getItems().remove(i);
            }
        }

    }

    public Scene newPopup(String fxml, String name) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root1);
        Stage primaryStage = new Stage();
        // set title
        primaryStage.setTitle(name);
        primaryStage.setScene(scene);
        primaryStage.show();

        return scene;
    }

    public void addRestOfStations(Scene scene, String ID) throws IOException {
        // get the name of the selected

        // if there is a listview with the ID "ID"
        ListView<String> lv;
        lv = (ListView<String>) scene.lookup(".listview-allcomlist");

        // iv ID of lv is "abc", add the fitters to the listview

        // add all the stations to the listview from every team
        for (int i = 0; i < department.getTeamList().length; i++) {
            for (int j = 0; j < department.getTeamList()[i].getStationList().length; j++) {
                lv.getItems().add(department.getTeamList()[i].getStationList()[j]);
            }
        }
        // remove if fitter already got the competency
        for (int i = 0; i < department.getTeamList().length; i++) {
            for (int j = 0; j < department.getTeamList()[i].getFitterList().length; j++) {
                if (department.getTeamList()[i].getFitterList()[j].getName().equals(ID)) {
                    for (int k = 0; k < department.getTeamList()[i].getFitterList()[j].getCompetency().length; k++) {
                        lv.getItems().remove(department.getTeamList()[i].getFitterList()[j].getCompetency()[k]);
                    }
                }
            }
        }

    }

    public void addCompetencyStations(Scene scene, String nameID) {
        ListView<String> lv;
        lv = (ListView<String>) scene.lookup(".listview-comlist");

        for (int i = 0; i < department.getTeamList().length; i++) {
            for (int j = 0; j < department.getTeamList()[i].getFitterList().length; j++) {
                if (department.getTeamList()[i].getFitterList()[j].getName().equals(nameID)) {
                    for (int k = 0; k < department.getTeamList()[i].getFitterList()[j].getCompetency().length; k++) {
                        lv.getItems().add(department.getTeamList()[i].getFitterList()[j].getCompetency()[k]);
                    }
                }

            }
        }
    }

    public static Department getDepartment() {
        return department;
    }

}
