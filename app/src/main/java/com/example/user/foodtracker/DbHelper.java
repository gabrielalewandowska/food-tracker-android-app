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
                    DbContract.FeedFoodHistory.COL1 + " INTEGER," +
                    DbContract.FeedFoodHistory.COL2 + " INTEGER REFERENCES available_foods(_id)," +
                    DbContract.FeedFoodHistory.COL3 + " INTEGER)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DbContract.FeedAvailableFoods.TABLE_NAME +
            "DROP TABLE IF EXISTS " + DbContract.FeedFoodHistory.TABLE_NAME;



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        this.deleteAllFoodItems();
//        addNewFood("oats", 389, 66.3, 6.9, 16.9);
//        addNewFood("bananas", 89, 22.8, 0.3, 1.1);
//        addNewFood("sweet potatoes", 86, 20.12, 0.05, 1.6);
    }

//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(
//                "CREATE TABLE available_foods(" +
//                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "name TEXT," +
//                        "kcal INTEGER," +
//                        "carbs REAL," +
//                        "fat REAL," +
//                        "protein REAL);" +
//                        "CREATE TABLE food_history(" +
//                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "date INTEGER," +
//                        "food_id INTEGER REFERENCES available_foods(_id)," +
//                        "quantity INTEGER);" +
//        "");
//    }

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

    public Cursor returnAllAvailableFoods(){
        String[] columns = {"_id", "name", "kcal", "carbohydrates", "fat", "protein"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DbContract.FeedAvailableFoods.TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
    }

    public void deleteAllFoodItems(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + DbContract.FeedAvailableFoods.TABLE_NAME);
        db.close();
    }
}
