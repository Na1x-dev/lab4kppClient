package com.example.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInController {
    User mainUser;


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
    void onSignEnter(MouseEvent event) {
        signinButton.setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        signinButton.setTextFill(Paint.valueOf("BLACK"));
    }

    @FXML
    void onSignExit(MouseEvent event) {
        signinButton.setStyle("-fx-background-color: #7C809B; -fx-background-radius: 16;");
        signinButton.setTextFill(Paint.valueOf("WHITE"));
    }

    @FXML
    void onLoginEnter(MouseEvent event) {
        loginButton.setStyle("-fx-background-color: #7C809B;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        loginButton.setTextFill(Paint.valueOf("WHITE"));
    }

    @FXML
    void onLoginExit(MouseEvent event) {
        loginButton.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16;");
        loginButton.setTextFill(Paint.valueOf("BLACK"));
    }

    @FXML
    void onSignPress(MouseEvent event) {
        signinButton.setStyle("-fx-background-color: #dddddd;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        signinButton.setTextFill(Paint.valueOf("BLACK"));
    }

    @FXML
    void onSignRelease(MouseEvent event) {
        signinButton.setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        signinButton.setTextFill(Paint.valueOf("BLACK"));
    }

    @FXML
    void onLoginPress(MouseEvent event) {
        loginButton.setStyle("-fx-background-color: #6B698A;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        loginButton.setTextFill(Paint.valueOf("WHITE"));
    }

    @FXML
    void onLoginRelease(MouseEvent event) {
        loginButton.setStyle("-fx-background-color: #7C809B;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        loginButton.setTextFill(Paint.valueOf("WHITE"));
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

    User parseResponseToUser(HttpPost request, User mainUser) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        request.setEntity(new StringEntity(mainUser.getJson()));
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        User checkUser = new User();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            checkUser = gson.fromJson(result, User.class);
            if(checkUser == null){
                checkUser = new User();
            }
        }
        return checkUser;
    }

    boolean validation(User user) {
        Pattern firstnamePattern = Pattern.compile(".+");
        Pattern lastnamePattern = Pattern.compile(".+");
        Pattern usernamePattern = Pattern.compile(".+"); //[a-z0-9_-]{3,16}
        Pattern passwordPattern = Pattern.compile(".+"); //(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}
        Pattern emailPattern = Pattern.compile(".+"); //[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}

        Matcher firstnameMatcher = firstnamePattern.matcher(user.getFirstname());
        Matcher lastnameMatcher = lastnamePattern.matcher(user.getFirstname());
        Matcher usernameMatcher = usernamePattern.matcher(user.getFirstname());
        Matcher passwordMatcher = passwordPattern.matcher(user.getFirstname());
        Matcher emailMatcher = emailPattern.matcher(user.getFirstname());

        boolean isValidated = true;

        if (firstnameMatcher.matches()) {
            firstnameField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        } else {
            isValidated = false;
            //firstnameField.clear();
            firstnameField.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        }
        if (lastnameMatcher.matches()) {
            lastnameField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        } else {
            isValidated = false;
            //lastnameField.clear();
            lastnameField.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        }
        if (usernameMatcher.matches()) {
            loginField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        } else {
            isValidated = false;
            //loginField.clear();
            loginField.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        }
        if (passwordMatcher.matches()) {
            if (mainUser.getPassword().equals(passwordField2.getText())) {
                passwordField1.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
                passwordField2.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
            } else {
                //passwordField.clear();
                passwordField1.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
                passwordField2.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
            }
        } else {
            isValidated = false;
            //passwordField.clear();
            passwordField1.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        }
        if (emailMatcher.matches()) {
            emailField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        } else {
            isValidated = false;
            //emailField.clear();
            emailField.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        }
        return isValidated;
    }

    @FXML
    void signInAction(ActionEvent event) {

        mainUser = new User();
        mainUser.setUsername(loginField.getText());
        mainUser.setPassword(passwordField1.getText());
        mainUser.setFirstname(firstnameField.getText());
        mainUser.setLastname(lastnameField.getText());
        mainUser.setEmail(emailField.getText());
        HttpPost request = new HttpPost("http://localhost:8080/users");
        try {

            if (validation(mainUser)) {
                //request.setEntity(new StringEntity(mainUser.getJson()));
                User chekUser = parseResponseToUser(request, mainUser);
                System.out.println(chekUser.toString());
                if (!chekUser.getUsername().equals(mainUser.getUsername())) {
                    System.out.println("Success");
                    System.out.println(mainUser.toString());
                    openMainWindow(event);
                    loginField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
                } else {
                    if (chekUser.getUsername().equals(mainUser.getUsername())) {
                        loginField.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
                    }
                    System.out.println("Not success");
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
            loginField.setStyle("-fx-background-color: #ED2; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        }
    }
}


