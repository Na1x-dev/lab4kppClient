package com.example.client;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MainWindowController {
    User mainUser;

    @FXML
    private AnchorPane LogInPane;

    @FXML
    private TextField loginField;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordField;

    @FXML
    void loginAction(ActionEvent event) {
        System.out.println("hohol");
        ((Node)(event.getSource())).getScene().getWindow().getOnShown();
    }
}
