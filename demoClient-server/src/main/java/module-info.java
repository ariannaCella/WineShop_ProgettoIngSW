module com.example.democlientserver {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.democlientserver to javafx.fxml;
    exports com.example.democlientserver;
}