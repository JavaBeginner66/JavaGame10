package view;

import controller.RessourcePanelController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.interfaces.GameEngine;
import view.observers.GameEngineCallbackGUI;

public class RessourcePanel extends GridPane {

    private long gold;
    private long mana;

    private Label goldLabel;
    private Label manaLabel;

    public RessourcePanel(){
        this.setStyle("-fx-background-color: #c4c4c4;");
        this.setPadding(new Insets(0,5,5,15));
        addLabels();
    }

    private void addLabels(){

        goldLabel = new Label("Gold: ");
        goldLabel = GameEngineCallbackGUI.setLabelStyle(goldLabel, 20);

        manaLabel = new Label("Mana: ");
        manaLabel = GameEngineCallbackGUI.setLabelStyle(manaLabel, 20);


        this.add(goldLabel, 0,0);
        this.add(manaLabel, 0, 1);
    }


    public void addListeners(MainFrame frame, GameEngine engine){
        RessourcePanelController listener = new RessourcePanelController(frame, engine);
    }

    public void setGoldLabel(long gold){
        this.gold += gold;
        this.goldLabel.setText("Gold: " + this.gold);
    }

    public void setMana(){
        this.mana += mana;
        this.manaLabel.setText("Mana: " + this.mana);
    }


}
