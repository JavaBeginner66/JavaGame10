package controller;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import model.ValueContainer;
import model.interfaces.GameEngine;
import view.MainFrame;

public class BuffPanelController implements EventHandler {

    private MainFrame frame;
    private GameEngine engine;

    private ValueContainer valueContainer = ValueContainer.getInstance();


    public BuffPanelController(MainFrame frame, GameEngine engine){
        this.frame = frame;
        this.engine = engine;
    }

    @Override
    public void handle(Event event) {
        CheckBox s = (CheckBox)event.getSource();
        String box = s.getText();
        boolean state = s.isSelected();

        switch(box){
            case "1":
                statsIncrease(state,"goldMultiplier", 5,  "buff1");
                break;
            case "2":
                statsIncrease(state, "energyMultiplier", 4,  "buff2");
                break;
            case "3":
                statsIncrease(state, "strengthMultiplier", 3,  "buff3");
                break;
            case "4":
                statsIncrease(state,"timeMultiplier", 2,  "buff4");
                break;
            case "5":
                System.out.println("Box 5" + " " + state);
                break;

        }
    }

    private void statsIncrease(boolean state, String multiplier, int newValue, String buffType){
        if(state) {
            valueContainer.setValue(multiplier, newValue);
            ressourceDrain(multiplier, buffType);
        }
        else {
            valueContainer.setValue(multiplier, 1);
        }

    }

    private void ressourceDrain(String multiplier, String buffType){
        Thread ressourceDrain = new Thread() {
            public void run() {
                while (valueContainer.getValue(multiplier) != 1) {
                    try {
                        Platform.runLater(()->{
                            try {
                                switch(multiplier){
                                    case"goldMultiplier":
                                        if(frame.getRessourcePanel().getEnergy() <= 10)
                                            throw new GameControllerException("Not enough energy");
                                        else
                                            frame.getRessourcePanel().setEnergyLabel(-10);
                                        break;
                                    case"energyMultiplier":
                                        if(frame.getRessourcePanel().getTime() <= 0.1)
                                            throw new GameControllerException("Not enough time reduction");
                                        else
                                            frame.getRessourcePanel().setTimeLabel(-0.1);
                                        break;
                                    case"strengthMultiplier":
                                        if(frame.getRessourcePanel().getGold() <= 1000)
                                            throw new GameControllerException("Not enough gold");
                                        else
                                            frame.getRessourcePanel().setGoldLabel(-1000);
                                        break;
                                    case"timeMultiplier":
                                        if(frame.getRessourcePanel().getStrength() <= 100)
                                            throw new GameControllerException("Not enough strength");
                                        else
                                            frame.getRessourcePanel().setStrengthLabel(-100);
                                        break;
                                }

                            }catch(GameControllerException e) {
                                System.out.println(e.getMessage());
                                frame.getBuffPanel().getBuffs(buffType).setSelected(false);
                                valueContainer.setValue(multiplier, 1);
                            }
                        });
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ressourceDrain.start();
    }
}
