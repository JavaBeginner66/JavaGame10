package controller;

import model.interfaces.GameEngine;
import view.MainFrame;

public class MenuPanelController {

    private MainFrame frame;
    private GameEngine engine;

    public MenuPanelController(MainFrame frame, GameEngine engine){
        this.frame = frame;
        this.engine = engine;
    }
}
