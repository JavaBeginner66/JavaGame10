package view;

import controller.EventPanelController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import model.interfaces.GameEngine;

import java.util.TreeMap;


public class EventPanel extends BorderPane {

    private Button attack, b2, b3, autoAttack;
    private TreeMap<String, Button> buttons;

    private HBox barPane;


    public EventPanel(){
        hashButtons();
        ScrollPane sp = new ScrollPane();
        this.setCenter(sp);
        this.setBottom(barPane = new HBox());
        this.setPadding(new Insets(20,20,20,20));

        barPane.setMinHeight(40);
        barPane.setPadding(new Insets(20,0,0,0));

        sp.setContent(addButtons());
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setPannable(true);
    }

    private void hashButtons(){

        buttons = new TreeMap<>();
        buttons.put("attack", attack = new Button("Attack"));
        buttons.put("b2", b2 = new Button("b2"));
        buttons.put("b3", b3 = new Button("b3"));
        buttons.put("autoAttack", autoAttack = new Button("AutoAttack"));
    }

    public ProgressBar addProgressBar(){

        ProgressBar bar = new ProgressBar();
        bar.setProgress(0);

        barPane.getChildren().add(bar);

        return bar;
    }

    private GridPane addButtons(){
        GridPane grid = new GridPane();
        VBox.setVgrow(grid, Priority.ALWAYS);
        grid.setStyle("-fx-background-color: #c2a1c1;");

        grid.setPadding(new Insets(20,20,20,20));
        grid.setHgap(5);
        grid.setVgap(5);

        grid.add(attack, 0,0);
        grid.add(b2, 0,1);
        grid.add(b3, 0,2);
        grid.add(autoAttack, 0,3);


        autoAttack.setPrefSize(400, 400);
        autoAttack.setDisable(false);

        return grid;
    }


    public void addListeners(MainFrame frame, GameEngine engine){
        EventPanelController listener = new EventPanelController(frame, engine);
        attack.setOnAction(listener);
        //b2.setOnAction(listener);
        //b3.setOnAction(listener);
        autoAttack.setOnAction(listener);
    }

    /* Getters and setters */

    public HBox getBarPane() {
        return barPane;
    }

    public Button getButtons(String key){
        return buttons.get(key);
    }
}
