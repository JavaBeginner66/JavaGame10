package view;

import javafx.scene.control.CheckBox;

public class MyCheckbox extends CheckBox {

    private int id;
    private String description;

    MyCheckbox(int id, String description){
        this.id = id;
        this.description = description;
    }

    public int getID() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
