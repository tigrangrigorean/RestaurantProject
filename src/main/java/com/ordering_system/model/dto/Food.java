package com.ordering_system.model.dto;


public class Food {


    private String name;
    private String ingredient;
    private double price;

    public Food(String name, String ingredient, double price) {
        this.name = name;
        this.ingredient = ingredient;
        this.price = price;
    }
    public Food(){}

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
