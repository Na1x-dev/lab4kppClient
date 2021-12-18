package com.example.client;

import com.example.client.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.client.StaticFieldsAndRequests.*;


public class StoreController {
    private static final Logger log = Logger.getLogger(StoreController.class);
    ArrayList<Component> components = new ArrayList<>();
    ArrayList<Button> buttons = new ArrayList<>();

    @FXML
    private Button button_0;

    @FXML
    private Button button_1;

    @FXML
    private Button button_10;

    @FXML
    private Button button_11;

    @FXML
    private Button button_12;

    @FXML
    private Button button_13;

    @FXML
    private Button button_14;

    @FXML
    private Button button_15;

    @FXML
    private Button button_16;

    @FXML
    private Button button_17;

    @FXML
    private Button button_18;

    @FXML
    private Button button_19;

    @FXML
    private Button button_2;

    @FXML
    private Button button_3;

    @FXML
    private Button button_4;

    @FXML
    private Button button_5;

    @FXML
    private Button button_6;

    @FXML
    private Button button_7;

    @FXML
    private Button button_8;

    @FXML
    private Button button_9;

    @FXML
    private Button backToMainButton;

    // стили для кнопок /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void whiteButtonRelease(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #7C809B; -fx-background-radius: 16; -fx-background-image: url(file:images/" + someButton.getId().substring(7) + ".jpg); -fx-background-repeat: no-repeat; -fx-background-size: 97; -fx-background-position: center center");
        someButton.setTextFill(Paint.valueOf("BLACK"));
    }

    @FXML
    void whiteButtonPress(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #dddddd;-fx-border-width: 2.5; -fx-border-radius: 14; -fx-border-color: #7C809B; -fx-background-radius: 16; -fx-background-image: url(file:images/" + someButton.getId().substring(7) +".jpg); -fx-background-repeat: no-repeat; -fx-background-size: 95; -fx-background-position: center center");
        someButton.setTextFill(Paint.valueOf("BLACK"));
    }

    @FXML
    void whiteButtonExit(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-background-image: url(file:images/" + someButton.getId().substring(7) +".jpg); -fx-background-repeat: no-repeat; -fx-background-size: 100; -fx-background-position: center center");
        someButton.setTextFill(Paint.valueOf("BLACK"));
        }

    @FXML
    void whiteButtonEnter(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #7C809B; -fx-background-radius: 16; -fx-background-image: url(file:images/" + someButton.getId().substring(7) + ".jpg); -fx-background-repeat: no-repeat; -fx-background-size: 97; -fx-background-position: center center");
        someButton.setTextFill(Paint.valueOf("BLACK"));

    }

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

    // методы контроллера /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void backToMain(ActionEvent event) {
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
    void toComponentCard(ActionEvent event) {
        Button someButton = (Button) event.getSource();
        component = components.get(Integer.parseInt(someButton.getId().substring(7)));
        idButton = Integer.parseInt(someButton.getId().substring(7));
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ComponentCard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 700);
            Stage stage = new Stage();
            stage.setTitle("Component Card");
            stage.setScene(scene);
            stage.show();
            log.info("окно ComponentCard успешно создано");
        } catch (IOException e) {
            log.error("окно ComponentCard не создано");
        }
    }

    @FXML
    void initialize(){
        addAllButtons();
        components = (ArrayList<Component>) getResponseComponents();
        for(int i=0; i<buttons.size(); i++){
            buttons.get(i).setText(components.get(i).getNameOfComponent());
            buttons.get(i).setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #7C809B; -fx-background-radius: 16; -fx-background-image: url(file:images/" + i + ".jpg); -fx-background-repeat: no-repeat; -fx-background-size: 100 ; -fx-background-position: center center");
        }

    }

    void addAllButtons(){
        buttons.add(button_0);
        buttons.add(button_1);
        buttons.add(button_2);
        buttons.add(button_3);
        buttons.add(button_4);
        buttons.add(button_5);
        buttons.add(button_6);
        buttons.add(button_7);
        buttons.add(button_8);
        buttons.add(button_9);
        buttons.add(button_10);
        buttons.add(button_11);
        buttons.add(button_12);
        buttons.add(button_13);
        buttons.add(button_14);
        buttons.add(button_15);
        buttons.add(button_16);
        buttons.add(button_17);
        buttons.add(button_18);
        buttons.add(button_19);
    }
}
