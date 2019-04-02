package model;

import controller.MyTask;
import javafx.application.Platform;
import javafx.scene.control.Button;
import model.interfaces.GameEngine;
import view.observers.GameEngineCallbackGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameEngineImplement implements GameEngine {

    private GameEngineCallbackGUI callBackGUI;

    public static ExecutorService executor = Executors.newCachedThreadPool();

    private ValueContainer valueContainer = ValueContainer.getInstance();

    private ArrayList<MyTask> tasks = new ArrayList<>();

    private boolean autoBasicUnlocked = false;


    public GameEngineImplement(GameEngineCallbackGUI callBackGUI){
        this.callBackGUI = callBackGUI;
        monitorThread();
    }

    @Override
    public void monitorThread(){
        Runnable monitor = new Runnable() {

            @Override
            public void run() {
                boolean running = true;
                while(running) {
                    List<MyTask> tasksToRun = new ArrayList<>(tasks);
                    for (MyTask task : tasksToRun) {

                        if(task.getParameter() == valueContainer.getValue("basic") && task.isDone() && autoBasicUnlocked)
                            executeAutoAttack(null);


                        if(task.getParameter() == valueContainer.getValue("auto") && task.isDone()){
                            tasks.remove(task);
                            executeAutoAttack(callBackGUI.getMainFrame().getEventPanel().getButtons("steal"));
                            autoBasicUnlocked = true;
                        }
                        if (task.isDone()) {

                            MyTask temp = null;

                            if (tasks.remove(task))
                                temp = task;
                            /* Stops multiple of same tasks from getting through and giving gold for completion*/
                            if (temp != null)
                                if (temp.equals(task)) {
                                    Platform.runLater(() ->{
                                        progressBarResult(task.getParameter());
                                    });
                                }
                        }
                    }
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread m = new Thread(monitor);
        m.start();
    }

    @Override
    public void executeAutoAttack(Button b){
        MyTask task = new MyTask(valueContainer.getValue("basic"), b, callBackGUI.getMainFrame());

        tasks.add(task);
        // Shorten reference
        callBackGUI.getMainFrame().getEventPanel().getButtons("steal").setDisable(true);

        executor.execute(task);
    }

    @Override
    public void unlockAutoAttack(){
        MyTask task4 = new MyTask(valueContainer.getValue("auto"), null, callBackGUI.getMainFrame());

        tasks.add(task4);
        callBackGUI.getMainFrame().getEventPanel().getButtons("steal").setDisable(true);
        callBackGUI.getMainFrame().getEventPanel().getButtons("autoAttack").setDisable(true);

        executor.execute(task4);
    }


    @Override
    public void progressBarResult(long time){
        long gold = time * valueContainer.getValue("multiplier");
        callBackGUI.getMainFrame().getRessourcePanel().setGoldLabel(gold);
    }


}
