module com.example.democlientserver {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires javafx.base;

    opens com.example.democlientserver to javafx.fxml,javafx.base;
    opens Actors  to javafx.fxml, javafx.base;
    exports com.example.democlientserver;
}
