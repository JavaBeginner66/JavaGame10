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
     * Method steal starts a Task thread that generates the lowest gold income.
     *
     *  @param b
     *      b is the button reference sent from the EventHandler.
     *      Used to disable/enable buttons before/after threads.
     */
    void steal(Button b);

    /**
     *
     * Method steal starts a Task thread that lowers the time spent on all tasks.
     *
     *  @param b
     *      b is the button reference sent from the EventHandler.
     *      Used to disable/enable buttons before/after threads.
     */
    void time(Button b);

    /**
     * Method autoIncome creates a Task thread that makes the steal Task
     * automated on completion.
     */
    void autoIncome();

    /**
     *  Method autoTime creates a task which automates time reduction on tasks
     */
    void autoTime();

    void autoStrength();

    void autoEnergy();

    void energy(Button b);

    void strength(Button b);





}
