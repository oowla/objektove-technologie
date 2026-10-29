module com.example.aplikacia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aplikacia to javafx.fxml;
    exports com.example.aplikacia;
}