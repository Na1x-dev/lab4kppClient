package com.example.client;

public class Component {
    public Integer id;
    public String nameOfComponent;
    public double price;
    public String description;

    public Component(){
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNameOfComponent(String nameOfComponent) {
        this.nameOfComponent = nameOfComponent;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getNameOfComponent() {
        return nameOfComponent;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }


}
