package com.example.user.foodtracker;

import android.content.ContentValues;
import android.content.Context;
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
                    DbContract.FeedAvailableFoods._ID + " INTEGER PRIMARY KEY," +
                    DbContract.FeedAvailableFoods.COL1 + " TEXT," +
                    DbContract.FeedAvailableFoods.COL2 + " FLOAT," +
                    DbContract.FeedAvailableFoods.COL3 + " FLOAT," +
                    DbContract.FeedAvailableFoods.COL4 + " FLOAT," +
                    DbContract.FeedAvailableFoods.COL5 + " FLOAT);" +
            "CREATE TABLE " + DbContract.FeedFoodHistory.TABLE_NAME + " (" +
                    DbContract.FeedFoodHistory._ID + " INTEGER PRIMARY KEY," +
                    DbContract.FeedFoodHistory.COL1 + " INTEGER," +
                    DbContract.FeedFoodHistory.COL2 + " INTEGER REFERENCES available_foods(_id)," +
                    DbContract.FeedFoodHistory.COL3 + " FLOAT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DbContract.FeedAvailableFoods.TABLE_NAME +
            "DROP TABLE IF EXISTS " + DbContract.FeedFoodHistory.TABLE_NAME;



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
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
        db.insertOrThrow("available_foods", null, values);
    }

    public void addNewFoodHistory(Integer date, Integer food_id, Integer quantity){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.FeedFoodHistory.COL1, date);
        values.put(DbContract.FeedFoodHistory.COL2, food_id);
        values.put(DbContract.FeedFoodHistory.COL3, quantity);
        db.insertOrThrow("food_history", null, values);
    }
}
