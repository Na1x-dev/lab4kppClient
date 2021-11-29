package com.example.client;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Transaction {
    public Integer id;
    public String username;
    public String nameOfTransaction;
    public String checkNumber;
    public double price;
    public String description;
    public String cardNumber;
    //private AnchorPane transactionPane = new AnchorPane();

    public Transaction() {
        username = "Semen";
        nameOfTransaction = "some big black";
        checkNumber = "9999999999";
        price = 9.99;
        description = "this is not cock";
        cardNumber = "0000 0000 0000 0000";
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNameOfTransaction() {
        return nameOfTransaction;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNameOfTransaction(String nameOfTransaction) {
        this.nameOfTransaction = nameOfTransaction;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    void viewTransaction() {
        System.out.println("clicked");
    }


    public void pressWidget(AnchorPane transactionPane) {
        viewTransaction();
        transactionPane.setStyle("-fx-background-color: #dddddd;-fx-border-width: 1.5; -fx-border-radius: 15; -fx-border-color: #000000; -fx-background-radius: 15;");
    }


    public void releaseWidget(AnchorPane transactionPane) {
        transactionPane.setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 15; -fx-border-color: #000000; -fx-background-radius: 15;");
    }


    public void createTransactionWidget(AnchorPane transactionsField, int y) {
        Label nameOfTransactionLabel = new Label(this.nameOfTransaction);
        Label checkNumberLabel = new Label(this.checkNumber);
        Label priceLabel = new Label(String.valueOf(this.price) + " $");
        AnchorPane transactionPane = new AnchorPane(nameOfTransactionLabel, checkNumberLabel, priceLabel);
        transactionPane.setLayoutY(y);
        transactionPane.setMinSize(530, 100);
        transactionPane.setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 15; -fx-border-color: #000000; -fx-background-radius: 15;");
        transactionPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                viewTransaction();
                transactionPane.setStyle("-fx-background-color: #dddddd;-fx-border-width: 1.5; -fx-border-radius: 15; -fx-border-color: #000000; -fx-background-radius: 15;");
            }
        });

        transactionPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                transactionPane.setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 15; -fx-border-color: #000000; -fx-background-radius: 15;");
            }
        });
        nameOfTransactionStyle(nameOfTransactionLabel);
        checkNumberStyle(checkNumberLabel);
        priceStyle(priceLabel);
        transactionsField.getChildren().add(transactionPane);
    }

    void nameOfTransactionStyle(Label nameOfTransactionLabel){
        Font underdogFont = new Font("Underdog", 28);
        nameOfTransactionLabel.fontProperty().set(underdogFont);
        nameOfTransactionLabel.setLayoutX(15);
        nameOfTransactionLabel.setLayoutY(25);
        nameOfTransactionLabel.setMinSize(500, 0);
        nameOfTransactionLabel.setAlignment(Pos.CENTER);
    }

    void checkNumberStyle(Label checkNumberLabel){
        Font underdogFont = new Font("Underdog", 16);
        checkNumberLabel.fontProperty().set(underdogFont);
        checkNumberLabel.setLayoutX(15);
        checkNumberLabel.setLayoutY(70);
    }

    void priceStyle(Label priceLabel){
        Font underdogFont = new Font("Underdog", 22);
        priceLabel.fontProperty().set(underdogFont);
        priceLabel.setLayoutX(415);
        priceLabel.setLayoutY(60);
        priceLabel.setMinSize(100, 0);
        priceLabel.setAlignment(Pos.CENTER_RIGHT);
    }
}
