package com.ordering_system.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "food")
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String ingredient;
    @ManyToOne(
            cascade = CascadeType.REFRESH
    )
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurantEntity;
    private double price;


    public FoodEntity( String name, String ingredient, double price,RestaurantEntity restaurantEntity,long id
                      ) {
        this.name = name;
        this.ingredient = ingredient;
        this.price = price;
        this.restaurantEntity = restaurantEntity;
        this.id=id;

    }

    public FoodEntity() {
    }

    public RestaurantEntity getRestaurantEntity() {
        return restaurantEntity;
    }

    public void setRestaurantEntity(RestaurantEntity restaurantEntity) {
        this.restaurantEntity = restaurantEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
