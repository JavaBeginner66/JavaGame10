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
            barLabel = frame.getEventPanel().addBarLabel("  " + key + "   ");
            GameEngineCallbackGUI.setLabelStyle(barLabel, 18);
            bar = frame.getEventPanel().addProgressBar();
            bar.setStyle(GameEngineCallbackGUI.progressBarStyle);
            bar.setPadding(new Insets(0,5,0,0));
            bar.progressProperty().bind(this.progressProperty());
        });
    }

    public String getParameterKey() {

        return key;
    }
}
