package com.example.bank;

import javafx.application.Platform;

public class Zamestnanec extends Thread {
    private int identifikator;
    private BankovyUcet ucet;
    private double suma;
    private boolean vklad;
    private HelloController controller;

    public Zamestnanec(int id, BankovyUcet u, double s, boolean v, HelloController c){
        identifikator = id;
        ucet = u;
        suma = s;
        vklad = v;
        controller = c;
        setDaemon(true);
    }

    @Override
    public void run(){
        synchronized(ucet){
            String sprava;
            if(vklad){
                ucet.vloz(suma);
                sprava = "vklad " + String.format("%.2f", suma) + " EUR";
            } else if(ucet.vyber(suma)){
                sprava = "vyber " + String.format("%.2f", suma) + " EUR";
            } else {
                sprava = "vyber " + String.format("%.2f", suma) + " EUR zamietnuty - nedostatok penazi";
            }
            String riadok = "Zamestnanec " + identifikator + ": " + sprava
                    + ", zostatok: " + String.format("%.2f", ucet.getSuma()) + " EUR\n";
            Platform.runLater(() -> controller.dokoncene(riadok));
        }
    }
}
