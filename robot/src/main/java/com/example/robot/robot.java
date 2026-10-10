
package com.example.robot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class robot extends Canvas {
    private GraphicsContext gc;
    private Timeline t;

    private double x = 100;
    private double y = 100;
    private double rychlost = 3;
    private double uhol = 0;

    private final double sirka = 80;
    private final double vyska = 50;

    private boolean automatika = false;

    public robot() {
        super(600, 400);
        gc = getGraphicsContext2D();

        t = new Timeline(new KeyFrame(Duration.millis(20), e -> pohniSa()));
        t.setCycleCount(Timeline.INDEFINITE);

        widthProperty().addListener((obs, oldVal, newVal) -> {
            upravHranice();
            vykresli();
        });

        heightProperty().addListener((obs, oldVal, newVal) -> {
            upravHranice();
            vykresli();
        });

        vykresli();
    }

    public void manualnyPohyb(double dx, double dy) {
        if (automatika) return;

        if (dx > 0) uhol = 0;
        else if (dx < 0) uhol = 180;
        else if (dy > 0) uhol = 90;
        else if (dy < 0) uhol = 270;

        x += dx;
        y += dy;

        upravHranice();
        vykresli();
    }

    private void pohniSa() {
        x += rychlost;

        double polovicaSirky = sirka / 2;

        if (x >= getWidth() - polovicaSirky) {
            x = getWidth() - polovicaSirky;
            rychlost = -Math.abs(rychlost);
        } else if (x <= polovicaSirky) {
            x = polovicaSirky;
            rychlost = Math.abs(rychlost);
        }

        uhol = rychlost > 0 ? 0 : 180;
        vykresli();
    }

    public void kliknutie(double mx, double my) {
        double polovicaSirky = (uhol == 90 || uhol == 270) ? vyska / 2 : sirka / 2;
        double polovicaVysky = (uhol == 90 || uhol == 270) ? sirka / 2 : vyska / 2;

        if (mx >= x - polovicaSirky && mx <= x + polovicaSirky &&
                my >= y - polovicaVysky && my <= y + polovicaVysky) {

            automatika = !automatika;

            if (automatika) {
                rychlost = (uhol == 180) ? -3 : 3;
                uhol = rychlost > 0 ? 0 : 180;
                upravHranice();
                t.play();
            } else {
                t.stop();
            }

            vykresli();
        }
    }

    private void upravHranice() {
        double polovicaSirky = (uhol == 90 || uhol == 270) ? vyska / 2 : sirka / 2;
        double polovicaVysky = (uhol == 90 || uhol == 270) ? sirka / 2 : vyska / 2;

        if (getWidth() >= polovicaSirky * 2) {
            x = Math.max(polovicaSirky, Math.min(x, getWidth() - polovicaSirky));
        } else {
            x = getWidth() / 2;
        }

        if (getHeight() >= polovicaVysky * 2) {
            y = Math.max(polovicaVysky, Math.min(y, getHeight() - polovicaVysky));
        } else {
            y = getHeight() / 2;
        }
    }

    private Color farbaSmeru() {
        if (uhol == 0) return Color.DEEPSKYBLUE;
        if (uhol == 90) return Color.LIMEGREEN;
        if (uhol == 180) return Color.ORANGE;
        return Color.HOTPINK;
    }

    private void vykresli() {
        gc.clearRect(0, 0, getWidth(), getHeight());

        gc.save();
        gc.translate(x, y);
        gc.rotate(uhol);

        gc.setFill(Color.GRAY);
        gc.fillRect(-sirka / 2, -vyska / 2, sirka, vyska);

        gc.setFill(farbaSmeru());
        gc.fillRect(sirka / 2 - 15, -vyska / 2 + 5, 10, vyska - 10);

        gc.setStroke(Color.DARKGRAY);
        gc.setLineWidth(2);
        gc.strokeRect(-sirka / 2, -vyska / 2, sirka, vyska);

        gc.restore();
    }
}