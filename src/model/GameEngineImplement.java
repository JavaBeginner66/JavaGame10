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

                        if(task.getParameterKey().equals("steal") && task.isDone() && ValueContainer.autoStealUnlocked) {
                            steal(null);
                        }

                        if(task.getParameterKey().equals("time1") && task.isDone() && ValueContainer.autoTimeUnlocked) {
                            time(null);
                        }


                        if(task.getParameterKey().equals("autoIncome") && task.isDone()){
                            tasks.remove(task);
                            steal(callBackGUI.getMainFrame().getEventPanel().getButton("steal"));
                            ValueContainer.autoStealUnlocked = true;
                        }
                        if(task.getParameterKey().equals("autoTime") && task.isDone()){
                            tasks.remove(task);
                            time(callBackGUI.getMainFrame().getEventPanel().getButton("time1"));
                            ValueContainer.autoTimeUnlocked = true;
                        }
                        if (task.isDone()) {

                            MyTask temp = null;

                            if (tasks.remove(task))
                                temp = task;
                            /* Stops multiple of same tasks from getting through */
                            if (temp != null)
                                if (temp.equals(task)) {
                                    Platform.runLater(() ->{
                                        catchCompletedTask(task.getParameterKey());
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

    private void catchCompletedTask(String parameterKey){
        Set<String> keys = valueContainer.getValueMap().keySet();

        for (String key: keys) {
            if(key.equals(parameterKey)){
                switch (key){
                    case "steal":
                        progressBarResult(ValueContainer.stealValue);
                        break;
                    case "income1":
                        progressBarResult(ValueContainer.incomeValue1);
                        break;
                    case "income2":
                        progressBarResult(ValueContainer.incomeValue2);
                        break;
                    case "income3":
                        progressBarResult(ValueContainer.incomeValue3);
                        break;
                    case "time1": case "time2": case "time3":
                        timeCut(parameterKey);
                        break;
                }
            }

        }

    }

    @Override
    public void steal(Button b){
        MyTask task = null;
        String income = "steal";
        if(b != null)
            income = b.getText();

        switch(income){
            case "steal":
                task = new MyTask("steal", b, callBackGUI.getMainFrame());
                callBackGUI.getMainFrame().getEventPanel().getButton("steal").setDisable(true);
                break;
            case "income1":
                task = new MyTask("income1", b, callBackGUI.getMainFrame());
                callBackGUI.getMainFrame().getEventPanel().getButton("income1").setDisable(true);
                break;
            case "income2":
                task = new MyTask("income2", b, callBackGUI.getMainFrame());
                callBackGUI.getMainFrame().getEventPanel().getButton("income2").setDisable(true);
                break;
            case "income3":
                task = new MyTask("income3", b, callBackGUI.getMainFrame());
                callBackGUI.getMainFrame().getEventPanel().getButton("income3").setDisable(true);
                break;

        }

        tasks.add(task);
        executor.execute(task);
    }

    @Override
    public void time(Button b){
        MyTask task = null;
        String time = "time1";

        if(b != null)
            time = b.getText();

        switch(time) {
            case "time1":
                task = new MyTask("time1", b, callBackGUI.getMainFrame());
                callBackGUI.getMainFrame().getEventPanel().getButton("time1").setDisable(true);
                break;
            case "time2":
                task = new MyTask("time2", b, callBackGUI.getMainFrame());
                callBackGUI.getMainFrame().getEventPanel().getButton("time2").setDisable(true);
                break;
            case "time3":
                task = new MyTask("time3", b, callBackGUI.getMainFrame());
                callBackGUI.getMainFrame().getEventPanel().getButton("time3").setDisable(true);
                break;
        }

        tasks.add(task);
        executor.execute(task);
    }

    @Override
    public void autoIncome(){
        MyTask task = new MyTask("autoIncome", null, callBackGUI.getMainFrame());

        tasks.add(task);
        callBackGUI.getMainFrame().getEventPanel().getButton("steal").setDisable(true);
        callBackGUI.getMainFrame().getEventPanel().getButton("passive1").setDisable(true);

        executor.execute(task);
    }
    @Override
    public void autoTime(){
        MyTask task = new MyTask("autoTime", null, callBackGUI.getMainFrame());

        tasks.add(task);
        callBackGUI.getMainFrame().getEventPanel().getButton("passive4").setDisable(true);
        callBackGUI.getMainFrame().getEventPanel().getButton("time1").setDisable(true);

        executor.execute(task);
    }



    public void progressBarResult(int value){
        double gold = value * valueContainer.getValue("multiplier");
        callBackGUI.getMainFrame().getRessourcePanel().setGoldLabel(gold);
    }

    private void timeCut(String key){

            Set keys = valueContainer.getValueMap().keySet();
            for (Iterator i = keys.iterator(); i.hasNext();) {
                String s = (String) i.next();
                if(s.equals("multiplier"))
                    continue;
                double value = valueContainer.getValue(s);
                switch(key){
                    case "time1":
                        value = value * ValueContainer.timeCut1;
                        break;
                    case "time2":
                        value = value * ValueContainer.timeCut2;
                        break;
                    case "time3":
                        value = value * ValueContainer.timeCut3;
                        break;
                }
                valueContainer.setValue(s, value);
                System.out.println(s + " " + value);
            }

        }


}
