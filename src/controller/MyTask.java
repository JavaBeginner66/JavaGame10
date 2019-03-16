package controller;

import javafx.concurrent.Task;
import view.EventPanel;

public class MyTask extends Task<Void> {

    private int parameter;

    public MyTask(int parameter){
        this.parameter = parameter;
        new Thread(this).start();

    }

    public Void call(){
        for (int i = 1; i <= parameter; i++) {
            updateProgress(i, parameter);
        }
        return null;
    }
}
