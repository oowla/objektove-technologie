package com.example.pocitanie;

import java.math.BigInteger;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

public class Faktorial extends Thread {
    private int cislo;
    private TextArea vypis;
    private ProgressBar progress;
    private Runnable dokoncenie;

    public Faktorial(int n, TextArea t, ProgressBar p, Runnable koniec){
        cislo = n;
        vypis = t;
        progress = p;
        dokoncenie = koniec;
        setDaemon(true);
    }

    @Override
    public void run(){
        BigInteger vysledok = BigInteger.ONE;
        try {
            if(cislo == 0){
                Platform.runLater(() -> {
                    vypis.appendText("0! = 1\n");
                    progress.setProgress(1);
                });
            }
            for(int i=1; i<=cislo; i++){
                vysledok = vysledok.multiply(BigInteger.valueOf(i));
                String riadok = i + "! = " + vysledok + "\n";
                double priebeh = (double)i/cislo;
                Platform.runLater(() -> {
                    vypis.appendText(riadok);
                    progress.setProgress(priebeh);
                });
                Thread.sleep(100);
            }
        } catch(InterruptedException e){
            Thread.currentThread().interrupt();
        } finally {
            Platform.runLater(dokoncenie);
        }
    }
}
