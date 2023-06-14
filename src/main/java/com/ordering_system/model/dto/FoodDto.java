package com.ordering_system.model.dto;

public class FoodDto {
    private long id;

    public FoodDto(){}
    public FoodDto(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
