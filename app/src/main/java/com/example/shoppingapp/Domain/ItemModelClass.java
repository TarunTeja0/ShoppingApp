package com.example.shoppingapp.Domain;

public class ItemModelClass {
    String name;
    String imageUrl;
    String description;
    String category;
    int quantity;
    int stock;
    int cost;
    String deliveryDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCost() {
        return cost;
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

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    public ItemModelClass(String name, String imageUrl, String description, String category, int quantity,int stock, int cost, String deliveryDate) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.stock = stock;
        this.cost = cost;
        this.deliveryDate = deliveryDate;
    }
}
