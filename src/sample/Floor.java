package sample;

import javafx.scene.layout.HBox;

public class Floor extends HBox{

    private static int counter = 0;
    private static int minus = 10;

    private boolean isActive;
    private int floorNumber = 4;
    private long price;
    private long floorOutcome = 0;

    public Floor(){
        int multiplier = 15;
        isActive = false;
        floorNumber -= counter;
        counter++;
        price = (multiplier - minus) * 10000;
        minus++;
        if(counter > 4) {
            counter = 0;
            minus -= 10;
        }
        if(floorNumber != 0)
            this.setVisible(false);
    }

    public boolean getIsActive(){
        return isActive;
    }

    public void setActive(){
        isActive = true;
    }

    public long getPrice(){
        return price;
    }

    public long getFloorOutcome(){
        return floorOutcome;
    }

    public void setFloorOutcome(long value){
        floorOutcome += value;
    }

}
