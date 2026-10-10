
package com.example.robot;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML private Pane root;
    private robot r;

    @FXML
    protected void initialize() {
        r = new robot();
        root.getChildren().add(0, r);

        r.widthProperty().bind(root.widthProperty());
        r.heightProperty().bind(root.heightProperty());

        root.setFocusTraversable(true);

        root.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.W) {
                        r.manualnyPohyb(0, -10);
                    } else if (event.getCode() == KeyCode.A) {
                        r.manualnyPohyb(-10, 0);
                    } else if (event.getCode() == KeyCode.S) {
                        r.manualnyPohyb(0, 10);
                    } else if (event.getCode() == KeyCode.D) {
                        r.manualnyPohyb(10, 0);
                    }
                });
            }
        });

        r.setOnMouseClicked(event -> {
            r.kliknutie(event.getX(), event.getY());
            root.requestFocus();
        });
    }
}
