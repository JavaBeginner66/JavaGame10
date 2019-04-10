package model;

import controller.GameControllerException;
import controller.MyTask;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import model.interfaces.GameEngine;
import view.EventPanel;
import view.MyButton;
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
                while(true) {
                    List<MyTask> tasksToRun = new ArrayList<>(tasks);
                    for (MyTask task : tasksToRun) {

                        if(task.getParameterKey().equals("task") && task.isDone() && ValueContainer.autoTaskUnlocked) {
                            task(null, "task", "");
                        }

                        if(task.getParameterKey().equals("time1") && task.isDone() && ValueContainer.autoTimeUnlocked) {
                            task(null, "time" , "1");
                        }

                        if(task.getParameterKey().equals("energy1") && task.isDone() && ValueContainer.autoEnergyUnlocked) {
                            task(null, "energy", "1");
                        }

                        if(task.getParameterKey().equals("strength1") && task.isDone() && ValueContainer.autoStrengthUnlocked) {
                            task(null, "strength", "1");
                        }


                        if(task.getParameterKey().equals("passive1") && task.isDone()){
                            tasks.remove(task);
                            task(callBackGUI.getMainFrame().getEventPanel().getButton("task"), "task", "");
                            ValueContainer.autoTaskUnlocked = true;
                        }
                        if(task.getParameterKey().equals("passive3") && task.isDone()){
                            tasks.remove(task);
                            task(callBackGUI.getMainFrame().getEventPanel().getButton("time1"), "time", "1");
                            ValueContainer.autoTimeUnlocked = true;
                        }
                        if(task.getParameterKey().equals("passive4") && task.isDone()){
                            tasks.remove(task);
                            task(callBackGUI.getMainFrame().getEventPanel().getButton("energy1"), "energy", "1");
                            ValueContainer.autoEnergyUnlocked = true;
                        }
                        if(task.getParameterKey().equals("passive2") && task.isDone()){
                            tasks.remove(task);
                            task(callBackGUI.getMainFrame().getEventPanel().getButton("strength1"), "strength", "1");
                            ValueContainer.autoStrengthUnlocked = true;
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
        EventPanel event = callBackGUI.getMainFrame().getEventPanel();

        for (String key: keys) {
            if(key.equals(parameterKey)){

                switch (key){
                    case "task":
                        taskResult(ValueContainer.taskValue, valueContainer.getValue("goldMultiplier"), "income");
                        runningTasksStayDisabled("income1");
                        runningTasksStayDisabled("strength1");
                        runningTasksStayDisabled("time1");
                        runningTasksStayDisabled("energy1");
                        break;
                    case "income1":
                        taskResult(ValueContainer.incomeValue1, valueContainer.getValue("goldMultiplier"), "income");
                        runningTasksStayDisabled("income2");
                        checkUnlocks("energy1", "passive1");
                        break;
                    case "income2":
                        taskResult(ValueContainer.incomeValue2, valueContainer.getValue("goldMultiplier"), "income");
                        runningTasksStayDisabled("income3");
                        checkUnlocks("strength2", "passive2");
                        break;
                    case "income3":
                        taskResult(ValueContainer.incomeValue3, valueContainer.getValue("goldMultiplier"), "income");
                        break;
                    case "energy1":
                        taskResult(ValueContainer.energyValue1, valueContainer.getValue("energyMultiplier"), "energy");
                        runningTasksStayDisabled("energy2");
                        checkUnlocks("income1", "passive1");
                        break;
                    case "energy2":
                        taskResult(ValueContainer.energyValue2, valueContainer.getValue("energyMultiplier"), "energy");
                        runningTasksStayDisabled("energy3");
                        checkUnlocks("time2", "passive4");
                        break;
                    case "energy3":
                        taskResult(ValueContainer.energyValue3, valueContainer.getValue("energyMultiplier"), "energy");
                        break;
                    case "strength1":
                        taskResult(ValueContainer.strengthValue1, valueContainer.getValue("strengthMultiplier"), "strength");
                        runningTasksStayDisabled("strength2");
                        break;
                    case "strength2":
                        taskResult(ValueContainer.strengthValue2, valueContainer.getValue("strengthMultiplier"), "strength");
                        runningTasksStayDisabled("strength3");
                        checkUnlocks("income2", "passive2");
                        break;
                    case "strength3":
                        taskResult(ValueContainer.strengthValue3, valueContainer.getValue("strengthMultiplier"), "strength");
                        checkUnlocks("time3", "passive3");
                        break;
                    case "time1":
                        timeCut(parameterKey);
                        runningTasksStayDisabled("time2");
                        break;
                    case "time2":
                        timeCut(parameterKey);
                        runningTasksStayDisabled("time3");
                        checkUnlocks("energy2", "passive4");
                        break;
                    case "time3":
                        timeCut(parameterKey);
                        checkUnlocks("strength3", "passive3");
                        break;
                }
            }

        }

    }

    private void runningTasksStayDisabled(String key){
        int incr = 0;
        for (MyTask task : tasks) {
            if (task.getParameterKey().equals(key)) {
                incr = 1;
                break;
            }
        }
        if(incr == 0)
            callBackGUI.getMainFrame().getEventPanel().getButton(key).setDisable(false);

    }

    private void checkUnlocks(String button, String passive){
        if(!callBackGUI.getMainFrame().getEventPanel().getButton(button).isDisabled()) {

            switch (passive) {
                case "passive1":

                    if (!ValueContainer.autoTaskUnlocked)
                        runningTasksStayDisabled(passive);
                    break;
                case "passive2":
                    if (!ValueContainer.autoStrengthUnlocked)
                        runningTasksStayDisabled(passive);
                    break;
                case "passive3":
                    if (!ValueContainer.autoTimeUnlocked)
                        runningTasksStayDisabled(passive);
                    break;
                case "passive4":
                    if (!ValueContainer.autoEnergyUnlocked)
                        runningTasksStayDisabled(passive);
                    break;
            }
        }
    }


    @Override
    public void task(Button b, String key, String keyNumber){
        MyTask task = null;
        String value = key + keyNumber;

        if (b != null)
            value = b.getText();

        try {

            if (!(callBackGUI.getMainFrame().getRessourcePanel().getGold() >=
                    callBackGUI.getMainFrame().getEventPanel().getButton(value).getCost())) {
                throw new GameControllerException("Insufficient amount of gold."+ "\n" + "Hover over a button to see requirements.");
            } else {

                task = new MyTask(value, b, callBackGUI.getMainFrame());
                callBackGUI.getMainFrame().getEventPanel().getButton(value).setDisable(true);

                tasks.add(task);
                executor.execute(task);

                final String val = value;

                Platform.runLater(()->{
                    callBackGUI.getMainFrame().getRessourcePanel().setGoldLabel(-callBackGUI.getMainFrame().getEventPanel().getButton(val).getCost());
                } );

            }
        }catch (GameControllerException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

    }



    @Override
    public void automateTask(String key, String passive, String buttonToDisable){

        try{
            if(!(callBackGUI.getMainFrame().getRessourcePanel().getGold() >=
                    callBackGUI.getMainFrame().getEventPanel().getButton(passive).getCost())) {
                throw new GameControllerException("Insufficient amount of gold" + "\n" + "Hover over a button too see requirements");
            } else {

                MyTask task = new MyTask(key, null, callBackGUI.getMainFrame());

                tasks.add(task);

                callBackGUI.getMainFrame().getEventPanel().getButton(passive).setDisable(true);
                callBackGUI.getMainFrame().getEventPanel().getButton(buttonToDisable).setDisable(true);

                executor.execute(task);
            }
        }catch (GameControllerException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

    }

    @Override
    public void taskResult(int value, double multiplier, String attribute){
        switch(attribute){
            case "income":
                callBackGUI.getMainFrame().getRessourcePanel().setGoldLabel(value*multiplier);
                break;
            case "energy":
                callBackGUI.getMainFrame().getRessourcePanel().setEnergyLabel(value*multiplier);
                break;
            case "strength":
                callBackGUI.getMainFrame().getRessourcePanel().setStrengthLabel(value*multiplier);
                break;
        }

    }


    private void timeCut(String key){

            Set keys = valueContainer.getValueMap().keySet();
            double timeReduction = 0;
            for (Iterator i = keys.iterator(); i.hasNext();) {
                String s = (String) i.next();
                if(s.equals("goldMultiplier"))
                    continue;
                if(s.equals("energyMultiplier"))
                    continue;
                if(s.equals("strengthMultiplier"))
                    continue;
                double value = valueContainer.getValue(s);
                switch(key){
                    case "time1":
                        value = value * ValueContainer.timeCut1;
                        timeReduction = 1-ValueContainer.timeCut1;
                        break;
                    case "time2":
                        value = value * ValueContainer.timeCut2;
                        timeReduction = 1-ValueContainer.timeCut2;
                        break;
                    case "time3":
                        value = value * ValueContainer.timeCut3;
                        timeReduction = 1-ValueContainer.timeCut3;
                        break;
                }
                valueContainer.setValue(s, value);
            }
            callBackGUI.getMainFrame().getRessourcePanel().setTimeLabel(timeReduction);

        }


}
