package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class ViewController {

    @FXML
    private StackPane view;

    public void setView(Node node){
        view.getChildren().setAll(node);
    }
}
