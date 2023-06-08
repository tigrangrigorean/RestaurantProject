package com.ordering_system.model.dto;


public class Food {


    private String name;
    private String ingredient;
    private double price;
    private long restaurantId;

    public Food(String name, String ingredient, double price,long restaurantId) {

        this.name = name;
        this.ingredient = ingredient;
        this.price = price;
        this.restaurantId=restaurantId;
    }
    public Food(){}

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
