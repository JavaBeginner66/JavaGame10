package view;

import javafx.scene.layout.BorderPane;
import model.interfaces.GameEngine;

public class MainFrame extends BorderPane {

    private MenuPanel toolBar;
    private RessourcePanel ressourcePanel;
    private EventPanel eventPanel;
    private SidePanel sidePanel;
    private BuffPanel buffPanel;


    public MainFrame(){

        this.setTop(toolBar = new MenuPanel());
        this.setBottom(ressourcePanel = new RessourcePanel());
        this.setCenter(eventPanel = new EventPanel());
        this.setRight(sidePanel = new SidePanel());
        this.setLeft(buffPanel = new BuffPanel());


    }

    public void addListeners(GameEngine engine){
        toolBar.addListeners(this, engine);
        ressourcePanel.addListeners(this, engine);
        eventPanel.addListeners(this, engine);
        sidePanel.addListeners(this, engine);
        buffPanel.addListeners(this, engine);
    }

    /* Get methods for panels */

    public MenuPanel getToolBar() {
        return toolBar;
    }

    public RessourcePanel getRessourcePanel() {
        return ressourcePanel;
    }

    public EventPanel getEventPanel() {
        return eventPanel;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }
}
