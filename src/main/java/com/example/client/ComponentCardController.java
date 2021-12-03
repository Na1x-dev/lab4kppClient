package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import java.io.IOException;

import static com.example.client.StaticFieldsAndRequests.*;

public class ComponentCardController {
    Transaction transaction = new Transaction();

    @FXML
    private AnchorPane componentImage;

    @FXML
    private Label description;

    @FXML
    private Label nameOfComponent;

    @FXML
    private Button payButton;

    @FXML
    private Label price;

    @FXML
    void whiteButtonRelease(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #7C809B;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        someButton.setTextFill(Paint.valueOf("WHITE"));
    }

    @FXML
    void whiteButtonPress(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #6B698A;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        someButton.setTextFill(Paint.valueOf("WHITE"));
    }

    @FXML
    void whiteButtonExit(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16;");
        someButton.setTextFill(Paint.valueOf("BLACK"));
    }

    @FXML
    void whiteButtonEnter(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #7C809B;-fx-border-width: 1.5; -fx-border-radius: 14; -fx-border-color: #000000; -fx-background-radius: 16;");
        someButton.setTextFill(Paint.valueOf("WHITE"));
    }

    @FXML
    void pay(ActionEvent event) {

            bill = transaction.componentToTransaction(component, bill);
            try {
                postResponseTransactions(transaction);
                putResponseBill(bill);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ((Node) (event.getSource())).getScene().getWindow().hide();

    }
    @FXML
    void initialize(){
        if (!bill.getIsActive()) {
            payButton.setDisable(true);
            payButton.setText("Карта заблокирована");
        }
        setCardStyle();
    }

    void setCardStyle(){
        componentImage.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16; -fx-border-color: #000000; -fx-border-width: 1.5; -fx-border-radius: 16; -fx-background-image: url(file:images/" + idButton +".jpg); -fx-background-repeat: no-repeat; -fx-background-size: 130; -fx-background-position: center center");
        nameOfComponent.setText(component.getNameOfComponent());
        description.setText(component.getDescription());
        price.setText(String.valueOf(component.getPrice()));
    }
}
