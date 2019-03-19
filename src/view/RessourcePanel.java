package view;

import controller.RessourcePanelController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import model.interfaces.GameEngine;

public class RessourcePanel extends GridPane {

    private int gold;

    public RessourcePanel(){
        Label gold = new Label("Gold: ");
        gold.setFont(Font.font("Monospace",
                FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.setStyle("-fx-background-color: #c4c4c4;");
        this.setPadding(new Insets(0,5,5,15));

        this.add(gold, 0,0);
    }




    public void addListeners(MainFrame frame, GameEngine engine){
        RessourcePanelController listener = new RessourcePanelController(frame, engine);
    }
}
