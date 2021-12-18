package com.example.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.client.HelloController;

public class StaticFieldsAndRequests {
    private static final Logger log = Logger.getLogger(StaticFieldsAndRequests.class);
    public static User mainUser;
    public static Bill bill;
    public static List<Transaction> transactions = new ArrayList<>();
    static String ip = "http://localhost:8080";
    public static Component component;
    public static int idButton;
    public static int idTransaction;
    public static String logString;

    public static User getResponseUser(User mainUser) {
        HttpUriRequest request = new HttpGet(ip + "/users/byUsername/" + mainUser.getUsername());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса get класса User");
        } catch (IOException e) {
            log.error("нет ответа запроса get класса User");
            logString="Сервер недоступен";
        }
        request.setHeader("Accept", "application/json; charset=UTF-8");
        request.setHeader("Content-type", "application/json; charset=UTF-8");
        User checkUser = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = null;
                result = EntityUtils.toString(entity);
                log.info("получена информация объекта класса User");
                checkUser = User.parseUserFromJSON(result);
                if (checkUser == null) {
                    checkUser = new User();
                }
            }
        } catch (NullPointerException | IOException e) {
            log.error("не удалось получить информацию объекта класса User");
            //e.printStackTrace();
            logString="Сервер недоступен";
        }
        return checkUser;
    }

    public static List<Transaction> getResponseTransactions(User mainUser) {
        HttpUriRequest request = new HttpGet(ip + "/transactions/byUsername/" + mainUser.getUsername());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса get класса Transaction");
        } catch (IOException e) {
            log.error("нет ответа запроса get класса Transaction");
            e.printStackTrace();
        }
        request.setHeader("Accept", "application/json; charset=UTF-8");
        request.setHeader("Content-type", "application/json; charset=UTF-8");
        HttpEntity entity = response.getEntity();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            if (entity != null) {
                String result = null;
                result = EntityUtils.toString(entity);
                log.info("получена информация объекта класса Transaction");
                Type transactionsListType = new TypeToken<ArrayList<Transaction>>() {
                }.getType();
                transactions = gson.fromJson(result, transactionsListType);
                if (transactions == null) {
                    transactions = new ArrayList<>();
                }
            }
        } catch (IOException e) {
            log.error("не удалось получить информацию объекта класса Transaction");
            e.printStackTrace();
        }
        return transactions;
    }

    public static Bill getResponseBill(User mainUser) {
        HttpUriRequest request = new HttpGet(ip + "/bills/byUsername/" + mainUser.getUsername());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса get класса Bill");
        } catch (IOException e) {
            log.error("нет ответа запроса get класса Bill");
            e.printStackTrace();
        }
        request.setHeader("Accept", "application/json; charset=UTF-8");
        request.setHeader("Content-type", "application/json; charset=UTF-8");
        HttpEntity entity = response.getEntity();
        Bill bill = new Bill();
        try {
            if (entity != null) {
                String result = null;
                result = EntityUtils.toString(entity);
                log.info("получена информация объекта класса Bill");
                bill = bill.parseUserFromJSON(result);
                if (bill == null) {
                    bill = new Bill();
                }
            }
        } catch (IOException e) {
            log.error("не удалось получить информацию объекта класса Bill");
            e.printStackTrace();
        }
        return bill;
    }

    public static List<Component> getResponseComponents() {
        HttpUriRequest request = new HttpGet(ip + "/components");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса get класса Component");
        } catch (IOException e) {
            log.error("нет ответа запроса get класса Component");
            e.printStackTrace();
        }
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        HttpEntity entity = response.getEntity();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ArrayList<Component> components = new ArrayList<>();
        try {
            if (entity != null) {
                String result = null;
                result = EntityUtils.toString(entity);
                log.info("получена информация объектов класса Component");
                Type componentsListType = new TypeToken<ArrayList<Component>>() {
                }.getType();
                components = gson.fromJson(result, componentsListType);
                if (components == null) {
                    components = new ArrayList<>();
                }
            }
        } catch (IOException e) {
            log.error("не удалось получить информацию объектов класса Components");
            e.printStackTrace();
        }
        return components;
    }

    public static User postResponseUser(User mainUser) {
        HttpPost request = new HttpPost(ip + "/users");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        request.setEntity(new StringEntity(mainUser.getJson(), "UTF-8"));
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса post класса User");
        } catch (IOException e) {
            log.error("нет ответа запроса post класса User");
        }
        User checkUser = null;
        try {
        HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result;
                result = EntityUtils.toString(entity);
                log.info("получена информация объекта класса User");
                checkUser = User.parseUserFromJSON(result);
                if (checkUser == null) {
                    checkUser = new User();
                }
            }
        } catch (NullPointerException | IOException e) {
            log.error("не удалось получить информацию объекта класса User");
            //e.printStackTrace();
        }
        return checkUser; // возвращает объект юзер, если юзер с таким никнеймом уже существует
    }

    public static void postResponseBill(Bill bill) {
        HttpPost request = new HttpPost(ip + "/bills");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        request.setEntity(new StringEntity(bill.getJson(), "UTF-8"));
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса post класса Bill");
        } catch (IOException e) {
            log.error("нет ответа запроса post класса Bill");
            //e.printStackTrace();
        }
        try {
            HttpEntity entity = response.getEntity();
            Bill checkBill = new Bill();
            if (entity != null) {
                String result = null;
                result = EntityUtils.toString(entity);
                log.info("получена информация объекта класса Bill");
            }
        } catch (NullPointerException | IOException e) {
            log.error("не удалось получить информацию объекта класса Bill");
            //e.printStackTrace();
        }
    }

    public static void postResponseTransactions(Transaction transaction) {
        HttpPost request = new HttpPost(ip + "/transactions");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        request.setEntity(new StringEntity(transaction.getJson(), "UTF-8"));
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса post класса Transaction");
        } catch (IOException e) {
            log.error("нет ответа запроса post класса Transaction");
            //e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        try {
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                log.info("получена информация объекта класса Transaction");
            }
        } catch (IOException e) {
            log.error("не удалось получить информацию объекта класса Transaction");
            //e.printStackTrace();
        }
    }

    public static void putResponseBill(Bill bill) {
        HttpPut request = new HttpPut(ip + "/bills/byId/" + bill.getId());
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        request.setEntity(new StringEntity(bill.getJson(), "UTF-8"));
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса put класса Bill");
        } catch (IOException e) {
            log.error("нет ответа запроса put класса Bill");
            //e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        try {
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                log.info("получена информация объекта класса Bill");
            }
        } catch (IOException e) {
            log.error("не удалось получить информацию объекта класса Bill");
            //e.printStackTrace();
        }
    }
}
