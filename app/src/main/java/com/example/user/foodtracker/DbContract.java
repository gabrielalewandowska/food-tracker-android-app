package com.example.user.foodtracker;

import android.provider.BaseColumns;

/**
 * Created by user on 22/09/2017.
 */

public class DbContract {
    private DbContract(){}

    public static class FeedAvailableFoods implements BaseColumns {
        public static final String TABLE_NAME = "available_foods";
        public static final String _ID = "id";
        public static final String NAME = "name";
        public static final String KCAL = "kcal";
        public static final String CARBS = "carbohydrates";
        public static final String FAT = "fat";
        public static final String PROTEIN = "protein";
    }


    public static class FeedFoodHistory implements BaseColumns {
        public static final String TABLE_NAME = "food_history";
        public static final String _ID = "id";
        public static final String DATE = "date";
        public static final String FOOD_ID = "food_id";
        public static final String QUANTITY = "quantity";
    }
}

