package com.example.aplikacia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML private TextField textField;
    @FXML private ListView<Nakupny_zoznam> list;
    @FXML private Spinner<Integer> kusy;
    @FXML private Label polozky;

    private ObservableList<Nakupny_zoznam> zoznam =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        kusy.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1)
        );

        list.setItems(zoznam);
        aktualizujPocet();

        list.getSelectionModel().selectedItemProperty()
                .addListener((obs, stara, nova) -> {
                    if (nova != null) {
                        textField.setText(nova.getNazov());
                        kusy.getValueFactory().setValue(nova.getKusy());
                    }
                });
    }

    @FXML
    private void pridaj() {
        String nazov = textField.getText().trim();

        if (nazov.isEmpty()) return;

        int pocet = kusy.getValue();

        Nakupny_zoznam p = new Nakupny_zoznam(nazov, pocet);
        zoznam.add(p);

        textField.clear();
        aktualizujPocet();
    }

    @FXML
    private void uprav() {
        Nakupny_zoznam vybrana =
                list.getSelectionModel().getSelectedItem();

        if (vybrana == null) return;

        String nazov = textField.getText().trim();
        if (nazov.isEmpty()) return;

        vybrana.setNazov(nazov);
        vybrana.setKusy(kusy.getValue());

        list.refresh();
    }

    @FXML
    private void zmaz() {
        Nakupny_zoznam vybrana =
                list.getSelectionModel().getSelectedItem();

        if (vybrana == null) return;

        zoznam.remove(vybrana);

        textField.clear();
        aktualizujPocet();
    }

    private void aktualizujPocet() {
        polozky.setText("Celkový počet položiek: " + zoznam.size());
    }
}