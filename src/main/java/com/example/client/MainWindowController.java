package com.example.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.client.StaticFieldsAndRequests.*;

public class MainWindowController {
    private static final Logger log = Logger.getLogger(MainWindowController.class);

    @FXML
    private Button storeButton;

    @FXML
    private AnchorPane LogInPane;

    @FXML
    private Button backToLogInButton;

    @FXML
    private Label balance;

    @FXML
    private Label cardName;

    @FXML
    private Label cardNumber;

    @FXML
    private Label cardValidity;

    @FXML
    private Button lockButton;

    @FXML
    private AnchorPane transactionsField;

    @FXML
    private Label ibanLabel;

    @FXML
    private Label isActiveLabel;

    @FXML
    private Label firstnameLabel;

    @FXML
    private Label lastnameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label emailLabel;

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
    void toStore(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Store.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
            Stage stage = new Stage();
            stage.setTitle("Store");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            log.info("окно Store успешно создано");
        } catch (IOException e) {
            log.error("окно Store не создано");
        }
    }

    @FXML
    void backToLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            Stage stage = new Stage();
            stage.setTitle("LogIn");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            log.info("окно hello-view успешно создано");
        } catch (IOException e) {
            log.error("окно hello-view не создано");
        }
    }

    @FXML
    void lockUnlock(ActionEvent event) {
        if(bill.getManualBlock()){
            bill.setManualBlock(false);
            lockButton.setText("Заблокировать");
        }
        else if (!bill.getManualBlock()){
            bill.setManualBlock(true);
            lockButton.setText("Разблокировать");
        }
        putResponseBill(bill);
        refresh();
        renderTransactions();
    }

    @FXML
    private void initialize() {
        refresh();
        renderTransactions();
    }

    @FXML
    void plusMoney(){
        bill.setBalance(bill.getBalance()+50);
        putResponseBill(bill);
        refresh();
    }

    void refresh() {
        bill = getResponseBill(mainUser);
        transactions = getResponseTransactions(mainUser);
        cardName.setText(bill.getCardName());
        cardNumber.setText(bill.getCardNumber());
        cardValidity.setText(bill.getCardValidity());
        ibanLabel.setText(bill.getIban());
        balance.setText(String.format("%.2f", bill.getBalance()));
        firstnameLabel.setText(mainUser.getFirstname());
        lastnameLabel.setText(mainUser.getLastname());
        usernameLabel.setText(mainUser.getUsername());
        emailLabel.setText(mainUser.getEmail());
        if(!bill.getAutoBlock() && !bill.getManualBlock()){
            isActiveLabel.setText("Активен");
        }
        else {
            isActiveLabel.setText("Заблокирован");
        }
    }

    void renderTransactions() {
        transactionsField.getChildren().clear();
        for (int i = 0; i < transactions.size(); i++) {
            AnchorPane anchorPane;
            int y = 100 * i + 10 * i;
            anchorPane = transactions.get(i).createTransactionWidget(transactionsField, y);
            initializeTransactions(anchorPane, i);
        }
    }

    void initializeTransactions(AnchorPane transactionPane, int i) {
        transactionPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                viewTransaction(event, i);
                transactionPane.setStyle("-fx-background-color: #dddddd;-fx-border-width: 1.5; -fx-border-radius: 15; -fx-border-color: #000000; -fx-background-radius: 15;");
            }
        });
        transactionPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                transactionPane.setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 15; -fx-border-color: #000000; -fx-background-radius: 15;");
            }
        });
    }

    void viewTransaction(MouseEvent event, int i) {
        idTransaction = i;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("TransactionCard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 700);
            Stage stage = new Stage();
            stage.setTitle("Transaction Card");
            stage.setScene(scene);
            stage.show();
            log.info("окно TransactionCard успешно создано");
        } catch (IOException e) {
            log.error("окно TransactionCard не создано");
        }
    }
}

