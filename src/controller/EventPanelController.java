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
            case "task":
                engine.task(b, "task", "");
                break;
            case "income1":
                engine.task(b, "income", "1");
                break;
            case "income2":
                engine.task(b, "income", "2");
                break;
            case "income3":
                engine.task(b, "income", "3");
                break;
            case "time1":
                engine.task(b, "time", "1");
                break;
            case "time2":
                engine.task(b, "time", "2");
                break;
            case "time3":
                engine.task(b, "time", "3");
                break;
            case "energy1":
                engine.task(b, "energy", "1");
                break;
            case "energy2":
                engine.task(b, "energy", "2");
                break;
            case "energy3":
                engine.task(b, "energy", "3");
                break;
            case "strength1":
                engine.task(b, "strength", "1");
                break;
            case "strength2":
                engine.task(b, "strength", "2");
                break;
            case "strength3":
                engine.task(b, "strength", "3");
                break;
            case "passive1":
                engine.automateTask("passive1", "passive1", "task");
                break;
            case "passive2":
                engine.automateTask("passive2", "passive2", "strength1");
                break;
            case "passive3":
                engine.automateTask("passive3", "passive3", "time1");
                break;
            case "passive4":
                engine.automateTask("passive4", "passive4", "energy1");
                break;
        }
    }


}
