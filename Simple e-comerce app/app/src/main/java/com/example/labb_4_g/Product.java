package com.example.labb_4_g;

import java.io.Serializable;

public class Product implements Serializable {

    private String id, name, description, category, company, price, available;
    public Product(String id,
                   String name,
                   String description,
                   String category,
                   String company,
                   String price,
                   String available){

        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.company = company;
        this.price = price;
        this.available = available;
    }

    public String getAllInfo(){
        return id + "\n" + name + "\n" + description + "\n" + category + "\n" + company + "\n" + price + "\n" + available;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
