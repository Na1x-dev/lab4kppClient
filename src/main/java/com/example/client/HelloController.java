package com.example.client;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import static com.example.client.MainWindowController.mainUser;

public class HelloController {
    //User mainUser;

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
        loginButton.setStyle("-fx-background-color: #dddddd;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        loginButton.setTextFill(Paint.valueOf("BLACK"));
    }

    @FXML
    void onLoginRelease(MouseEvent event) {
        loginButton.setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        loginButton.setTextFill(Paint.valueOf("BLACK"));
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
        } catch (IOException e) {
            System.out.println("Failed to create new Window");
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
        } catch (IOException e) {
            e.printStackTrace();
            //System.Logger logger = System.Logger.getLogger(getClass().getName());
            //logger.log(System.Logger.Level.SEVERE, "Failed to create new Window.", e);
            System.out.println("Failed to create new Windowwww");
        }
    }

    User getResponseUser(User mainUser) throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/users/byUsername/" + mainUser.getUsername());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        User chekUser = new User();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            chekUser = chekUser.parseUserFromJSON(result);
            if(chekUser == null){
                chekUser = new User();
            }
        }
        return chekUser;
    }

    @FXML
    void loginAction(ActionEvent event) {
        mainUser = new User();
        mainUser.setUsername(loginField.getText());
        mainUser.setPassword(passwordField.getText());
        try {
            User chekUser = getResponseUser(mainUser);
            if (chekUser.getPassword().equals(mainUser.getPassword()) && !mainUser.getUsername().equals("") && !mainUser.getPassword().equals("")) {
                System.out.println("Success");
                passwordField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
                mainUser = chekUser;
                openMainWindow(event);
            } else {
                passwordField.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
            }
        } catch (IOException e) {
            e.printStackTrace();
            loginField.setStyle("-fx-background-color: #ED254E; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-width: 2; -fx-border-color: #000000;");
        }

    }

}
