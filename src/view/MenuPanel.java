package view;

import controller.MenuPanelController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.interfaces.GameEngine;

public class MenuPanel extends MenuBar {

    private Menu menu, help;
    private MenuItem exit, save, load, newGame, about, shortCuts, description;

    public MenuPanel(){

        /* Menus */
        menu = new Menu("File");
        help = new Menu("Help");

        /* Menu items */
        exit = new MenuItem("Exit Game");
        save = new MenuItem("Save Game");
        load = new MenuItem("Load Game");
        newGame = new MenuItem("New Game");

        about = new MenuItem("About");
        shortCuts = new MenuItem("Shortcuts");
        description = new MenuItem("Description");

        /* Construct menu */
        menu.getItems().addAll(save, load, newGame, exit);
        help.getItems().addAll(about, shortCuts, description);
        this.getMenus().addAll(menu, help);

        this.setStyle("-fx-background-color: #c4c4c4;");
    }


    public void addListeners(MainFrame frame, GameEngine engine){
        MenuPanelController listener = new MenuPanelController(frame, engine);
        exit.setOnAction(listener);
        save.setOnAction(listener);
        load.setOnAction(listener);
        newGame.setOnAction(listener);
        about.setOnAction(listener);
        shortCuts.setOnAction(listener);
        description.setOnAction(listener);
    }
}
