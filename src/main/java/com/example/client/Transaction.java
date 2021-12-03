package com.example.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client.StaticFieldsAndRequests.*;

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

    public AnchorPane createTransactionWidget(AnchorPane transactionsField, int y) {
        Label nameOfTransactionLabel = new Label(this.nameOfTransaction);
        Label checkNumberLabel = new Label(this.checkNumber);
        Label priceLabel = new Label(String.valueOf(this.price) + " BYN");
        AnchorPane transactionPane = new AnchorPane(nameOfTransactionLabel, checkNumberLabel, priceLabel);
        transactionPane.setLayoutY(y);
        transactionPane.setMinSize(530, 100);
        transactionPane.setStyle("-fx-background-color: #ffffff;-fx-border-width: 1.5; -fx-border-radius: 15; -fx-border-color: #000000; -fx-background-radius: 15;");

        nameOfTransactionStyle(nameOfTransactionLabel);
        checkNumberStyle(checkNumberLabel);
        priceStyle(priceLabel);
        transactionsField.getChildren().add(transactionPane);
        return transactionPane;
    }

    void nameOfTransactionStyle(Label nameOfTransactionLabel){
        Font underdogFont = new Font("Underdog", 28);
        nameOfTransactionLabel.fontProperty().set(underdogFont);
        nameOfTransactionLabel.setLayoutX(15);
        nameOfTransactionLabel.setLayoutY(25);
        nameOfTransactionLabel.setMinSize(500, 0);
        nameOfTransactionLabel.setMaxWidth(500);
        nameOfTransactionLabel.setAlignment(Pos.CENTER);
        nameOfTransactionLabel.setTextOverrun(OverrunStyle.ELLIPSIS);
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
        priceLabel.setLayoutX(315);
        priceLabel.setLayoutY(60);
        priceLabel.setMinSize(200, 0);
        priceLabel.setAlignment(Pos.BASELINE_RIGHT);
    }

    Bill componentToTransaction(Component component, Bill bill) {
        this.username = bill.getUsername();
        this.nameOfTransaction = component.getNameOfComponent();
        this.checkNumber = String.valueOf((int)(Math.random() * 1000000000));
        this.price = component.getPrice();
        bill.setBalance(bill.getBalance() - this.price);
        this.cardNumber = bill.getCardNumber();
        this.description = component.getDescription();
        return bill;
    }

    public Transaction parseUserFromJSON(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(JSON, Transaction.class);
    }

    public String getJson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(Transaction.this);
    }


}
