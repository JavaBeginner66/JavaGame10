package view;

import controller.MenuPanelController;
import javafx.scene.layout.VBox;
import model.interfaces.GameEngine;

public class MenuPanel extends VBox {


    public void addListeners(MainFrame frame, GameEngine engine){
        MenuPanelController listener = new MenuPanelController(frame, engine);
    }
}
