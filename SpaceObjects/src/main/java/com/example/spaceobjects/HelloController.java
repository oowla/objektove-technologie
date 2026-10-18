package com.example.spaceobjects;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML private Pane root;
    private Planet planet;
    private Asteroid asteroid;
    private Satellite satellite;

    @FXML
    protected void planet(){
        planet = new Planet((int)(Math.random()*500)+30, (int)(Math.random()*230)+100, 5, Color.BLUE);
        root.getChildren().add(planet);
    }

    @FXML
    protected void asteroid(){
        asteroid = new Asteroid((int)(Math.random()*560), (int)(Math.random()*290)+70, 5, Color.GRAY);
        root.getChildren().add(asteroid);
    }

    @FXML
    protected void satellite(){
        satellite = new Satellite((int)(Math.random()*560), (int)(Math.random()*290)+70, 5, Color.GREEN);
        root.getChildren().add(satellite);
    }

    @FXML
    protected void change_color(){
        for(int i=0; i<root.getChildren().size(); i++){
            Node d = root.getChildren().get(i);
            if(d instanceof Asteroid) ((Asteroid)d).zmenaFarby(Color.RED);
        }
    }

    @FXML
    protected void move(){
        for(int i=0; i<root.getChildren().size(); i++){
            Node d = root.getChildren().get(i);
            if(d instanceof Satellite) ((Satellite)d).posunutie();
        }
    }
}
