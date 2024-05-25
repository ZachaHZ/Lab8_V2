module ucr.lab {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens ucr.lab to javafx.fxml;
    exports ucr.lab;
    exports controller;
    opens controller to javafx.fxml;
}