package model;

import model.interfaces.GameEngine;
import view.observers.GameEngineCallbackGUI;

public class GameEngineImplement implements GameEngine {

    private GameEngineCallbackGUI callBackGUI;

    public GameEngineImplement(GameEngineCallbackGUI callBackGUI){
        this.callBackGUI = callBackGUI;
    }

    @Override
    public void progressBarResult(long time){
        callBackGUI.calculateGoldGained(time);
    }
}
