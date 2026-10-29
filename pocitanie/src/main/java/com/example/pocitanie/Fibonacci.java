package com.example.pocitanie;

import java.math.BigInteger;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

public class Fibonacci extends Thread {
    private int cislo;
    private TextArea vypis;
    private ProgressBar progress;
    private Runnable dokoncenie;

    public Fibonacci(int n, TextArea t, ProgressBar p, Runnable koniec){
        cislo = n;
        vypis = t;
        progress = p;
        dokoncenie = koniec;
        setDaemon(true);
    }

    @Override
    public void run(){
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        try {
            if(cislo == 0){
                Platform.runLater(() -> {
                    vypis.appendText("Pocet clenov: 0\n");
                    progress.setProgress(1);
                });
            }
            for(int i=0; i<cislo; i++){
                String riadok = "F(" + i + ") = " + a + "\n";
                double priebeh = (double)(i+1)/cislo;
                Platform.runLater(() -> {
                    vypis.appendText(riadok);
                    progress.setProgress(priebeh);
                });
                BigInteger dalsi = a.add(b);
                a = b;
                b = dalsi;
                Thread.sleep(100);
            }
        } catch(InterruptedException e){
            Thread.currentThread().interrupt();
        } finally {
            Platform.runLater(dokoncenie);
        }
    }
}
