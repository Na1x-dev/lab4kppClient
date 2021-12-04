package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.client.StaticFieldsAndRequests.*;

public class SignInController {

    @FXML
    private AnchorPane LogInPane;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private Button signinButton;

    @FXML
    private Label logLabel;

    @FXML
    void onSignEnter(MouseEvent event) {
        signinButton.setStyle("-fx-background-color: #7C809B;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        signinButton.setTextFill(Paint.valueOf("WHITE"));
    }

    @FXML
    void onSignExit(MouseEvent event) {
        signinButton.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16;");
        signinButton.setTextFill(Paint.valueOf("BLACK"));
    }

    @FXML
    void onLoginEnter(MouseEvent event) {
        loginButton.setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        loginButton.setTextFill(Paint.valueOf("BLACK"));
    }

    @FXML
    void onLoginExit(MouseEvent event) {
        loginButton.setStyle("-fx-background-color: #7C809B; -fx-background-radius: 16;");
        loginButton.setTextFill(Paint.valueOf("WHITE"));
    }

    @FXML
    void onSignPress(MouseEvent event) {
        signinButton.setStyle("-fx-background-color: #6B698A;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        signinButton.setTextFill(Paint.valueOf("WHITE"));
    }

    @FXML
    void onSignRelease(MouseEvent event) {
        signinButton.setStyle("-fx-background-color: #7C809B;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        signinButton.setTextFill(Paint.valueOf("WHITE"));
    }

    @FXML
    void onLoginPress(MouseEvent event) {
        loginButton.setStyle("-fx-background-color: #dddddd;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        loginButton.setTextFill(Paint.valueOf("BLACK"));
    }

    @FXML
    void onLoginRelease(MouseEvent event) {
        loginButton.setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        loginButton.setTextFill(Paint.valueOf("BLACK"));
    }


    @FXML
    void backToLogIn(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            Stage stage = new Stage();
            stage.setTitle("LogIn");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            //System.Logger logger = System.Logger.getLogger(getClass().getName());
            //logger.log(System.Logger.Level.SEVERE, "Failed to create new Window.", e);
            System.out.println("Failed to create new Window");
        }
    }

    @FXML
    void signInAction(ActionEvent event) {
        mainUser = new User();
        bill = new Bill();
        mainUser.setUsername(loginField.getText());
        mainUser.setPassword(passwordField1.getText());
        mainUser.setFirstname(firstnameField.getText());
        mainUser.setLastname(lastnameField.getText());
        mainUser.setEmail(emailField.getText());
        bill.generateBankData(mainUser.getUsername());
        try {
            if (validation(mainUser)) {
                User chekUser = postResponseUser(mainUser);
                postResponseBill(bill);
                if (!chekUser.getUsername().equals(mainUser.getUsername())) {
                    System.out.println("Success");
                    openMainWindow(event);
                    loginField.setStyle("-fx-background-color: #7C809B; -fx-background-radius: 16; -fx-border-radius: 14; -fx-border-width: 1.5; -fx-border-color: #000000; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #ffffff");
                } else {
                    loginField.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;-fx-prompt-text-fill: #ffffff");
                    logLabel.setText("Никнейм уже используется");
                }
            }
            else {
                logLabel.setText("Введите другие данные");
            }
        } catch (IOException e) {
            logLabel.setText("Сервер недоступен");
        }
    }

    @FXML
    void onChangeBlueField(KeyEvent event){
        TextField someField = (TextField) event.getSource();
        logLabel.setText("");
        someField.setStyle("-fx-background-color: #7C809B; -fx-background-radius: 16; -fx-border-radius: 14; -fx-border-width: 1.5; -fx-border-color: #000000; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #ffffff");
    }

    @FXML
    void onChangeWhiteField(KeyEvent event){
        TextField someField = (TextField) event.getSource();
        logLabel.setText("");
        someField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    void openMainWindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("mainWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
            Stage stage = new Stage();
            stage.setTitle("Main Window");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            //System.Logger logger = System.Logger.getLogger(getClass().getName());
            //logger.log(System.Logger.Level.SEVERE, "Failed to create new Window.", e);
            System.out.println("Failed to create new Window");
        }
    }

    boolean validation(User user) {
        Pattern firstnamePattern = Pattern.compile(".{4,}");
        Pattern lastnamePattern = Pattern.compile(".{4,}");
        Pattern usernamePattern = Pattern.compile(".{4,}"); //[a-z0-9_-]{3,16}
        Pattern passwordPattern = Pattern.compile(".{4,}"); //(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}
        Pattern emailPattern = Pattern.compile(".{4,}"); //[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}

        Matcher firstnameMatcher = firstnamePattern.matcher(user.getFirstname());
        Matcher lastnameMatcher = lastnamePattern.matcher(user.getLastname());
        Matcher usernameMatcher = usernamePattern.matcher(user.getUsername());
        Matcher passwordMatcher = passwordPattern.matcher(user.getPassword());
        Matcher emailMatcher = emailPattern.matcher(user.getEmail());

        boolean isValidated = true;

        if (firstnameMatcher.matches()) {
            firstnameField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        } else {
            isValidated = false;
            //firstnameField.clear();
            firstnameField.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000; -fx-text-fill: #ffffff");
        }
        if (lastnameMatcher.matches()) {
            lastnameField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        } else {
            isValidated = false;
            //lastnameField.clear();
            lastnameField.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000; -fx-text-fill: #ffffff");
        }
        if (usernameMatcher.matches()) {
            loginField.setStyle("-fx-background-color: #7C809B; -fx-background-radius: 16; -fx-border-radius: 14; -fx-border-width: 1.5; -fx-border-color: #000000; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #ffffff");
        } else {
            isValidated = false;
            //loginField.clear();
            loginField.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000; -fx-text-fill: #ffffff");
        }
        if (passwordMatcher.matches()) {
            if (mainUser.getPassword().equals(passwordField2.getText())) {
                passwordField1.setStyle("-fx-background-color: #7C809B; -fx-background-radius: 16; -fx-border-radius: 14; -fx-border-width: 1.5; -fx-border-color: #000000; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #ffffff");
                passwordField2.setStyle("-fx-background-color: #7C809B; -fx-background-radius: 16; -fx-border-radius: 14; -fx-border-width: 1.5; -fx-border-color: #000000; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #ffffff");
            } else {
                //passwordField.clear();
                passwordField1.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000; -fx-text-fill: #ffffff");
                passwordField2.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000; -fx-text-fill: #ffffff");
                isValidated = false;
            }
        } else {
            isValidated = false;
            //passwordField.clear();
            passwordField1.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;-fx-text-fill: #ffffff");
            passwordField2.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;-fx-text-fill: #ffffff");
        }
        if (emailMatcher.matches()) {
            emailField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        } else {
            isValidated = false;
            //emailField.clear();
            emailField.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000; -fx-text-fill: #ffffff");
        }
        return isValidated;
    }

}


