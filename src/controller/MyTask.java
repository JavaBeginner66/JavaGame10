package controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import model.interfaces.GameEngine;
import view.EventPanel;
import view.MainFrame;

import java.util.ArrayList;

@SuppressWarnings("TryWithIdenticalCatches")

public class MyTask extends Task<Void> {

    private long parameter;
    private Button b;
    private MainFrame frame;

    private ProgressBar bar;

    public MyTask(long parameter, Button b, MainFrame frame){
        this.parameter = parameter;
        this.b = b;
        this.frame = frame;
        addBar();

    }

    public Void call() {

        try {
            if(b != null)
                b.setDisable(true);
            for (long i = 1; i <= parameter; i++) {
                updateProgress(i, parameter);
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
            });
        }
        return null;
    }

    private void addBar(){
        Platform.runLater(() ->{
            bar = frame.getEventPanel().addProgressBar();
            bar.progressProperty().bind(this.progressProperty());
        });
    }

    public long getParameter() {
        return parameter;
    }
}
