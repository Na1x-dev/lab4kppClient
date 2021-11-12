module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;


    opens com.example.client to javafx.fxml;
    exports com.example.client;
}