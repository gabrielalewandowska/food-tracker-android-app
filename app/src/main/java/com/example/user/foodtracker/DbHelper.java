package com.example.user.foodtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 22/09/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "food.db";
    public static final String Table1 = "available_foods";
    public static final String Table2 = "food_history";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DbContract.FeedAvailableFoods.TABLE_NAME + " (" +
                    DbContract.FeedAvailableFoods._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    DbContract.FeedAvailableFoods.COL1 + " TEXT," +
                    DbContract.FeedAvailableFoods.COL2 + " REAL," +
                    DbContract.FeedAvailableFoods.COL3 + " REAL," +
                    DbContract.FeedAvailableFoods.COL4 + " REAL," +
                    DbContract.FeedAvailableFoods.COL5 + " REAL);" +
            "CREATE TABLE " + DbContract.FeedFoodHistory.TABLE_NAME + " (" +
                    DbContract.FeedFoodHistory._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DbContract.FeedFoodHistory.COL1 + " TEXT," +
                    DbContract.FeedFoodHistory.COL2 + " INTEGER REFERENCES available_foods(_id)," +
                    DbContract.FeedFoodHistory.COL3 + " INTEGER)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DbContract.FeedAvailableFoods.TABLE_NAME +
            "DROP TABLE IF EXISTS " + DbContract.FeedFoodHistory.TABLE_NAME;



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        this.deleteAllFoodItems();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void addNewFood(String name, int kcal, double carbs, double fat, double protein){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.FeedAvailableFoods.COL1, name);
        values.put(DbContract.FeedAvailableFoods.COL2, kcal);
        values.put(DbContract.FeedAvailableFoods.COL3, carbs);
        values.put(DbContract.FeedAvailableFoods.COL4, fat);
        values.put(DbContract.FeedAvailableFoods.COL5, protein);
        db.insertOrThrow(DbContract.FeedAvailableFoods.TABLE_NAME, null, values);
    }

    public void addNewFoodHistory(Integer date, Integer food_id, Integer quantity){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.FeedFoodHistory.COL1, date);
        values.put(DbContract.FeedFoodHistory.COL2, food_id);
        values.put(DbContract.FeedFoodHistory.COL3, quantity);
        db.insertOrThrow(DbContract.FeedFoodHistory.TABLE_NAME, null, values);
    }

    public FoodItem returnAllAvailableFoods(){
        FoodItem foodItem = new FoodItem();
        String[] columns = {"id", "name", "kcal", "carbohydrates", "fat", "protein"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DbContract.FeedAvailableFoods.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
            foodItem.setId(cursor.getInt(0));
            foodItem.setName(cursor.getString(1));
            foodItem.setKcal(cursor.getInt(2));
            foodItem.setCarbohydrates(cursor.getDouble(3));
            foodItem.setFat(cursor.getDouble(4));
            foodItem.setProtein(cursor.getDouble(5));
        }
        return foodItem;
    }

    public void deleteAllFoodItems(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + DbContract.FeedAvailableFoods.TABLE_NAME);
        db.close();
    }


//    public Cursor getNutrientValue(String name, String nutrient){
//        SQLiteDatabase db = getReadableDatabase();
//        String[] columns = {"name", nutrient};
//        String selection = "name = ?";
//        String[] selectionArgs = {name};
//        Cursor cursor = db.query(
//                DbContract.FeedAvailableFoods.TABLE_NAME,
//                columns,
//                selection,
//                selectionArgs,
//                null, null, null
//        );
//
//        return cursor;
//    }
//
//    public FoodItem findFoodItemByName(String name){
//        SQLiteDatabase db = getReadableDatabase();
//        FoodItem foodItem = new FoodItem(name);
//        String[] columns = {DbContract.FeedAvailableFoods.COL1, DbContract.FeedAvailableFoods.COL2,
//                DbContract.FeedAvailableFoods.COL3, DbContract.FeedAvailableFoods.COL4, DbContract.FeedAvailableFoods.COL5};
//        String[] args = {name};
//        Cursor cursor = db.query(DbContract.FeedAvailableFoods.TABLE_NAME, columns, "name = ? ", args, null, null, null, null);
//        if(cursor != null){
//            cursor.moveToFirst();
//            foodItem.setNutrientsHashMap();
//        }
//
//    }
}
