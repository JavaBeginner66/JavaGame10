package controller;

import model.interfaces.GameEngine;
import view.MainFrame;

public class SidePanelController {

    private MainFrame frame;
    private GameEngine engine;

    public SidePanelController(MainFrame frame, GameEngine engine){
        this.frame = frame;
        this.engine = engine;
    }
}
