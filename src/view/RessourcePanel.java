package view;

import controller.RessourcePanelController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.interfaces.GameEngine;
import view.observers.GameEngineCallbackGUI;

public class RessourcePanel extends GridPane {

    private long gold;
    private long energy;

    private Label goldLabel;
    private Label energyLabel;

    public RessourcePanel(){
        this.setStyle("-fx-background-color: #c4c4c4;");
        this.setPadding(new Insets(0,5,5,15));
        addLabels();
    }

    private void addLabels(){

        goldLabel = new Label("Gold: ");
        goldLabel = GameEngineCallbackGUI.setLabelStyle(goldLabel, 20);

        energyLabel = new Label("Energy: ");
        energyLabel = GameEngineCallbackGUI.setLabelStyle(energyLabel, 20);


        this.add(goldLabel, 0,0);
        this.add(energyLabel, 0, 1);
    }


    public void addListeners(MainFrame frame, GameEngine engine){
        RessourcePanelController listener = new RessourcePanelController(frame, engine);
    }

    public void setGoldLabel(long gold){
        this.gold += gold;
        this.goldLabel.setText("Gold: " + this.gold);
    }

    public void setMana(){
        this.energy += energy;
        this.energyLabel.setText("Energy: " + this.energy);
    }


}
