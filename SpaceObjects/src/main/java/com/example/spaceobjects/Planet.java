package com.example.spaceobjects;

import javafx.scene.paint.Color;

public class Planet extends SpaceObject {
    private double uhol = 0;
    private int startX;
    private int startY;

    public Planet(int x, int y, int r, Color f){
        super(x, y, r, f);
        startX = x;
        startY = y;
        posun();
    }

    @Override
    protected void vykresli(){
        gc.clearRect(0,0, getWidth(), getHeight());
        gc.setFill(Farba);
        gc.fillOval(0,0, getWidth(), getHeight());
    }

    @Override
    protected void posun(){
        setLayoutX(startX + 30 * Math.cos(uhol));
        setLayoutY(startY + 30 * Math.sin(uhol));
        uhol += 0.02 * rychlost;
        vykresli();
    }
}
