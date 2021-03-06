package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.GameEngineImplement;
import model.interfaces.GameEngine;
import view.MainFrame;
import view.observers.GameEngineCallbackGUI;

public class IdleGameClient extends Application {

    @Override
    public void start(Stage stage) {


        /* View */
        final MainFrame mainFrame = new MainFrame();
        /* Model and GUI callback*/
        final GameEngine gameEngine = new GameEngineImplement(new GameEngineCallbackGUI(mainFrame));
        /* Listeners */
        mainFrame.addListeners(gameEngine);



        Scene scene = new Scene(mainFrame, 1000, 600);
        stage.setTitle("IdleGame");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args){
        launch(args);
    }
}
