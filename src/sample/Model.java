package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Model{

    private final int numberOfFloors;
    private final int numberOfWorkers;

    private long money = 0;
    private LocationController controller;
    private ArrayList<Floor> floors;

    public Model(LocationController controller){
        this.controller = controller;
        numberOfWorkers = 6;
        numberOfFloors = 4;
        floors = new ArrayList<>();
    }

    public void gainMoney(long value){
        money += value;
    }

    public long getMoney(){
        return money;
    }

    public ArrayList<Floor> makeFloors(){
        for(int i = numberOfFloors; i >= 0; i--){
            floors.add(makeFloor());
        }
        return floors;
    }

    public ArrayList<WorkerButton> makeWorkers(Floor f, Label l) {
        ArrayList<WorkerButton> workers = new ArrayList<>();
        for(int i = 0; i < numberOfWorkers; i++){
            workers.add(makeWorkerButton(f, l));
        }

        return workers;
    }

    private Floor makeFloor(){
        Floor f = new Floor();
        f.setPrefHeight(130);
        f.setAlignment(Pos.CENTER);
        f.setSpacing(10);
        f.getStyleClass().add("floor");

        return f;
    }

    public VBox initLocation() {
        VBox vb = new VBox();
        vb.setMaxWidth(700);
        vb.setSpacing(13);
        vb.setAlignment(Pos.CENTER);
        vb.getStyleClass().add("locationPanel");

        ArrayList<Floor> floorList = makeFloors();
        for (Floor f : floorList) {
            f.getChildren().add(buyFloorButton(f));
            vb.getChildren().add(f);
        }
        floorList.clear();
        return vb;
    }

    private Button buyFloorButton(Floor f){
        Button b = new Button();
        Label l = moneyLabel(f);
        b.setPrefHeight(100);
        b.setPrefWidth(300);
        b.setText("KUP PIĘTRO: " + f.getPrice() + "$");
        b.getStyleClass().add("buyButton");

        b.setOnAction(e -> {
            VBox currentLocation = controller.getCurrentLocation();
            int index = currentLocation.getChildren().indexOf(f);
            Floor toCompare;

            if(currentLocation.getChildren().indexOf(f) != numberOfFloors)
                toCompare = (Floor) currentLocation.getChildren().get(index + 1);
            else
                toCompare = (Floor) currentLocation.getChildren().get(index);

            if((currentLocation.getChildren().indexOf(f) == numberOfFloors && money >= f.getPrice()) || (money >= f.getPrice() && toCompare.getIsActive())) {
                money -= f.getPrice();
                f.getChildren().remove(b);
                f.getChildren().addAll(makeWorkers(f, l));
                f.getChildren().add(l);
                f.setActive();
                if(currentLocation.getChildren().indexOf(f) != 0)
                    currentLocation.getChildren().get(index - 1).setVisible(true);
            }

        });

        return b;
    }

    private Label moneyLabel(Floor f){
        Label l = new Label();
        l.setPrefHeight(50);
        l.setPrefWidth(100);
        l.setText(f.getFloorOutcome() + "$/s");
        l.setAlignment(Pos.CENTER);
        l.getStyleClass().add("outcome");

        return l;
    }

    private WorkerButton makeWorkerButton(Floor f, Label l){
        WorkerButton wb = new WorkerButton(f);
        wb.setEconomy();
        wb.setPrefHeight(80);
        wb.setPrefWidth(80);
        wb.setText(Long.toString(wb.getPrice()) + "$");
        wb.getStyleClass().add("workerButton");

        wb.setOnAction(e -> {

            int index = f.getChildren().indexOf(wb);
            WorkerButton toCompare;
            if(index != 0){
                toCompare = (WorkerButton) f.getChildren().get(index - 1);
            }
            else
                toCompare = (WorkerButton) f.getChildren().get(index);

            if((f.getChildren().indexOf(wb) == 0 && money >= wb.getPrice()) || (money >= wb.getPrice() && toCompare.getIsActive())) {
                money -= wb.getPrice();
                wb.setActive();
                controller.addMoneyToAdd(wb.getOutcome());
                wb.setDisable(true);
                f.setFloorOutcome(wb.getOutcome());
                l.setText(Long.toString(f.getFloorOutcome()) + " $/s");
            }
        });

        return wb;
    }
}
