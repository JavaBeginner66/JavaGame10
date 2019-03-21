package view.observers;

import view.MainFrame;

public class GameEngineCallbackGUI {

    private MainFrame mainFrame;

    public GameEngineCallbackGUI(MainFrame frame){
        this.mainFrame = frame;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void calculateGoldGained(long time){
        long gold = time * 10;
        mainFrame.getRessourcePanel().setGoldLabel(gold);
    }
}
