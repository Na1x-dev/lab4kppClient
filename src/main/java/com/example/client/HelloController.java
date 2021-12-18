package com.example.client;


import com.example.client.StaticFieldsAndRequests;
import com.example.client.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;

import static com.example.client.StaticFieldsAndRequests.*;


public class HelloController {
    private static final Logger log = Logger.getLogger(HelloController.class);
    @FXML
    private AnchorPane LogInPane;

    @FXML
    private TextField loginField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signinButton;

    @FXML
    private TextField passwordField;

    @FXML
    private Label logLoginLabel;

    @FXML
    void blueButtonRelease(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        someButton.setTextFill(Paint.valueOf("BLACK"));
    }

    @FXML
    void blueButtonPress(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #dddddd;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        someButton.setTextFill(Paint.valueOf("BLACK"));
    }

    @FXML
    void blueButtonExit(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #7C809B; -fx-background-radius: 16;");
        someButton.setTextFill(Paint.valueOf("WHITE"));
    }

    @FXML
    void blueButtonEnter(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        someButton.setTextFill(Paint.valueOf("BLACK"));
    }

    @FXML
    void openSignInWindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("SignIn.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            Stage stage = new Stage();
            stage.setTitle("SignIn");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            log.info("окно SignIn успешно создано");
        } catch (IOException e) {
            log.error("окно SignIn не создано");
        }
    }

    public void openMainWindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("mainWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
            Stage stage = new Stage();
            stage.setTitle("Main Window");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            log.info("окно mainWindow успешно создано");
        } catch (IOException e) {
            log.error("окно mainWindow не создано");
        }
    }

    @FXML
    void onChangeField() {
        logLoginLabel.setText("");
        loginField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        passwordField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
    }

    @FXML
    void loginAction(ActionEvent event) {
        mainUser = new User();
        mainUser.setUsername(loginField.getText());
        mainUser.setPassword(passwordField.getText());
        User checkUser = getResponseUser(mainUser);
        if(checkUser == null) {
            logString = "Сервер недоступен";
        }
        else if (checkUser.getPassword().equals(mainUser.getPassword()) && !mainUser.getUsername().equals("") && !mainUser.getPassword().equals("")) {
            passwordField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
            mainUser = checkUser;
            openMainWindow(event);
        }
        else {
            passwordField.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
            loginField.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
            logString = "Неверные логин и(или) пароль";
        }
        logLoginLabel.setText(logString);
    }
}
