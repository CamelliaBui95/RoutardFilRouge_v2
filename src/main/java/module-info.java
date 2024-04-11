module fr.routardfilrouge.routard {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires static lombok;
    requires spring.security.crypto;

    opens fr.routardfilrouge.routard to javafx.fxml;
    exports fr.routardfilrouge.routard;
    exports fr.routardfilrouge.routard.controllers;
    opens fr.routardfilrouge.routard.controllers to javafx.fxml;
}