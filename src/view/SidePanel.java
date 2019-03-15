package view;

import controller.SidePanelController;
import javafx.scene.layout.HBox;
import model.interfaces.GameEngine;

public class SidePanel extends HBox {

    public void addListeners(MainFrame frame, GameEngine engine){
        SidePanelController listener = new SidePanelController();
    }
}
