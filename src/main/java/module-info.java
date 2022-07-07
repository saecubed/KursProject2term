module com.example.kurspr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.kurspr to javafx.fxml;
    exports com.example.kurspr;
}