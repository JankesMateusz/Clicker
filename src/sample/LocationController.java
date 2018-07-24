package sample;

import javafx.animation.AnimationTimer;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LocationController{

    private long moneyToAdd = 1;
    private Model model;
    private VBox currentLocation;
    private VBox warsaw;
    private VBox gdansk;
    private VBox tokyo;

    @FXML
    Label finances;

    @FXML
    StackPane floors;

    @FXML
    Button gain;

    public LocationController (){
        model = new Model(this);
        setLocations();
    }

    public void initialize(){
        runMoney();
        floors.getChildren().add(warsaw);
        currentLocation = warsaw;
    }

    private void viewMoney(){
        finances.setText(Long.toString(model.getMoney()) + " $");
    }

    private void runMoney(){
        AnimationTimer at = new AnimationTimer() {

            @Override
            public void handle(long now) {
                viewMoney();
                model.gainMoney(moneyToAdd);
            }
        };
        at.start();
    }

    private void setLocations() {
        warsaw = model.initLocation();
        gdansk = model.initLocation();
        tokyo = model.initLocation();
    }

    @FXML
    public void changeLocation(ActionEvent event){
        Button button = (Button) event.getSource();
        if(button.getText().equals("Gdańsk")){
            floors.getChildren().remove(currentLocation);
            floors.getChildren().add(gdansk);
            currentLocation = gdansk;
        }
        else if(button.getText().equals("Tokio")){
            floors.getChildren().remove(currentLocation);
            floors.getChildren().add(tokyo);
            currentLocation = tokyo;
        }
        else {
            floors.getChildren().remove(currentLocation);
            floors.getChildren().add(warsaw);
            currentLocation = warsaw;
        }
    }

    public void gainMoney(){
        model.gainMoney(1000);
    }

    public void addMoneyToAdd(long value){
        moneyToAdd += value;
    }

    public VBox getCurrentLocation() {
        return currentLocation;
    }
}
