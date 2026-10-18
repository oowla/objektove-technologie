package com.example.geometricshapes;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public abstract class GeometricShapes extends Canvas {
    protected GraphicsContext gc;
    protected Color Farba;
    private Timeline casovac;
    protected int rychlost;

    public GeometricShapes(int x, int y, int r, Color f){
        super(40,40);
        Farba = f; rychlost = r;
        setLayoutX(x); setLayoutY(y);
        gc = getGraphicsContext2D();
        casovac = new Timeline(new KeyFrame(Duration.millis(100), e-> posun()));
        casovac.setCycleCount(Animation.INDEFINITE);
        casovac.play();
    }

    protected abstract void posun();

    protected abstract void vykresli();

    public void zmenaFarby(Color f) {
        Farba = f;
    }

    public void posunutie(){
        setLayoutY(getLayoutY()+30);
    }
}