package view;

import controller.MenuPanelController;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.interfaces.GameEngine;

public class MenuPanel extends VBox {

    public MenuPanel(){
        Label l = new Label("Noe");
        this.getChildren().addAll(l);
        this.setStyle("-fx-background-color: #c4c4c4;");
    }


    public void addListeners(MainFrame frame, GameEngine engine){
        MenuPanelController listener = new MenuPanelController(frame, engine);
    }
}
