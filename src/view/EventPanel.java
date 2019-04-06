package view;

import controller.EventPanelController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.interfaces.GameEngine;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;



public class EventPanel extends BorderPane {

    private Button task, strength2, strength1, strength3, energy1, energy2, energy3, income1, income2, income3, time1, time2, time3,
            passive2, passive1, passive3, passive4;
    private TreeMap<String, Button> buttons;

    private HBox barPane;


    public EventPanel(){
        /* Put buttons in a TreeSet */
        mapButtons();
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

    private void mapButtons(){

        buttons = new TreeMap<>();

        buttons.put("task", task = new Button("task"));
        buttons.put("strength2", strength2 = new Button("strength2"));
        buttons.put("strength1", strength1 = new Button("strength1"));
        buttons.put("strength3", strength3 = new Button("strength3"));
        buttons.put("energy1", energy1 = new Button("energy1"));
        buttons.put("energy2", energy2 = new Button("energy2"));
        buttons.put("energy3", energy3 = new Button("energy3"));
        buttons.put("income1", income1 = new Button("income1"));
        buttons.put("income2", income2 = new Button("income2"));
        buttons.put("income3", income3 = new Button("income3"));
        buttons.put("time1", time1 = new Button("time1"));
        buttons.put("time2", time2 = new Button("time2"));
        buttons.put("time3", time3 = new Button("time3"));
        buttons.put("passive2", passive2 = new Button("passive2"));
        buttons.put("passive1", passive1 = new Button("passive1"));
        buttons.put("passive3", passive3 = new Button("passive3"));
        buttons.put("passive4", passive4 = new Button("passive4"));


    }

    public ProgressBar addProgressBar(){

        ProgressBar bar = new ProgressBar();
        bar.setProgress(0);

        barPane.getChildren().add(bar);

        return bar;
    }


    public void addListeners(MainFrame frame, GameEngine engine){
        EventPanelController listener = new EventPanelController(frame, engine);

        task.setOnAction(listener);
        income1.setOnAction(listener);
        income2.setOnAction(listener);
        income3.setOnAction(listener);
        passive1.setOnAction(listener);
        passive2.setOnAction(listener);
        passive3.setOnAction(listener);
        passive4.setOnAction(listener);
        time1.setOnAction(listener);
        time2.setOnAction(listener);
        time3.setOnAction(listener);
        energy1.setOnAction(listener);
        energy2.setOnAction(listener);
        energy3.setOnAction(listener);
        strength1.setOnAction(listener);
        strength2.setOnAction(listener);
        strength3.setOnAction(listener);

    }

    private void setButtonStyle(TreeMap buttons){

        String buttonStyle = "-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 4em;\n" +
                    "-fx-min-width: 100px; " +
                    "-fx-min-height: 50px; " +
                    "-fx-max-width: 100px; " +
                    "-fx-max-height: 50px;" +
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
                "    -fx-background-radius: 4em;\n" +
                    "-fx-min-width: 100px; " +
                    "-fx-min-height: 50px; " +
                    "-fx-max-width: 100px; " +
                    "-fx-max-height: 50px;" +
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
        grid.setVgap(40);

        Button invis1 = new Button();
        Button invis2 = new Button();
        Button invis3 = new Button();
        Button invis4 = new Button();
        Button invis5 = new Button();
        Button invis6 = new Button();
        Button invis7 = new Button();

        grid.add(invis1, 0,0);
        grid.add(invis2, 1,0);
        grid.add(invis3, 2,0);
        grid.add(invis4, 2,1);
        grid.add(invis5, 2,5);
        grid.add(invis6, 4,1);
        grid.add(invis7, 4,5);

        grid.add(strength3, 3,0);
        grid.add(strength2, 3,1);
        grid.add(strength1, 3,2);
        grid.add(task, 3,3);
        grid.add(energy1, 3,4);
        grid.add(energy2, 3,5);
        grid.add(energy3, 3,6);

        grid.add(income1, 2,3);
        grid.add(income2, 1,3);
        grid.add(income3, 0,3);

        grid.add(time1, 4,3);
        grid.add(time2, 5,3);
        grid.add(time3, 6,3);

        grid.add(passive2, 1, 1);
        grid.add(passive1, 1, 5);
        grid.add(passive3, 5, 1);
        grid.add(passive4, 5, 5);

        /* Disabling buttons */
        buttonState(true, income1, income2, income3, energy1, energy2, energy3, strength1, strength2, strength3, time1, time2, time3, passive1, passive2, passive3, passive4);

        /* Creating space by adding invisible buttons */
        invis1.setVisible(false);
        invis2.setVisible(false);
        invis3.setVisible(false);
        invis4.setVisible(false);
        invis5.setVisible(false);
        invis6.setVisible(false);
        invis7.setVisible(false);


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

    public void buttonState(boolean state, Button... buttons){
        for (Button button : buttons) {
            button.setDisable(state);
        }
    }

    public void buttonState(boolean state, String... key){
        for (String aKey : key) {
            this.buttons.get(aKey).setDisable(state);
        }
    }

    public Button getButton(String key){
        return buttons.get(key);
    }
}
