package com.example.geometricshapes;

import javafx.scene.paint.Color;

public class Rectangel extends GeometricShapes{
    public Rectangel(int x, int y, int r, Color f){
        super(x, y, r, f);
    }
    @Override
    protected void vykresli(){
        gc.clearRect(0,0, getWidth(), getHeight());
        gc.setFill(Farba);
        gc.fillRect(0,0, getWidth(), getHeight());

    }
}
