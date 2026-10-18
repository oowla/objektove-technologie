package com.example.geometricshapes;

import javafx.scene.paint.Color;

public class Rectangel extends GeometricShapes{
    public Rectangel(int x, int y, int r, Color f){
        super(x, y, r, f);
        posun();
    }
    @Override
    protected void vykresli(){
        gc.clearRect(0,0, (getWidth()/2), getHeight());
        gc.setFill(Farba);
        gc.fillRect(0,0, (getWidth()/2), getHeight());

    }

    @Override
    protected void posun(){
        if((getLayoutX()>300) || (getLayoutX()<10)){
            rychlost = -rychlost;
        }
        setLayoutX(getLayoutX()+rychlost);
        vykresli();
    }
}
