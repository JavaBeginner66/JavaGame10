package view.observers;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import view.MainFrame;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GameEngineCallbackGUI {

    private MainFrame mainFrame;

    public static String progressBarStyle =
            "-fx-text-box-border: black;\n" +
            "  -fx-control-inner-background: #38f7e1;" +
            "   -fx-accent: #ed2f2f;";

    public static String buttonStyle = "-fx-background-color: \n" +
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

    public static String hoverStyle = "-fx-background-color: \n" +
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

    public static String tooltipStyle = "-fx-font-family: normal bold 2; "
            + "-fx-base: #AE3522; "
            + "-fx-text-fill: orange;";

    public final static String goldImg = "gold.png";
    public final static String strengthImg = "strength.png";
    public final static String energyImg = "energy.png";
    public final static String timeImg = "time.png";

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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }



}
