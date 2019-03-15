package view;

import controller.TextPanelController;
import javafx.scene.layout.Pane;
import model.interfaces.GameEngine;

public class TextPanel extends Pane {


    public void addListeners(MainFrame frame, GameEngine engine){
        TextPanelController listener = new TextPanelController();
    }
}
