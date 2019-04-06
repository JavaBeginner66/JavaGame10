package model.interfaces;

import javafx.scene.control.Button;

/**
 * GameEngine interface
 * Implementing classes; GameEngineImplement
 */

public interface GameEngine {

    /**
     *  Thread that watches over tasks.
     *  Primarly used to spot whenever a thread is finished
     *  to give rewards to player.
     */
    void monitorThread();

    /**
     *
     * Method task starts a Task thread that generates the lowest gold income.
     *
     *  @param b
     *      b is the button reference sent from the EventHandler.
     *      Used to disable/enable buttons before/after threads.
     */
    void task(Button b, String key, String keyNumber);

    /**
     *
     * Method task starts a Task thread that lowers the time spent on all tasks.
     *
     *  @param b
     *      b is the button reference sent from the EventHandler.
     *      Used to disable/enable buttons before/after threads.
     */


    /**
     * Method autoIncome creates a Task thread that makes the task Task
     * automated on completion.
     */
    void automateTask(String key, String passive, String buttonToDisable);

    void taskResult(int value, double multiplier, String attribute);










}
