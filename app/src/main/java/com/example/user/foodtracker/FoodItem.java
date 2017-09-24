package com.example.user.foodtracker;

import java.util.HashMap;

/**
 * Created by user on 24/09/2017.
 */

public class FoodItem {
    private String name;
    private HashMap<Nutrients, Double> nutrientsHashMap;

    public FoodItem(String name, HashMap<Nutrients, Double> nutrientsHashMap) {
        this.name = name;
        this.nutrientsHashMap = new HashMap<>();
            for(Nutrients nutrient : Nutrients.values()){
                this.nutrientsHashMap.put(nutrient, 0.0);
            }
    }

    public String getName() {
        return name;
    }

    public HashMap<Nutrients, Double> getNutrientsHashMap() {
        return nutrientsHashMap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNutrientsHashMap(HashMap<Nutrients, Double> nutrientsHashMap) {
        this.nutrientsHashMap = nutrientsHashMap;
    }
}
