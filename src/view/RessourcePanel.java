package view;

import controller.RessourcePanelController;
import javafx.scene.layout.Pane;
import model.interfaces.GameEngine;

public class RessourcePanel extends Pane {

    public RessourcePanel(){

    }


    public void addListeners(MainFrame frame, GameEngine engine){
        RessourcePanelController listener = new RessourcePanelController(frame, engine);
    }
}
