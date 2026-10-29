package com.example.bank;

public class BankovyUcet {
    private double suma;

    public BankovyUcet(double s){
        if(!Double.isFinite(s) || s < 0) throw new IllegalArgumentException();
        suma = s;
    }

    public synchronized void vloz(double s){
        if(!Double.isFinite(s) || s <= 0 || !Double.isFinite(suma+s)){
            throw new IllegalArgumentException();
        }
        suma += s;
    }

    public synchronized boolean vyber(double s){
        if(!Double.isFinite(s) || s <= 0) throw new IllegalArgumentException();
        if(suma < s) return false;
        suma -= s;
        return true;
    }

    public synchronized double getSuma(){
        return suma;
    }
}
