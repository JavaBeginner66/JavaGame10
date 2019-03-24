package controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import model.interfaces.GameEngine;
import view.EventPanel;
import view.MainFrame;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public class EventPanelController implements EventHandler<ActionEvent> {

    private ExecutorService executor = Executors.newCachedThreadPool();
    public static ArrayList<MyTask> tasks = new ArrayList<>();

    private final int BASIC_ATTACK = 100;
    private final int SECONDS_3 = 300;
    private final int SECONDS_5 = 500;
    private final int AUTO_ATTACK = 150;

    public static boolean autoBasicUnlocked = false;

    private MainFrame frame;
    private GameEngine engine;

    public EventPanelController(MainFrame frame, GameEngine engine){
        this.frame = frame;
        this.engine = engine;
    }


    @Override
    public void handle(ActionEvent event) {

        /* Getting button and button text */
        Button b = (Button) event.getSource();
        String s = b.getText();

        /* Monitor Thread */
        if(tasks.isEmpty()){
            Runnable monitor = new Runnable() {

                @Override
                public void run() {
                    boolean running = true;
                    while(running) {
                            List<MyTask> tasksToRun = new ArrayList<>(tasks);
                            for (MyTask task : tasksToRun) {
                                Platform.runLater(() -> {
                                    if(task.getParameter() == BASIC_ATTACK && task.isDone() && autoBasicUnlocked) {

                                        executeAutoAttack(null);

                                        /* Attempt to remove duplicate tasks
                                        for (int i = 0; i < tasks.size(); i++) {
                                            if (tasks.get(i).getParameter() == BASIC_ATTACK) {
                                                System.out.println(tasks.remove(tasks.get(i)));
                                            }
                                        }
                                        */
                                    }

                                    if(task.getParameter() == AUTO_ATTACK && task.isDone()){
                                        tasks.remove(task);
                                        executeAutoAttack(frame.getEventPanel().getAttack());
                                        autoBasicUnlocked = true;
                                    }
                                    if (task.isDone()) {

                                        MyTask temp = null;

                                        if (tasks.remove(task))
                                            temp = task;
                                        /* Stops multiple of same tasks from getting through and giving gold for completion*/
                                        if (temp != null)
                                            if (temp.equals(task))
                                                engine.progressBarResult(task.getParameter());
                                    }
                                });
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

        switch(s){
            case "Attack":
                executeAutoAttack(frame.getEventPanel().getAttack());
                break;
            case "Button2":
                MyTask task2 = new MyTask(SECONDS_3, b, frame);
                tasks.add(task2);
                executor.execute(task2);
                break;
            case "Button3":
                MyTask task3 = new MyTask(SECONDS_5, b, frame);
                tasks.add(task3);
                executor.execute(task3);
                break;
            case "Auto-attack: (10 000)":
                unlockAutoAttack();
                break;
        }
    }

    private void executeAutoAttack(Button b){
        MyTask task = new MyTask(BASIC_ATTACK, b, frame);

        tasks.add(task);
        frame.getEventPanel().getAttack().setDisable(true);

        executor.execute(task);
    }

    private void unlockAutoAttack(){
        MyTask task4 = new MyTask(AUTO_ATTACK, null, frame);

        tasks.add(task4);
        frame.getEventPanel().getAttack().setDisable(true);
        frame.getEventPanel().getAutoAttack().setDisable(true);

        executor.execute(task4);
    }



}
