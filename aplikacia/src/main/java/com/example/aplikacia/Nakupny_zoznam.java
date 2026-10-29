package com.example.aplikacia;

public class Nakupny_zoznam {
    private String nazov;
    private int kusy;

    public Nakupny_zoznam(String nazov, int kusy) {
        this.nazov = nazov;
        this.kusy = kusy;
    }

    public String getNazov() {
        return nazov;
    }

    public int getKusy() {
        return kusy;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public void setKusy(int kusy) {
        this.kusy = kusy;
    }

    @Override
    public String toString() {
        return nazov + " - " + kusy + " ks";
    }
}
