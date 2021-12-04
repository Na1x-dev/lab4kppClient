package com.example.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Button;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StaticFieldsAndRequests {
    static User mainUser;
    static Bill bill;
    static List<Transaction> transactions = new ArrayList<>();
    static String ip = "http://localhost:8080";
    static Component component;
    static int idButton;
    static int idTransaction;

    static User getResponseUser(User mainUser) throws IOException {
        HttpUriRequest request = new HttpGet(ip+"/users/byUsername/" + mainUser.getUsername());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        request.setHeader("Accept", "application/json; charset=UTF-8");
        request.setHeader("Content-type", "application/json; charset=UTF-8");
        HttpEntity entity = response.getEntity();
        User checkUser = new User();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            checkUser = checkUser.parseUserFromJSON(result);
            if(checkUser == null){
                checkUser = new User();
            }
        }
        return checkUser;
    }

    static List<Transaction> getResponseTransactions(User mainUser) throws IOException {
        HttpUriRequest request = new HttpGet(ip + "/transactions/byUsername/" + mainUser.getUsername());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        request.setHeader("Accept", "application/json; charset=UTF-8");
        request.setHeader("Content-type", "application/json; charset=UTF-8");
        HttpEntity entity = response.getEntity();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ArrayList<Transaction> transactions = new ArrayList<>();
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

    static Bill getResponseBill(User mainUser) throws IOException {
        HttpUriRequest request = new HttpGet(ip+"/bills/byUsername/" + mainUser.getUsername());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        request.setHeader("Accept", "application/json; charset=UTF-8");
        request.setHeader("Content-type", "application/json; charset=UTF-8");
        HttpEntity entity = response.getEntity();
        Bill bill = new Bill();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            bill = bill.parseUserFromJSON(result);
            //System.out.println(result);
            if (bill == null) {
                bill = new Bill();
            }
        }
        return bill;
    }

    static List<Component> getResponseComponents() throws IOException {
        HttpUriRequest request = new HttpGet(ip + "/components");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        HttpEntity entity = response.getEntity();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ArrayList<Component> components = new ArrayList<>();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            Type componentsListType = new TypeToken<ArrayList<Component>>() {
            }.getType();
            components = gson.fromJson(result, componentsListType);
            if (components == null) {
                components = new ArrayList<>();
            }
        }
        return components;
    }

    static User postResponseUser(User mainUser) throws IOException {
        HttpPost request = new HttpPost(ip+"/users");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        request.setEntity(new StringEntity(mainUser.getJson(),"UTF-8"));
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        User checkUser = new User();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            checkUser = checkUser.parseUserFromJSON(result);
            if(checkUser == null){
                checkUser = new User();
            }
        }
        return checkUser; // возвращает объект юзер, если юзер с таким никнеймом уже существует
    }

    static void postResponseBill(Bill bill) throws IOException {
        HttpPost request = new HttpPost(ip+"/bills");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        request.setEntity(new StringEntity(bill.getJson(),"UTF-8"));
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        Bill checkBill = new Bill();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            checkBill = checkBill.parseUserFromJSON(result);
            if(checkBill == null){
                checkBill = new Bill();
            }
            //System.out.println(checkBill.toString());
        }
    }

    static void postResponseTransactions(Transaction transaction) throws IOException {
        HttpPost request = new HttpPost(ip + "/transactions");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        request.setEntity(new StringEntity(transaction.getJson(),"UTF-8"));
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            //transaction = transaction.parseUserFromJSON(result);
            //System.out.println(result);
        }
        //return checkUser; // возвращает объект юзер, если юзер с таким никнеймом уже существует
    }

    static void putResponseBill(Bill bill) throws IOException {
        HttpPut request = new HttpPut(ip+"/bills/byId/" + bill.getId());
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        request.setEntity(new StringEntity(bill.getJson(),"UTF-8"));
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            //System.out.println(result);
        }
    }


}
