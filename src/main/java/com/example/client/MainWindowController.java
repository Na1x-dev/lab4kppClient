package com.example.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainWindowController {
    static User mainUser;
    static Bill bill;
    static List<Transaction> transactions = new ArrayList<>();


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
    private Label firstnameAndLastname;

    @FXML
    private Button refreshButton;

    @FXML
    private AnchorPane transactionsField;

    void renderTransactions() {
        transactionsField.getChildren().clear();
        for (int i = 0; i < transactions.size(); i++) {
            int y = 100 * i + 10 * i;
            transactions.get(i).createTransactionWidget(transactionsField, y);
        }
    }

    List<Transaction> getResponseTransactions(User mainUser) throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/transactions/byUsername/" + mainUser.getUsername());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            Type transactionsListType = new TypeToken<ArrayList<Transaction>>() {
            }.getType();
            transactions = gson.fromJson(result, transactionsListType);
            if (transactions == null) {
                transactions = new ArrayList<>();
            }
        }
        return transactions;
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
        try {
            transactions = getResponseTransactions(mainUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        renderTransactions();
        refresh();
    }

    @FXML
    private void initialize() {

        refresh();
        //renderTransactions();
    }
}


