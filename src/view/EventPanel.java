package view;

import controller.EventPanelController;
import javafx.scene.layout.GridPane;
import model.interfaces.GameEngine;

public class EventPanel extends GridPane {


    public void addListeners(MainFrame frame, GameEngine engine){
        EventPanelController listener = new EventPanelController();
    }
}
