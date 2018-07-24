package sample;

import javafx.scene.control.Button;

public class WorkerButton extends Button {

    private static int counter = 0;
    private long priceModifier;
    private static int id = 0;

    private boolean isActive;
    private long basePrice;
    private long outcome;
    private long outcomeModifier;
    private long price;

    public WorkerButton(Floor floor){
        priceModifier = floor.getPrice() / 1000;
        outcomeModifier = floor.getPrice() / 10000;
        basePrice = 100;
        isActive = false;
        id++;
        counter++;
    }

    public void setActive(){
        isActive = true;
    }

    public boolean getIsActive(){return isActive;}

    public void setEconomy(){
        if(id == 6){
            price = (counter  * basePrice * priceModifier) * 4;
            outcome = (id * outcomeModifier) * 2;
            id = 0;
        }
        else {
            price = (counter * basePrice * priceModifier) * 2;
            outcome = (id * outcomeModifier) * 2;
        }

    }

    public long getPrice(){
        return price;
    }

    public long getOutcome(){return outcome;}

}
