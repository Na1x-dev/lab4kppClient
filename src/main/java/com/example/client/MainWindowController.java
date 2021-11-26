package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

public class MainWindowController {
    static User mainUser;
    static Bill bill;
    static ArrayList<Transaction> transactions = new ArrayList<>();


    @FXML
    private Label firstnameAndLastname;

    @FXML
    private AnchorPane transactionsField;

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
    private Button refreshButton;

    void renderTransactions(){

    }

    Bill getResponseBill() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/bills/byUsername/" + mainUser.getUsername());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        Bill bill = new Bill();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            bill = bill.parseUserFromJSON(result);
            if (bill == null) {
                bill = new Bill();
            }
        }
        return bill;
    }

    void refresh() {
        try {
            bill = getResponseBill();
        } catch (IOException e) {
            e.printStackTrace();
        }
        firstnameAndLastname.setText(mainUser.getFirstname() + " " + mainUser.getLastname());
        cardName.setText(bill.getCardName());
        cardNumber.setText(bill.getCardNumber());
        cardValidity.setText(bill.getCardValidity());
        balance.setText(String.valueOf(bill.getBalance()));
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
        } catch (IOException e) {
            //System.Logger logger = System.Logger.getLogger(getClass().getName());
            //logger.log(System.Logger.Level.SEVERE, "Failed to create new Window.", e)
            System.out.println("Failed to create new Window");
            e.printStackTrace();
        }
    }

    @FXML
    void refresh(ActionEvent event) {
        refresh();
    }

    @FXML
    private void initialize() {
        refresh();
        transactions.add(new Transaction());
        transactions.get(0).createTransactionWidget(transactionsField);
    }
}


