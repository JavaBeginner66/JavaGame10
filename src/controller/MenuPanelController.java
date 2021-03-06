package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import model.GameEngineImplement;
import model.interfaces.GameEngine;
import view.MainFrame;

import java.util.concurrent.TimeUnit;

public class MenuPanelController implements EventHandler<ActionEvent> {

    private MainFrame frame;
    private GameEngine engine;

    public MenuPanelController(MainFrame frame, GameEngine engine){
        this.frame = frame;
        this.engine = engine;
    }

    @Override
    public void handle(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        String item = (String) menuItem.getText();
        switch (item){
            case "Exit Game":
                exit();
                break;
            case "Save Game":
                save();
                break;
            case "Load Game":
                load();
                break;
            case "New Game":
                newGame();
                break;
            case "Description":
                description();
                break;
            case "About":
                about();
                break;
            case "Shortcuts":
                shortcuts();
                break;
        }
    }

    private void exit(){
        save();
        try {
        GameEngineImplement.executor.shutdown();
        GameEngineImplement.executor.awaitTermination(3, TimeUnit.SECONDS);
        GameEngineImplement.executor.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    private void save(){

    }
    private void load(){

    }
    private void newGame(){

    }
    private void description(){

    }
    private void about(){

    }
    private void shortcuts(){

    }
}
