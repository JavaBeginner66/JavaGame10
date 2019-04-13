package view;

import controller.RessourcePanelController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.interfaces.GameEngine;
import view.observers.GameEngineCallbackGUI;

public class RessourcePanel extends GridPane {

    private double gold;
    private double energy;
    private double strength;
    private double time;

    private Label goldLabel;
    private Label energyLabel;
    private Label strengthLabel;
    private Label timeLabel;

    private final String goldImg = "gold.png";
    private final String strengthImg = "strength.png";
    private final String energyImg = "energy.png";
    private final String timeImg = "time.png";

    public RessourcePanel(){
        this.setStyle("-fx-background-color: #c4c4c4;");
        this.setPadding(new Insets(0,5,5,15));
        addLabels();
    }

    private void addLabels(){

        goldLabel = new Label("", new ImageView(new Image(goldImg)));
        goldLabel = GameEngineCallbackGUI.setLabelStyle(goldLabel, 20);

        strengthLabel = new Label("", new ImageView(new Image(strengthImg)));
        strengthLabel = GameEngineCallbackGUI.setLabelStyle(strengthLabel, 20);

        energyLabel = new Label("", new ImageView(new Image(energyImg)));
        energyLabel = GameEngineCallbackGUI.setLabelStyle(energyLabel, 20);

        timeLabel = new Label("", new ImageView(new Image(timeImg)));
        timeLabel = GameEngineCallbackGUI.setLabelStyle(timeLabel, 20);


        this.add(goldLabel, 0,0);
        this.add(strengthLabel, 0,1);
        this.add(energyLabel, 0, 2);
        this.add(timeLabel, 0, 3);

        this.setVgap(5);
        this.setHgap(5);
    }


    public void addListeners(MainFrame frame, GameEngine engine){
        RessourcePanelController listener = new RessourcePanelController(frame, engine);
    }

    public void setGoldLabel(double gold){
        this.gold += gold;
        this.goldLabel.setText(" " + GameEngineCallbackGUI.round(this.gold, 2));
    }

    public void setEnergyLabel(double energy){
        this.energy += energy;
        this.energyLabel.setText(" " + GameEngineCallbackGUI.round(this.energy, 2));
    }

    public void setStrengthLabel(double strength){
        this.strength += strength;
        this.strengthLabel.setText(" " + GameEngineCallbackGUI.round(this.strength, 2));
    }

    public void setTimeLabel(double time){
        this.time += time;
        this.timeLabel.setText(" " + GameEngineCallbackGUI.round(this.time, 2) + " %");
    }

    public double getGold() {
        return gold;
    }

    public double getEnergy() {
        return energy;
    }

    public double getStrength() {
        return strength;
    }

    public double getTime() {
        return time;
    }

    public String getGoldImg() {
        return goldImg;
    }

    public String getStrengthImg() {
        return strengthImg;
    }

    public String getEnergyImg() {
        return energyImg;
    }

    public String getTimeImg() {
        return timeImg;
    }
}
