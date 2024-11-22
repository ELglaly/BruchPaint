module org.example.assginment10 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.assginment10 to javafx.fxml;
    exports org.example.assginment10;
    exports com.paint;
    opens com.paint to javafx.fxml;
}