module com.example.geometricshapes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.geometricshapes to javafx.fxml;
    exports com.example.geometricshapes;
}