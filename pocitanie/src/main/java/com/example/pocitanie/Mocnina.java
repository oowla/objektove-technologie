package com.example.pocitanie;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

public class Mocnina extends Thread {
    private int cislo;
    private TextArea vypis;
    private ProgressBar progress;
    private Runnable dokoncenie;

    public Mocnina(int n, TextArea t, ProgressBar p, Runnable koniec){
        cislo = n;
        vypis = t;
        progress = p;
        dokoncenie = koniec;
        setDaemon(true);
    }

    @Override
    public void run(){
        try {
            if(cislo == 0){
                Platform.runLater(() -> {
                    vypis.appendText("0^2 = 0\n");
                    progress.setProgress(1);
                });
            }
            for(int i=1; i<=cislo; i++){
                long vysledok = (long)i*i;
                String riadok = i + "^2 = " + vysledok + "\n";
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
