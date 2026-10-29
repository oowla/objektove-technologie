module com.example.pocitanie {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pocitanie to javafx.fxml;
    exports com.example.pocitanie;
}