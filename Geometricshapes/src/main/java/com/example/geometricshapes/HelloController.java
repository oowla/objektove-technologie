package com.example.geometricshapes;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML private Pane root;
    private Rectangel rectangel;
    private Squarel squarel;
    private Circel circel;

    @FXML
    protected void square() {
        squarel = new Squarel((int)(Math.random() * 320),(int)(Math.random() * 240),5, Color.RED);
        root.getChildren().add(squarel);
    }

    @FXML
    protected void rectangle() {
        rectangel = new Rectangel((int)(Math.random() * 320),(int)(Math.random() * 240),5, Color.GREEN);
        root.getChildren().add(rectangel);
    }

    @FXML
    protected void circle() {
        circel = new Circel((int)(Math.random() * 320),(int)(Math.random() * 240),5, Color.BLUE);
        root.getChildren().add(circel);
    }

    @FXML
    protected void change_color() {
        for(int i=1; i<root.getChildren().size(); i++){
            Node d = root.getChildren().get(i);
            if (d instanceof GeometricShapes) ((GeometricShapes)d).zmenaFarby(Color.BLUE);
        }
    }

    @FXML
    protected void move() {
        for(int i=1; i<root.getChildren().size(); i++){
            Node d = root.getChildren().get(i);
            if (d instanceof GeometricShapes) ((GeometricShapes)d).posunutie();
        }
    }
}
