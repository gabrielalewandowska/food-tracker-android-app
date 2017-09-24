package com.example.user.foodtracker;

import java.util.HashMap;

/**
 * Created by user on 24/09/2017.
 */

public class FoodItem {
    private Integer id;
    private String name;
    private Integer kcal;
    private Double carbohydrates;
    private Double fat;
    private Double protein;

    public FoodItem(String name, Integer kcal, Double carbohydrates, Double fat, Double protein) {
        this.name = name;
        this.kcal = kcal;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.protein = protein;
    }

    public FoodItem() {
        this.id = 0;
        this.name = "";
        this.kcal = 0;
        this.carbohydrates = 0.0;
        this.fat = 0.0;
        this.protein = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKcal() {
        return kcal;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public Double getFat() {
        return fat;
    }

    public Double getProtein() {
        return protein;
    }

    public void setKcal(Integer kcal) {
        this.kcal = kcal;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }
}
