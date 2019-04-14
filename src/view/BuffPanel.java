package view;

import controller.BuffPanelController;
import controller.SidePanelController;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.ValueContainer;
import model.interfaces.GameEngine;
import view.observers.GameEngineCallbackGUI;

import java.util.TreeMap;

public class BuffPanel extends GridPane {

    private CheckBox buff1;
    private CheckBox buff2;
    private CheckBox buff3;
    private CheckBox buff4;

    private double goldDrain = 1000;
    private double energyDrain = 1;
    private double strengthDrain = 10;
    private double timeDrain = 0.1;

    private double buffValue = 10;

    private TreeMap<String, CheckBox> buffs;

    public BuffPanel(){

        this.setStyle("-fx-background-color: #c1c9c9;");
        this.setPadding(new Insets(50,15,5,5));
        this.setHgap(8);
        this.setVgap(15);
        addBoxes();
        mapBoxes();

    }

    private void addBoxes(){

        this.add(buff1 = new MyCheckbox(0, "While buff is active, gold gained is multiplied by: " + ValueContainer.getInstance().getValue("goldMultiplier") + ", but drains " + this.energyDrain + " per second."), 0,0);
        this.add(buff2 = new MyCheckbox(1, "While buff is active, energy gained is multiplied by: "+ ValueContainer.getInstance().getValue("energyMultiplier") + ", but drains " + this.timeDrain + " per second."), 0,1);
        this.add(buff3 = new MyCheckbox(2, "While buff is active, strength gained is multiplied by: "+ ValueContainer.getInstance().getValue("strengthMultiplier") + ", but drains " + this.goldDrain + " per second."), 0, 2);
        this.add(buff4 = new MyCheckbox(3, "While buff is active, time gained is multiplied by: "+ ValueContainer.getInstance().getValue("timeMultiplier") + ", but drains " + this.strengthDrain + " per second."), 0, 3);

        this.add(new ImageView(new Image(GameEngineCallbackGUI.goldImg)), 1,0);
        this.add(new ImageView(new Image(GameEngineCallbackGUI.energyImg)), 1,1);
        this.add(new ImageView(new Image(GameEngineCallbackGUI.strengthImg)), 1,2);
        this.add(new ImageView(new Image(GameEngineCallbackGUI.timeImg)), 1,3);

    }

    private void mapBoxes(){
        buffs = new TreeMap<>();
        buffs.put("buff1", buff1);
        buffs.put("buff2", buff2);
        buffs.put("buff3", buff3);
        buffs.put("buff4", buff4);
    }

    public void addListeners(MainFrame frame, GameEngine engine){
        BuffPanelController listener = new BuffPanelController(frame, engine);
        buff1.setOnAction(listener);
        buff2.setOnAction(listener);
        buff3.setOnAction(listener);
        buff4.setOnAction(listener);
    }

    public CheckBox getBuffs(String key){
        return buffs.get(key);
    }

    public TreeMap<String, CheckBox> getBuffs() {
        return buffs;
    }

    public CheckBox getBuff1() {
        return buff1;
    }

    public double getGoldDrain() {
        return goldDrain;
    }

    public double getEnergyDrain() {
        return energyDrain;
    }

    public double getStrengthDrain() {
        return strengthDrain;
    }

    public double getTimeDrain() {
        return timeDrain;
    }

    public double getBuffValue() {
        return buffValue;
    }
}
