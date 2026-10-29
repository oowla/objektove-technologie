package com.example.pocitanie;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML private TextField input;
    @FXML private TextArea faktorial;
    @FXML private TextArea mocnina;
    @FXML private TextArea fibonacci;
    @FXML private ProgressBar progressFaktorial;
    @FXML private ProgressBar progressMocnina;
    @FXML private ProgressBar progressFibonacci;
    @FXML private Button runButton;
    private int pocetVlakien;

    @FXML
    protected void run(){
        int cislo;
        try {
            cislo = Integer.parseInt(input.getText().trim());
            if(cislo < 0 || cislo > 1000){
                throw new NumberFormatException();
            }
        } catch(NumberFormatException e){
            faktorial.setText("Zadaj cele cislo od 0 do 1000.");
            mocnina.clear();
            fibonacci.clear();
            progressFaktorial.setProgress(0);
            progressMocnina.setProgress(0);
            progressFibonacci.setProgress(0);
            return;
        }

        faktorial.clear();
        mocnina.clear();
        fibonacci.clear();
        progressFaktorial.setProgress(0);
        progressMocnina.setProgress(0);
        progressFibonacci.setProgress(0);
        runButton.setDisable(true);
        pocetVlakien = 3;

        Faktorial vlakno1 = new Faktorial(cislo, faktorial, progressFaktorial, () -> dokoncene());
        Mocnina vlakno2 = new Mocnina(cislo, mocnina, progressMocnina, () -> dokoncene());
        Fibonacci vlakno3 = new Fibonacci(cislo, fibonacci, progressFibonacci, () -> dokoncene());
        vlakno1.start();
        vlakno2.start();
        vlakno3.start();
    }

    private void dokoncene(){
        pocetVlakien--;
        if(pocetVlakien == 0){
            runButton.setDisable(false);
        }
    }
}
