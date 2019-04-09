package view;

import controller.EventPanelController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.ValueContainer;
import model.interfaces.GameEngine;
import view.observers.GameEngineCallbackGUI;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;



public class EventPanel extends BorderPane {

    private MyButton task, strength2, strength1, strength3, energy1, energy2, energy3, income1, income2, income3, time1, time2, time3,
            passive2, passive1, passive3, passive4;
    private TreeMap<String, MyButton> buttons;

    private VBox barContainer;
    private HBox barDecription;
    private HBox barPane;


    public EventPanel(){
        /* Put buttons in a TreeSet */
        mapButtons();
        /* Style buttons */
        setButtonStyle(buttons);
        /* Create ScrollPane and set it in center */
        ScrollPane sp = new ScrollPane();
        this.setCenter(sp);
        /* Progressbars will instanciate under button window */
        this.setBottom(barContainer = new VBox());
        barDecription = new HBox();
        barPane = new HBox();
        barContainer.getChildren().addAll(barDecription, barPane);
        this.setPadding(new Insets(20,20,10,20));

        barContainer.setMinHeight(50);
        /* Buttons GridPane is set inside of ScrollPane */
        sp.setContent(addButtons());
        sp.setPannable(true);

    }

    private void mapButtons(){

        buttons = new TreeMap<>();


        buttons.put("task", task = new MyButton("task", null, 0, 0, "Starts a task that unlocks income1 and gives you " + ValueContainer.taskValue + " gold on completion" + "\n" + "Task takes " + ValueContainer.getInstance().getValue("task")/100 + " sec"));
        buttons.put("income1", income1 = new MyButton("income1", null, 1, 100,"Starts a task that unlocks income2 and gives you " + ValueContainer.incomeValue1 + " gold on completion"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("income1")/100 + " sec"));
        buttons.put("income2", income2 = new MyButton("income2", null, 2, 500,"Starts a task that unlocks income3 and gives you " + ValueContainer.incomeValue2 + " gold on completion"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("income2")/100 + " sec"));
        buttons.put("income3", income3 = new MyButton("income3", null, 3, 800, "Gives you "+ ValueContainer.incomeValue3  + " gold on completion"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("income3")/100 + " sec"));
        buttons.put("strength1", strength1 = new MyButton("strength1", null, 4, 500, "Starts a task that unlocks strength2 and gives you " + ValueContainer.strengthValue1 + " strength on completion"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("strength1")/100 + " sec"));
        buttons.put("strength2", strength2 = new MyButton("strength2", null, 5, 3000, "Starts a task that unlocks strength3 and gives you " + ValueContainer.strengthValue2 + " strength on completion"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("strength2")/100 + " sec"));
        buttons.put("strength3", strength3 = new MyButton("strength3", null, 6, 8000, "Gives you " + ValueContainer.strengthValue3  + " strength on completion"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("strength3")/100 + " sec"));
        buttons.put("energy1", energy1 = new MyButton("energy1", null, 7, 1000, "Starts a task that unlocks energy2 and gives you " + ValueContainer.energyValue1 + " energy on completion"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("energy1")/100 + " sec"));
        buttons.put("energy2", energy2 = new MyButton("energy2", null, 8, 5000, "Starts a task that unlocks energy3 and gives you " + ValueContainer.energyValue2 + " energy on completion"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("energy2")/100 + " sec"));
        buttons.put("energy3", energy3 = new MyButton("energy3", null, 9, 15000, "Gives you " + ValueContainer.energyValue3  + " energy on completion"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("energy3")/100 + " sec"));
        buttons.put("time1", time1 = new MyButton("time1", null, 10, 50000, "Starts a task that unlocks time2 and gives all tasks " + GameEngineCallbackGUI.round((1-ValueContainer.timeCut1),2) + " % time reduction"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("time1")/100 + " sec"));
        buttons.put("time2", time2 = new MyButton("time2", null, 11, 300000, "Starts a task that unlocks time3 and gives all tasks " + GameEngineCallbackGUI.round((1-ValueContainer.timeCut2),2) + " % time reduction"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("time2")/100 + " sec"));
        buttons.put("time3", time3 = new MyButton("time3", null, 12, 1000000, "Gives all tasks " + GameEngineCallbackGUI.round((1-ValueContainer.timeCut3),2)  + " % time reduction"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("time3")/100 + " sec"));
        buttons.put("passive1", passive1 = new MyButton("passive1", null, 13, 300000, "Starts a task that automates income1 on completion"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("passive1")/100 + " sec"));
        buttons.put("passive2", passive2 = new MyButton("passive2", null, 14, 1000000, "Starts a task that automates strength1 on completion"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("passive2")/100 + " sec"));
        buttons.put("passive3", passive3 = new MyButton("passive3", null, 15, 5000000, "Starts a task that automates time1 on completion"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("passive3")/100 + " sec"));
        buttons.put("passive4", passive4 = new MyButton("passive4", null, 16, 10000000, "Starts a task that automates energy1 on completion"+ "\n" + "Task takes " + ValueContainer.getInstance().getValue("passive4")/100 + " sec"));


    }

    public ProgressBar addProgressBar(){

        ProgressBar bar = new ProgressBar();
        bar.setProgress(0);

        barPane.getChildren().add(bar);

        return bar;
    }
    public Label addBarLabel(String desc){
        Label label = new Label(desc);
        barDecription.getChildren().add(label);

        return label;
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


        Set keys = buttons.keySet();
        for (Iterator i = keys.iterator(); i.hasNext();) {
            String s = (String) i.next();
            MyButton b = (MyButton) buttons.get(s);
            b.setStyle(GameEngineCallbackGUI.buttonStyle);
            b.setFocusTraversable(false);

            Tooltip tpText = new Tooltip();
            tpText.setText("Cost: " + b.getCost() + " gold " + "\n" + b.getDescription());
            tpText.setStyle(GameEngineCallbackGUI.tooltipStyle);

            b.setTooltip(tpText);

            b.setMinHeight(35);
            b.setMinWidth(100);

            b.setOnMouseEntered(e->{
                b.setStyle(GameEngineCallbackGUI.hoverStyle);
            });

            b.setOnMouseExited(e->{
                b.setStyle(GameEngineCallbackGUI.buttonStyle);
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
    public HBox getDescriptionPane() {
        return barDecription;
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

    public MyButton getButton(String key){
        return buttons.get(key);
    }
}
