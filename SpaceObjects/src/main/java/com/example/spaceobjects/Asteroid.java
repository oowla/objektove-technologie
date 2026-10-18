package com.example.spaceobjects;

import javafx.scene.paint.Color;

public class Asteroid extends SpaceObject {
    private int rychlostY;

    public Asteroid(int x, int y, int r, Color f){
        super(x, y, r, f);
        rychlostY = r;
        posun();
    }

    @Override
    protected void vykresli(){
        gc.clearRect(0,0, getWidth(), getHeight());
        gc.setFill(Farba);
        gc.fillRect(0,0, getWidth(), getHeight());
    }

    @Override
    protected void posun(){
        if((getLayoutX() >= 560 && rychlost > 0) || (getLayoutX() <= 0 && rychlost < 0)){
            rychlost = -rychlost;
        }
        if((getLayoutY() >= 360 && rychlostY > 0) || (getLayoutY() <= 70 && rychlostY < 0)){
            rychlostY = -rychlostY;
        }
        setLayoutX(Math.max(0, Math.min(560, getLayoutX()+rychlost)));
        setLayoutY(Math.max(70, Math.min(360, getLayoutY()+rychlostY)));
        vykresli();
    }
}
