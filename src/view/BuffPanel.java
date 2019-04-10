package view;

import controller.BuffPanelController;
import controller.SidePanelController;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.interfaces.GameEngine;

import java.util.TreeMap;

public class BuffPanel extends GridPane {

    private CheckBox buff1;
    private CheckBox buff2;
    private CheckBox buff3;
    private CheckBox buff4;
    private CheckBox buff5;

    private TreeMap<String, CheckBox> buffs;

    public BuffPanel(){

        this.setStyle("-fx-background-color: #c1c9c9;");
        this.setPadding(new Insets(10,15,5,5));
        this.setHgap(8);
        this.setVgap(8);
        addBoxes();
        mapBoxes();

    }

    private void addBoxes(){
        Label l = new Label("Buffs");

        this.add(l, 0 , 0);
        this.add(buff1 = new CheckBox("1"), 0,1);
        this.add(buff2 = new CheckBox("2"), 0,2);
        this.add(buff3 = new CheckBox("3"), 0, 3);
        this.add(buff4 = new CheckBox("4"), 0, 4);
        this.add(buff5 = new CheckBox("5"), 0, 5);
    }

    private void mapBoxes(){
        buffs = new TreeMap<>();
        buffs.put("buff1", buff1);
        buffs.put("buff2", buff2);
        buffs.put("buff3", buff3);
        buffs.put("buff4", buff4);
        buffs.put("buff5", buff5);

    }

    public void addListeners(MainFrame frame, GameEngine engine){
        BuffPanelController listener = new BuffPanelController(frame, engine);
        buff1.setOnAction(listener);
        buff2.setOnAction(listener);
        buff3.setOnAction(listener);
        buff4.setOnAction(listener);
        buff5.setOnAction(listener);
    }

    public CheckBox getBuffs(String key){
        return buffs.get(key);
    }

    public TreeMap<String, CheckBox> getBuffs() {
        return buffs;
    }
    
}
