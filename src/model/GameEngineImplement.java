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

    private ExecutorService executor = Executors.newCachedThreadPool();
    public static ArrayList<MyTask> tasks = new ArrayList<>();

    public static boolean autoBasicUnlocked = false;

    private final int BASIC_ATTACK = 100;
    private final int SECONDS_3 = 300;
    private final int SECONDS_5 = 500;
    private final int AUTO_ATTACK = 150;

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

                        if(task.getParameter() == BASIC_ATTACK && task.isDone() && autoBasicUnlocked)
                            executeAutoAttack(null);


                        if(task.getParameter() == AUTO_ATTACK && task.isDone()){
                            tasks.remove(task);
                            executeAutoAttack(callBackGUI.getMainFrame().getEventPanel().getAttack());
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
        MyTask task = new MyTask(BASIC_ATTACK, b, callBackGUI.getMainFrame());

        tasks.add(task);
        // Shorten reference
        callBackGUI.getMainFrame().getEventPanel().getAttack().setDisable(true);

        executor.execute(task);
    }

    @Override
    public void unlockAutoAttack(){
        MyTask task4 = new MyTask(AUTO_ATTACK, null, callBackGUI.getMainFrame());

        tasks.add(task4);
        callBackGUI.getMainFrame().getEventPanel().getAttack().setDisable(true);
        callBackGUI.getMainFrame().getEventPanel().getAutoAttack().setDisable(true);

        executor.execute(task4);
    }


    @Override
    public void progressBarResult(long time){
        callBackGUI.calculateGoldGained(time);
    }
}
