package com.example.geometricshapes;

import javafx.scene.paint.Color;

public class Squarel extends GeometricShapes{
    public Squarel(int x, int y, int r, Color f){
        super(x, y, r, f);
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
        if((getLayoutY()>300) || (getLayoutY()<10)){
            rychlost = -rychlost;
        }
        setLayoutY(getLayoutY()+rychlost);
        vykresli();
    }
}
