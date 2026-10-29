package com.example.bank;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML private Label balance;
    @FXML private TextArea transactions;
    @FXML private TextField startingBalance;
    @FXML private TextField amount;
    @FXML private ComboBox<String> type;
    @FXML private Button createButton;
    @FXML private Button runButton;
    @FXML private Button runFiveButton;
    private BankovyUcet ucet;
    private int dalsieId = 1;
    private int pocetVlakien;

    @FXML
    private void initialize(){
        type.getItems().addAll("Vklad", "Vyber");
        type.setValue("Vklad");
        runButton.setDisable(true);
        runFiveButton.setDisable(true);
    }

    private double nacitajSumu(String text){
        double suma = Double.parseDouble(text.trim().replace(',', '.'));
        if(!Double.isFinite(suma)) throw new IllegalArgumentException();
        return suma;
    }

    @FXML
    protected void create_account(){
        if(ucet != null) return;
        try {
            double suma = nacitajSumu(startingBalance.getText());
            ucet = new BankovyUcet(suma);
            balance.setText(String.format("%.2f EUR", ucet.getSuma()));
            transactions.appendText("Ucet vytvoreny: " + String.format("%.2f", ucet.getSuma()) + " EUR\n");
            createButton.setDisable(true);
            startingBalance.setDisable(true);
            runButton.setDisable(false);
            runFiveButton.setDisable(false);
        } catch(IllegalArgumentException e){
            transactions.appendText("Zadaj nezapornu pociatocnu sumu.\n");
        }
    }

    @FXML
    protected void runOne(){
        if(ucet == null || pocetVlakien > 0) return;
        try {
            double suma = nacitajSumu(amount.getText());
            if(suma <= 0) throw new IllegalArgumentException();
            if(type.getValue() == null) throw new IllegalArgumentException();
            pocetVlakien = 1;
            nastavTlacidla(true);
            Zamestnanec zamestnanec = new Zamestnanec(dalsieId++, ucet, suma,
                    type.getValue().equals("Vklad"), this);
            zamestnanec.start();
        } catch(IllegalArgumentException e){
            transactions.appendText("Zadaj kladnu sumu a vyber typ operacie.\n");
        }
    }

    @FXML
    protected void runFive(){
        if(ucet == null || pocetVlakien > 0) return;
        pocetVlakien = 5;
        nastavTlacidla(true);
        Zamestnanec[] zamestnanci = new Zamestnanec[5];
        for(int i=0; i<5; i++){
            double suma = ((int)(Math.random()*10000)+1)/100.0;
            boolean vklad = Math.random() < 0.5;
            zamestnanci[i] = new Zamestnanec(dalsieId++, ucet, suma, vklad,
                    this);
        }
        for(int i=0; i<5; i++){
            zamestnanci[i].start();
        }
    }

    public void dokoncene(String sprava){
        transactions.appendText(sprava);
        balance.setText(String.format("%.2f EUR", ucet.getSuma()));
        pocetVlakien--;
        if(pocetVlakien == 0) nastavTlacidla(false);
    }

    private void nastavTlacidla(boolean vypnute){
        runButton.setDisable(vypnute);
        runFiveButton.setDisable(vypnute);
    }
}
