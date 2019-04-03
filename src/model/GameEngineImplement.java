package model;

import controller.MyTask;
import javafx.application.Platform;
import javafx.scene.control.Button;
import model.interfaces.GameEngine;
import view.observers.GameEngineCallbackGUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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

                        if(task.getParameter() == valueContainer.getValue("steal") && task.isDone() && autoBasicUnlocked)
                            steal(null);


                        if(task.getParameter() == valueContainer.getValue("autoIncome") && task.isDone()){
                            tasks.remove(task);
                            steal(callBackGUI.getMainFrame().getEventPanel().getButton("steal"));
                            autoBasicUnlocked = true;
                        }
                        if (task.isDone()) {

                            MyTask temp = null;

                            if (tasks.remove(task))
                                temp = task;
                            /* Stops multiple of same tasks from getting through */
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
    public void steal(Button b){
        MyTask task = null;
        String income = "steal";
        if(b != null)
            income = b.getText();

        switch(income){
            case "steal":
                task = new MyTask(valueContainer.getValue("steal"), b, callBackGUI.getMainFrame());
                callBackGUI.getMainFrame().getEventPanel().getButton("steal").setDisable(true);
                break;
            case "income1":
                task = new MyTask(valueContainer.getValue("income1"), b, callBackGUI.getMainFrame());
                callBackGUI.getMainFrame().getEventPanel().getButton("income1").setDisable(true);
                break;
            case "income2":
                task = new MyTask(valueContainer.getValue("income2"), b, callBackGUI.getMainFrame());
                callBackGUI.getMainFrame().getEventPanel().getButton("income2").setDisable(true);
                break;
            case "income3":
                task = new MyTask(valueContainer.getValue("income3"), b, callBackGUI.getMainFrame());
                callBackGUI.getMainFrame().getEventPanel().getButton("income3").setDisable(true);
                break;

        }

        tasks.add(task);
        executor.execute(task);
    }

    @Override
    public void time(Button b){
        MyTask task = null;
        String time = b.getText();

        switch(time){
            case "time1":
                Set keys = valueContainer.getValueMap().keySet();
                for (Iterator i = keys.iterator(); i.hasNext();) {
                    String s = (String) i.next();
                    if(s.equals("multiplier"))
                        continue;
                    double value = valueContainer.getValue(s);
                    value = value * 0.9;
                    valueContainer.setValue(s, value);
                }
        }

        tasks.add(task);
        executor.execute(task);
    }

    @Override
    public void autoIncome(){
        MyTask task = new MyTask(valueContainer.getValue("autoIncome"), null, callBackGUI.getMainFrame());

        tasks.add(task);
        callBackGUI.getMainFrame().getEventPanel().getButton("steal").setDisable(true);
        callBackGUI.getMainFrame().getEventPanel().getButton("passive1").setDisable(true);

        executor.execute(task);
    }
    @Override
    public void autoTime(){
        MyTask task = new MyTask(valueContainer.getValue("autoIncome"), null, callBackGUI.getMainFrame());

        tasks.add(task);
        callBackGUI.getMainFrame().getEventPanel().getButton("passive4").setDisable(true);

        executor.execute(task);
    }



    @Override
    public void progressBarResult(double time){
        double gold = time * valueContainer.getValue("multiplier");
        callBackGUI.getMainFrame().getRessourcePanel().setGoldLabel(gold);
    }


}
