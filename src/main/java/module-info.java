module com.example.yatzygui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.yatzygui to javafx.fxml;
    exports com.example.yatzygui;
}