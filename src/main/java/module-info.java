module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires javafx.graphics;


    opens com.example.client to javafx.fxml;
    exports com.example.client;
}