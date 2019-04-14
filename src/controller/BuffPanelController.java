package controller;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import model.ValueContainer;
import model.interfaces.GameEngine;
import view.MainFrame;
import view.MyCheckbox;

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
        MyCheckbox s = (MyCheckbox)event.getSource();
        int box = s.getID();
        boolean state = s.isSelected();

        switch(box){
            case 0:
                statsIncrease(state,"goldMultiplier",  "buff1");
                break;
            case 1:
                statsIncrease(state, "energyMultiplier","buff2");
                break;
            case 2:
                statsIncrease(state, "strengthMultiplier",  "buff3");
                break;
            case 3:
                statsIncrease(state,"timeMultiplier",  "buff4");
                break;

        }
    }

    private void statsIncrease(boolean state, String multiplier, String buffType){
        if(state) {
            valueContainer.setValue(multiplier, frame.getBuffPanel().getBuffValue());
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
                                        if(frame.getRessourcePanel().getEnergy() <= frame.getBuffPanel().getEnergyDrain())
                                            throw new GameControllerException("Not enough energy");
                                        else
                                            frame.getRessourcePanel().setEnergyLabel(-frame.getBuffPanel().getEnergyDrain());
                                        break;
                                    case"energyMultiplier":
                                        if(frame.getRessourcePanel().getTime() <= frame.getBuffPanel().getTimeDrain())
                                            throw new GameControllerException("Not enough time reduction");
                                        else
                                            frame.getRessourcePanel().setTimeLabel(-frame.getBuffPanel().getTimeDrain());
                                        break;
                                    case"strengthMultiplier":
                                        if(frame.getRessourcePanel().getGold() <= frame.getBuffPanel().getGoldDrain())
                                            throw new GameControllerException("Not enough gold");
                                        else
                                            frame.getRessourcePanel().setGoldLabel(-frame.getBuffPanel().getGoldDrain());
                                        break;
                                    case"timeMultiplier":
                                        if(frame.getRessourcePanel().getStrength() <= frame.getBuffPanel().getStrengthDrain())
                                            throw new GameControllerException("Not enough strength");
                                        else
                                            frame.getRessourcePanel().setStrengthLabel(-frame.getBuffPanel().getStrengthDrain());
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
