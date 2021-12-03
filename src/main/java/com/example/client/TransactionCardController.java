package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static com.example.client.StaticFieldsAndRequests.*;

public class TransactionCardController {

    @FXML
    private Label cardNumberLabel;

    @FXML
    private Label checkNumberLabel;

    @FXML
    private Button closeButton;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label nameOfTransactionLabel;

    @FXML
    private Label priceLabel;

    @FXML
    void closeCard(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void initialize(){
        nameOfTransactionLabel.setText(transactions.get(idTransaction).getNameOfTransaction());
        cardNumberLabel.setText(transactions.get(idTransaction).getCardNumber());
        checkNumberLabel.setText(transactions.get(idTransaction).getCheckNumber());
        descriptionLabel.setText(transactions.get(idTransaction).getDescription());
        priceLabel.setText(String.valueOf(transactions.get(idTransaction).getPrice()));
    }
}


















