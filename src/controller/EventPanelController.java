package controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import model.interfaces.GameEngine;
import view.EventPanel;
import view.MainFrame;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

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
            case "Attack":
                engine.executeAutoAttack(frame.getEventPanel().getAttack());
                break;
            case "Button2":

                break;
            case "Button3":

                break;
            case "Auto-attack: (10 000)":
                engine.unlockAutoAttack();
                break;
        }
    }


}
