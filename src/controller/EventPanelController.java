package controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import model.interfaces.GameEngine;
import view.EventPanel;
import view.MainFrame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public class EventPanelController implements EventHandler<ActionEvent> {

    private ExecutorService executor = Executors.newCachedThreadPool();

    private MainFrame frame;
    private GameEngine engine;

    public EventPanelController(MainFrame frame, GameEngine engine){
        this.frame = frame;
        this.engine = engine;
    }


    @Override
    public void handle(ActionEvent event) {
        Button b = (Button) event.getSource();
        String s = b.getText();
        MyTask task;
        switch(s){
            case "Button1":
                executor.execute(new MyTask(100, b, frame, engine));
                break;
            case "Button2":
                executor.execute(new MyTask(1000, b, frame, engine));
                break;
            case "Button3":
                executor.execute(new MyTask(2000, b, frame, engine));
                break;
        }
        executor.shutdown();


    }


}
