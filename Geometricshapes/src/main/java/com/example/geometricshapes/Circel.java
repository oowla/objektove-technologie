package com.example.geometricshapes;

import javafx.scene.paint.Color;

public class Circel extends GeometricShapes{
    private double uhol=0;
    private double startX;
    private double startY;

    public Circel(int x, int y, int r, Color f){
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
        setLayoutX(startX+20*Math.cos(uhol));
        setLayoutY(startY+20*Math.sin(uhol));
        uhol+=0.1;
        vykresli();
    }

    @Override
    public void posunutie() {
        startY += 30;
        setLayoutY(getLayoutY() + 30);
    }
}
