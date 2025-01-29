module com.weaverstudios {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.weaverstudios to javafx.fxml;
    exports com.weaverstudios;
}
