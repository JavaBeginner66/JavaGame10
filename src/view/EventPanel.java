package view;

import controller.EventPanelController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.interfaces.GameEngine;



public class EventPanel extends BorderPane {

    private Button b1, b2;

    private HBox barPane;


    public EventPanel(){
        this.setCenter(addButtons());
        this.setBottom(barPane = new HBox());

        this.setPadding(new Insets(20,20,20,20));

    }

    public ProgressBar addProgressBar(){

        ProgressBar bar = new ProgressBar();
        bar.setProgress(0);

        barPane.getChildren().add(bar);

        return bar;
    }

    private GridPane addButtons(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20,20,20,20));
        grid.setHgap(5);
        grid.setVgap(5);

        grid.add(b1 = new Button("Button1"), 0,0);
        grid.add(b2 = new Button("Button2"), 1,0);

        return grid;
    }


    public void addListeners(MainFrame frame, GameEngine engine){
        EventPanelController listener = new EventPanelController(frame, engine);
        b1.setOnAction(listener);
        b2.setOnAction(listener);
    }

    /* Getters and setters */

    public HBox getBarPane() {
        return barPane;
    }
}
