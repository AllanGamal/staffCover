package hellofx.popups;

import hellofx.Controller;
import hellofx.deparment.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class FitterCompetencyList {
   

    @FXML
    private Button addToComList;

    @FXML
    private Button removeFrComList;

    
    @FXML
    void addStToComp(ActionEvent event) {
        // if the button that is clicked got a class "addToComList", get the stage and put "Stage stage = (Stage) addToComList.getScene().getWindow();" in the code
        String className = "addToComList";
        Stage stage = getStage(event);
        String fitterName = stage.getTitle();
        
        // if there is a listview with the class "listview-allcomlist", lv = listview
        String lvClassName1 = "listview-allcomlist";
        String lvClassName2 = "listview-comlist";
        ListView<String> lvl = (ListView<String>) stage.getScene().lookup("." + lvClassName1);
        ListView<String> lvr = (ListView<String>) stage.getScene().lookup("." + lvClassName2);
        String selectedItem = lvl.getSelectionModel().getSelectedItem();

        // check if there is a fitter with the name fitterName in the department
        for (int i = 0; i < Controller.getDepartment().getTeamList().length; i++) {
            for (int j = 0; i < Controller.getDepartment().getTeamList()[i].getFitterList().length; j++) {
                if (Controller.getDepartment().getTeamList()[i].getFitterList()[j].getName().equals(fitterName)) {

                    Controller.getDepartment().getTeamList()[i].getFitterList()[j].addCompetency(selectedItem);
                    lvr.getItems().add(selectedItem);
                    lvl.getItems().remove(selectedItem);
                    System.out.println(selectedItem);
                    break;
                }
            }
        }

        

        //


    }

    public Stage getStage(ActionEvent event) {
        // if scene includes className, then return stage
            Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();
            return stage;
    }

   
    

}