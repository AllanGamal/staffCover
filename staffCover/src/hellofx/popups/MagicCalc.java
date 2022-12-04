package hellofx.popups;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class MagicCalc {

   public static void addSolution(Scene scene, String vbox) {
        // add a listview to the vbox with the id "vble"
        
            VBox vble = (VBox) scene.lookup(vbox);
            ListView<String> listview = new ListView<String>();
            listview.setPrefSize(250, 500);
            // place in middle of the vbox
             
            listview.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
            vble.getChildren().add(listview);
        
   }

}
