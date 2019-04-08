package view;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class MyButton extends Button {

    private double cost;
    private int id;
    private String description;

    public MyButton(String text, Node graphic, int id, double cost, String description){
        super(text, graphic);
        this.id = id;
        this.cost = cost;
        this.description = description;
    }

    public double getCost(){
        return this.cost;
    }

    public int getID(){
        return this.id;
    }

    public String getDescription() {
        return description;
    }

}
