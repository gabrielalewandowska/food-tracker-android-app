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
        public static final String COL1 = "name";
        public static final String COL2 = "kcal";
        public static final String COL3 = "carbohydrates";
        public static final String COL4 = "fat";
        public static final String COL5 = "protein";
    }


    public static class FeedFoodHistory implements BaseColumns {
        public static final String TABLE_NAME = "food_history";
        public static final String _ID = "id";
        public static final String COL1 = "date";
        public static final String COL2 = "food_id";
        public static final String COL3 = "quantity";
    }
}

