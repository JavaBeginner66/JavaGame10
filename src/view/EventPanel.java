package view;

import controller.EventPanelController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.interfaces.GameEngine;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;



public class EventPanel extends BorderPane {

    private Button attack, b2, b3, autoAttack, b4, b5, b6, b7, b8, b9, b10, b11;
    private TreeMap<String, Button> buttons;

    private HBox barPane;


    public EventPanel(){
        /* Put buttons in a TreeSet */
        hashButtons();
        /* Style buttons */
        setButtonStyle(buttons);
        /* Create ScrollPane and set it in center */
        ScrollPane sp = new ScrollPane();
        this.setCenter(sp);
        /* Progressbars will spawn under button window */
        this.setBottom(barPane = new HBox());

        this.setPadding(new Insets(20,20,20,20));

        barPane.setMinHeight(40);
        barPane.setPadding(new Insets(20,0,0,0));

        /* Buttons GridPane is set inside of ScrollPane */
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
        buttons.put("autoAttack", autoAttack = new Button("Unlock AutoAttack"));
        buttons.put("b4", b4 = new Button("b4"));
        buttons.put("b5", b5 = new Button("b5"));
        buttons.put("b6", b6 = new Button("b6"));
        buttons.put("b7", b7 = new Button("b7"));
        buttons.put("b8", b8 = new Button("b8"));
        buttons.put("b9", b9 = new Button("b9"));
        buttons.put("b10", b10 = new Button("b10"));
        buttons.put("b11", b11 = new Button("b11"));
    }

    public ProgressBar addProgressBar(){

        ProgressBar bar = new ProgressBar();
        bar.setProgress(0);

        barPane.getChildren().add(bar);

        return bar;
    }


    public void addListeners(MainFrame frame, GameEngine engine){
        EventPanelController listener = new EventPanelController(frame, engine);
        attack.setOnAction(listener);
        b2.setOnAction(listener);
        b3.setOnAction(listener);
        autoAttack.setOnAction(listener);
    }

    private void setButtonStyle(TreeMap buttons){

        Tooltip tp = new Tooltip();
        tp.setStyle("-fx-font-family: normal bold 2; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;");

        Set keys = buttons.keySet();
        for (Iterator i = keys.iterator(); i.hasNext();) {
            String s = (String) i.next();
            Button b = (Button) buttons.get(s);
            b.setStyle("-fx-font: 16 arial; -fx-base: #2c64e8; -fx-font-size: 14px;" +
                    "-fx-text-fill:white; -fx-font-family: \"Arial Narrow\";" +
                    "    -fx-font-weight: bold;");
            tp.setText("Get correct info");

            b.setTooltip(tp);

            b.setMinHeight(35);
            b.setMinWidth(150);
        }



    }

    private GridPane addButtons(){
        GridPane grid = new GridPane();
        VBox.setVgrow(grid, Priority.ALWAYS);
        //grid.setStyle("-fx-background-color: #c2b1c1;");

        grid.setPadding(new Insets(20,20,20,20));
        grid.setHgap(8);
        grid.setVgap(8);

        grid.add(attack, 0,0);
        grid.add(b2, 0,1);
        grid.add(b3, 0,2);
        grid.add(autoAttack, 0,3);
        grid.add(b4, 0,4);
        grid.add(b5, 0,5);
        grid.add(b6, 0,6);
        grid.add(b7, 0,7);
        grid.add(b8, 0,8);
        grid.add(b9, 1,5);
        grid.add(b10, 2,5);
        grid.add(b11, 3,5);



        return grid;
    }

    /* Getters and setters */

    public HBox getBarPane() {
        return barPane;
    }

    public Button getButtons(String key){
        return buttons.get(key);
    }
}
