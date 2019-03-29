package view;

import controller.SidePanelController;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.interfaces.GameEngine;

public class SidePanel extends HBox {

    public SidePanel(){
        Label l = new Label("Noeeeeeeeeeeeeeeeeeeeeeee");
        this.getChildren().addAll(l);
        this.setStyle("-fx-background-color: #c4c4c4;");
    }

    public void addListeners(MainFrame frame, GameEngine engine){
        SidePanelController listener = new SidePanelController(frame, engine);
    }
}
