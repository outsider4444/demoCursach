module com.example.democursach {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
            
    opens com.example.democursach to javafx.fxml;
    exports com.example.democursach;
    exports com.example.democursach.Classes;
    opens com.example.democursach.Classes to javafx.fxml;
}