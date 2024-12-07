module com.matrix {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;

    exports com.matrix;
    opens com.matrix to javafx.fxml;
    exports com.matrix.interfaces;
    opens com.matrix.interfaces to javafx.fxml;
}