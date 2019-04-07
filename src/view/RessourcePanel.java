package view;

import controller.RessourcePanelController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.interfaces.GameEngine;
import view.observers.GameEngineCallbackGUI;

public class RessourcePanel extends GridPane {

    private double gold;
    private double energy;
    private double strength;

    private Label goldLabel;
    private Label energyLabel;
    private Label strengthLabel;

    public RessourcePanel(){
        this.setStyle("-fx-background-color: #c4c4c4;");
        this.setPadding(new Insets(0,5,5,15));
        addLabels();
    }

    private void addLabels(){

        goldLabel = new Label("Gold: ");
        goldLabel = GameEngineCallbackGUI.setLabelStyle(goldLabel, 20);

        strengthLabel = new Label("Dps: ");
        strengthLabel = GameEngineCallbackGUI.setLabelStyle(strengthLabel, 20);

        energyLabel = new Label("Energy: ");
        energyLabel = GameEngineCallbackGUI.setLabelStyle(energyLabel, 20);


        this.add(goldLabel, 0,0);
        this.add(strengthLabel, 0,1);
        this.add(energyLabel, 0, 2);
    }


    public void addListeners(MainFrame frame, GameEngine engine){
        RessourcePanelController listener = new RessourcePanelController(frame, engine);
    }

    public void setGoldLabel(double gold){
        this.gold += gold;
        this.goldLabel.setText("Gold: " + this.gold);
    }

    public void setEnergyLabel(double energy){
        this.energy += energy;
        this.energyLabel.setText("Energy: " + this.energy);
    }

    public void setStrengthLabel(double strength){
        this.strength += strength;
        this.strengthLabel.setText("Dps: " + this.strength);
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
}
