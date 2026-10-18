module com.example.spaceobjects {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.example.spaceobjects to javafx.fxml;
    exports com.example.spaceobjects;
}
