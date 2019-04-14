package controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import model.ValueContainer;
import model.interfaces.GameEngine;
import view.EventPanel;
import view.MainFrame;
import view.observers.GameEngineCallbackGUI;

import java.util.ArrayList;

@SuppressWarnings("TryWithIdenticalCatches")

public class MyTask extends Task<Void> {

    private String key;
    private Button b;
    private MainFrame frame;

    private ProgressBar bar;
    private Label barLabel;

    public MyTask(String key, Button b, MainFrame frame){
        this.key = key;
        this.b = b;
        this.frame = frame;
        addBar();

    }

    public Void call() {

        try {
            if(b != null)
                b.setDisable(true);
            for (long i = 1; i <= ValueContainer.getInstance().getValue(key); i++) {
                updateProgress(i, ValueContainer.getInstance().getValue(key));
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(b != null)
                b.setDisable(false);
            Platform.runLater(() -> {
                frame.getEventPanel().getBarPane().getChildren().remove(bar);
                frame.getEventPanel().getDescriptionPane().getChildren().remove(barLabel);
            });
        }
        return null;
    }

    private void addBar(){
        Platform.runLater(() ->{
            barLabel = frame.getEventPanel().addBarLabel(findIcon(key));
            bar = frame.getEventPanel().addProgressBar();
            GameEngineCallbackGUI.setLabelStyle(barLabel, 18);
            bar.setStyle(GameEngineCallbackGUI.progressBarStyle);
            bar.setPadding(new Insets(0,5,0,0));
            bar.progressProperty().bind(this.progressProperty());
        });
    }

    private String findIcon(String key){
        String img = "";
        switch(key){
            case "task": case "income1": case"income2": case"income3": case"passive1":
                img = GameEngineCallbackGUI.goldImg;
                break;
            case "strength1": case"strength2": case"strength3": case"passive2":
                img = GameEngineCallbackGUI.strengthImg;
                break;
            case "energy1": case"energy2": case"energy3": case"passive4":
                img = GameEngineCallbackGUI.energyImg;
                break;
            case"time1": case"time2": case"time3": case"passive3":
                img = GameEngineCallbackGUI.timeImg;

        }
        return img;
    }

    public String getParameterKey() {

        return key;
    }
}
