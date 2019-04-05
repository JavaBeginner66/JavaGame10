package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.interfaces.GameEngine;
import view.MainFrame;

public class EventPanelController implements EventHandler<ActionEvent> {

    private MainFrame frame;
    private GameEngine engine;

    public EventPanelController(MainFrame frame, GameEngine engine){
        this.frame = frame;
        this.engine = engine;
    }


    @Override
    public void handle(ActionEvent event) {

        /* Getting button and button text */
        Button b = (Button) event.getSource();
        String s = b.getText();

        switch(s){
            case "steal": case "income1": case "income2": case "income3":
                engine.steal(b);
                break;
            case "time1": case "time2": case "time3":
                engine.time(b);
                break;
            case "energy1": case "energy2": case "energy3":
                engine.energy(b);
                break;
            case "passive1":
                engine.autoIncome();
                break;
            case "passive3":
                engine.autoTime();
                break;
            case "passive4":
                engine.autoEnergy();
                break;
        }
    }


}
