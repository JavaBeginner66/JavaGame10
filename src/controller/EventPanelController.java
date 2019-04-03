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
            case "steal":
                engine.steal(b);
                break;
            case "income1":
                engine.steal(b);
                break;
            case "income2":
                engine.steal(b);
                break;
            case "income3":
                engine.steal(b);
                break;
            case "time1":
                engine.time(b);
                break;
            case "passive1":
                engine.autoIncome();
                break;
            case "passive4":
                engine.autoIncome();
                break;
        }
    }


}
