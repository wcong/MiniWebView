package org.wcong.webexplore;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;

/**
 * Created by wcong on 2016/5/5.
 */
public class InputBar extends VBox {

    private final WebEngine webEngine;

    private TextField inputField;

    private Button button;

    public InputBar(WebEngine web) {
        super();
        this.webEngine = web;
        inputField = new TextField();
        getChildren().add(inputField);
        button = new Button("前往");
        getChildren().add(button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                webEngine.load(inputField.getText());
            }
        });
    }

    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        double ifWidth = w * 0.8;
        inputField.setMinWidth(ifWidth);
        button.minWidth(w - ifWidth);
        layoutInArea(inputField, 0, 0, ifWidth, h, 0, HPos.CENTER, VPos.CENTER);
        layoutInArea(button, ifWidth, 0, w - ifWidth, h, 0, HPos.CENTER, VPos.CENTER);
    }
}
