package view;

import javafx.scene.layout.BorderPane;
import model.interfaces.GameEngine;

public class MainFrame extends BorderPane {

    private MenuPanel toolBar;
    private TextPanel textPanel;
    private EventPanel eventPanel;
    private SidePanel sidePanel;


    public MainFrame(){

        this.setTop(toolBar = new MenuPanel());
        this.setBottom(textPanel = new TextPanel());
        this.setCenter(eventPanel = new EventPanel());
        this.setRight(sidePanel = new SidePanel());


    }

    public void addListeners(GameEngine engine){
        toolBar.addListeners(this, engine);
        textPanel.addListeners(this, engine);
        eventPanel.addListeners(this, engine);
        sidePanel.addListeners(this, engine);
    }

    /* Get methods for panels */

    public MenuPanel getToolBar() {
        return toolBar;
    }

    public TextPanel getTextPanel() {
        return textPanel;
    }

    public EventPanel getEventPanel() {
        return eventPanel;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }
}
