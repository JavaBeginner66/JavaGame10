package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.GameEngineImplement;
import model.interfaces.GameEngine;
import view.MainFrame;

public class ClickerGameClient extends Application {

    @Override
    public void start(Stage stage) {

        /* Model */
        final GameEngine gameEngine = new GameEngineImplement();
        /* View */
        final MainFrame mainFrame = new MainFrame();
        /* Send listeners */
        mainFrame.addListeners(gameEngine);



        Scene scene = new Scene(mainFrame, 1200, 800);
        stage.setTitle("ClickerGame");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args){
        launch(args);
    }
}
