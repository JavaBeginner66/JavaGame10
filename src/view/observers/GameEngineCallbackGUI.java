package view.observers;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import view.MainFrame;

public class GameEngineCallbackGUI {

    private MainFrame mainFrame;

    public GameEngineCallbackGUI(MainFrame frame){
        this.mainFrame = frame;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public static Label setLabelStyle(Label text, int size){
        text.setFont(Font.font("Monospace",
                FontWeight.BOLD, FontPosture.REGULAR, size));
        return text;
    }

}
