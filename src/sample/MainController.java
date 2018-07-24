package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    Button startButton;

    @FXML
    public void initGame(){
        ViewNavigator.loadView(ViewNavigator.VIEW_1);
    }

}
