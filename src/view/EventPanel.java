package view;

import controller.EventPanelController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
        buttons.put("autoAttack", autoAttack = new Button("AutoAttack"));
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

        String buttonStyle = "-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Monospace\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 14px;\n" +
                "    -fx-padding: 5 5 5 5;" +
                "    -fx-hover: dropshadow( one-pass-box , rgba(0,0,0,0.9) , 1, 0.0 , 0 , 1 );";

        String hoverStyle = "-fx-background-color: \n" +
                "        #b2b8c4,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Monospace\";\n" +
                "    -fx-text-fill: linear-gradient(green, #d0d0d0);\n" +
                "    -fx-font-size: 14px;\n" +
                "    -fx-padding: 5 5 5 5;" +
                "    -fx-hover: dropshadow( one-pass-box , rgba(0,0,0,0.9) , 1, 0.0 , 0 , 1 );";

        String tooltipStyle = "-fx-font-family: normal bold 2; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;";

        Tooltip tp = new Tooltip();
        tp.setStyle(tooltipStyle);

        Set keys = buttons.keySet();
        for (Iterator i = keys.iterator(); i.hasNext();) {
            String s = (String) i.next();
            Button b = (Button) buttons.get(s);
            b.setStyle(buttonStyle);


            tp.setText("Get correct info");

            b.setTooltip(tp);

            b.setMinHeight(35);
            b.setMinWidth(100);

            b.setOnMouseEntered(e->{
                b.setStyle(hoverStyle);
            });

            b.setOnMouseExited(e->{
                b.setStyle(buttonStyle);
            });

        }





    }

    private GridPane addButtons(){
        GridPane grid = new GridPane();
        VBox.setVgrow(grid, Priority.ALWAYS);
        //grid.setStyle("-fx-background-color: #c2b1c1;");

        grid.setPadding(new Insets(20,20,20,20));
        grid.setHgap(30);
        grid.setVgap(15);

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

        b6.setVisible(false);


        BackgroundImage img = new BackgroundImage(new Image("background.jpg", grid.getWidth(), grid.getHeight(),true,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);


        grid.setBackground(new Background(img));

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
