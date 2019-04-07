package view;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class MyButton extends Button {

    private double cost;
    private int id;

    public MyButton(String text, Node graphic, int id, double cost){
        super(text, graphic);
        this.id = id;
        this.cost = cost;
    }

    public double getCost(){
        return this.cost;
    }

    public int getID(){
        return this.id;
    }
}
