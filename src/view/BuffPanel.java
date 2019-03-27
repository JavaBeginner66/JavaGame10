package view;

import controller.SidePanelController;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.interfaces.GameEngine;

public class BuffPanel extends GridPane {

    public BuffPanel(){

        this.setStyle("-fx-background-color: #c1c9c9;");
        this.setPadding(new Insets(10,15,5,5));
        this.setHgap(8);
        this.setVgap(8);

        Label l = new Label("Buffs");
        CheckBox cb1 = new CheckBox("First");
        CheckBox cb2 = new CheckBox("Second");

        this.add(l, 0 , 0);
        this.add(cb1, 0,1);
        this.add(cb2, 0,2);
    }

    public void addListeners(MainFrame frame, GameEngine engine){
        SidePanelController listener = new SidePanelController(frame, engine);
    }

}
