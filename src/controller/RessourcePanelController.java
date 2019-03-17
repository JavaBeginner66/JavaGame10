package controller;

import model.interfaces.GameEngine;
import view.MainFrame;

public class RessourcePanelController {

    private MainFrame frame;
    private GameEngine engine;

    public RessourcePanelController(MainFrame frame, GameEngine engine){
        this.frame = frame;
        this.engine = engine;
    }
}
