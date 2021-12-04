package com.example.client;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DecimalFormat;

public class Bill {

    public Integer id;
    public String username;
    public String iban;
    public String cardNumber;
    public String cardName;
    public String cardValidity;
    public double balance;
    public boolean isAutoBlock;
    public boolean isManualBlock;

    public Bill() {
        id = 1;
        username = "Semen";
        iban = "BY0";
        cardNumber = "0000 0000 0000 0000";
        cardName = "card 0";
        cardValidity = "00/00";
        balance = 0;
        isAutoBlock = false;
        isManualBlock = false;
    }

    public Integer getId() { return id; }

    public String getUsername() {
        return username;
    }

    public String getIban() {
        return iban;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardValidity() {
        return cardValidity;
    }

    public double getBalance() {
        return balance;
    }

    public boolean getAutoBlock() {
        return isAutoBlock;
    }

    public boolean getManualBlock() {
        return isManualBlock;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardValidity(String cardValidity) {
        this.cardValidity = cardValidity;
    }

    public void setBalance(double balance) {
        if(balance<(-1000)){
            setAutoBlock(true);
        }
        else {
            setAutoBlock(false);
        }
        this.balance = balance;
    }

    public void setAutoBlock(boolean autoBlock) {
        isAutoBlock = autoBlock;
    }

    public void setManualBlock(boolean manualBlock) {
        isManualBlock = manualBlock;
    }

    private void generateIban() {
        iban = "BY";
        for (int i = 0; i < 26; i++) {
            iban += (int) (Math.random() * 10);
        }
    }

    public void generateBankData(String username) {
        setUsername(username);
        generateIban();
        generateCardName();
        generateCardNumber();
        generateCardValidity();
        generateBalance();
    }

    private void generateCardName() {
        cardName = "Карта 1";
    }

    private void generateCardNumber() {
        cardNumber = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cardNumber += (int) (Math.random() * 10);
            }
            cardNumber += " ";
        }
        cardNumber = cardNumber.trim();
    }

    private void generateCardValidity() {
        cardValidity = "";
        cardValidity += (int) (1 + Math.random() * 10) + "/" + (int) (22 + Math.random() * 5);
    }

    private void generateBalance() {
        balance = 0.01;
    }

    public String getJson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(getCardName() + "  " + gson.toJson(Bill.this));
        return gson.toJson(Bill.this);
    }

    public Bill parseUserFromJSON(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(JSON, Bill.class);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", iban='" + iban + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardValidity='" + cardValidity + '\'' +
                ", balance=" + balance +
                '}';
    }
}
