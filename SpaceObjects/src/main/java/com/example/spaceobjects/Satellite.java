package com.example.spaceobjects;

import javafx.scene.paint.Color;

public class Satellite extends SpaceObject {
    public Satellite(int x, int y, int r, Color f){
        super(x, y, r, f);
        posun();
    }

    @Override
    protected void vykresli(){
        gc.clearRect(0,0, getWidth(), getHeight());
        gc.setFill(Farba);
        gc.fillRect(0,10, getWidth(), 20);
    }

    @Override
    protected void posun(){
        if((getLayoutY() >= 360 && rychlost > 0) || (getLayoutY() <= 70 && rychlost < 0)){
            rychlost = -rychlost;
        }
        setLayoutY(Math.max(70, Math.min(360, getLayoutY()+rychlost)));
        vykresli();
    }

    public void posunutie(){
        setLayoutX(getLayoutX()+20);
    }
}
