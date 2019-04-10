package controller;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
                goldIncrease(state);
                break;
            case "2":
                energyIncrease(state);
                break;
            case "3":
                System.out.println("Box 3" + " " + state);
                break;
            case "4":
                System.out.println("Box 4" + " " + state);
                break;
            case "5":
                System.out.println("Box 5" + " " + state);
                break;

        }
    }

    private void goldIncrease(boolean state){
        if(state) {
            valueContainer.setValue("goldMultiplier", 5);

            Thread energyDrain = new Thread() {
                public void run() {
                    while (valueContainer.getValue("goldMultiplier") != 1) {
                        try {
                            Platform.runLater(()->{
                                try {
                                    if(frame.getRessourcePanel().getEnergy() <= 0){
                                        throw new GameControllerException("Not enough energy");
                                    }else{
                                        frame.getRessourcePanel().setEnergyLabel(-1);
                                    }
                                }catch(GameControllerException e) {
                                    System.out.println(e.getMessage());
                                    frame.getBuffPanel().getBuffs("buff1").setSelected(false);
                                    valueContainer.setValue("goldMultiplier", 1);
                                }
                            });
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            energyDrain.start();
        }
        else {
            valueContainer.setValue("goldMultiplier", 1);
        }

    }

    private void energyIncrease(boolean state){
        // Bruke 1 thread for flere metoder?
    }
}
