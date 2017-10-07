package com.example.user.foodtracker;

/**
 * Created by user on 07/10/2017.
 */

public class FoodHistory {
    private Integer id;
    private String date;
    private Integer food_id;
    private Integer quantity;

    public FoodHistory() {
        this.id = 0;
        this.date = "";
        this.food_id = 0;
        this.quantity = 0;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Integer getFood_id() {
        return food_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFood_id(Integer food_id) {
        this.food_id = food_id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
