package com.example.user.foodtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 22/09/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "food.db";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DbContract.FeedAvailableFoods.TABLE_NAME + " (" +
                    DbContract.FeedAvailableFoods._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    DbContract.FeedAvailableFoods.NAME + " TEXT," +
                    DbContract.FeedAvailableFoods.KCAL + " REAL," +
                    DbContract.FeedAvailableFoods.CARBS + " REAL," +
                    DbContract.FeedAvailableFoods.FAT + " REAL," +
                    DbContract.FeedAvailableFoods.PROTEIN + " REAL);" +
            "CREATE TABLE " + DbContract.FeedFoodHistory.TABLE_NAME + " (" +
                    DbContract.FeedFoodHistory._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DbContract.FeedFoodHistory.DATE + " TEXT," +
                    DbContract.FeedFoodHistory.FOOD_ID + " INTEGER REFERENCES available_foods(_id)," +
                    DbContract.FeedFoodHistory.QUANTITY + " INTEGER)";


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
        values.put(DbContract.FeedAvailableFoods.NAME, name);
        values.put(DbContract.FeedAvailableFoods.KCAL, kcal);
        values.put(DbContract.FeedAvailableFoods.CARBS, carbs);
        values.put(DbContract.FeedAvailableFoods.FAT, fat);
        values.put(DbContract.FeedAvailableFoods.PROTEIN, protein);
        db.insertOrThrow(DbContract.FeedAvailableFoods.TABLE_NAME, null, values);
    }

    public void addNewFoodHistory(String date, Integer food_id, Integer quantity){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.FeedFoodHistory.DATE, date);
        values.put(DbContract.FeedFoodHistory.FOOD_ID, food_id);
        values.put(DbContract.FeedFoodHistory.QUANTITY, quantity);
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


    public List<String> getAllFoodNames(){
        List<String> foods = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DbContract.FeedAvailableFoods.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                foods.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning foods
        return foods;
    }

    public Integer findFoodIdWithName(String name){
        FoodItem foodItem = new FoodItem();
        String[] columns = {"id", "name", "kcal", "carbohydrates", "fat", "protein"};
        String[] args = {name};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DbContract.FeedAvailableFoods.TABLE_NAME, columns, " name = ?", args, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
            foodItem.setId(cursor.getInt(0));
            foodItem.setName(cursor.getString(1));
            foodItem.setKcal(cursor.getInt(2));
            foodItem.setCarbohydrates(cursor.getDouble(3));
            foodItem.setFat(cursor.getDouble(4));
            foodItem.setProtein(cursor.getDouble(5));
        }

        return foodItem.getId();
    }

    public void deleteAllFoodItems(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + DbContract.FeedAvailableFoods.TABLE_NAME);
        db.close();
    }

    //History Fragment

    // write a method that searches through all unique food items in table2
    public FoodHistory findFoodsEatenInAMonth(String month, String year){
        // search through col1 between 01 and 31
        FoodHistory foodHistory = new FoodHistory();

        return foodHistory;
    }

    // write a method that adds all eaten amounts of those foods

    // return food names and total amounts

}
