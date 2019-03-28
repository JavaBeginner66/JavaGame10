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
     * Method executeAttack starts the fastest Task thread.
     *
     *  @param b
     *      b is the button reference sent from the EventHandler.
     *      Used to disable/enable buttons before/after threads.
     */
    void executeAutoAttack(Button b);

    /**
     * Method unlockAutoAttack creates a Task thread that makes the executeAutoAttack Task
     * automated on completion.
     */
    void unlockAutoAttack();

    /**
     *
     * @param time
     *          How much time the progressbar spent
     */
    void progressBarResult(long time);

}
