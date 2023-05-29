module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires javax.ws.rs;

    opens com.example.app to javafx.fxml;
    exports com.example.app;
}