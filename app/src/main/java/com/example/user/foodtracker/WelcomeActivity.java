package com.example.user.foodtracker;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView tv = (TextView) findViewById(R.id.textView);
        DbHelper db = new DbHelper(this);
        db.deleteAllFoodItems();
//        db.addNewFood("oats", 389, 66.3, 6.9, 16.9);
//        db.addNewFood("bananas", 89, 22.8, 0.3, 1.1);
//        db.addNewFood("sweet potatoes", 86, 20.12, 0.05, 1.6);
        tv.setText("");

        Cursor k = db.returnAllAvailableFoods();
        while(k.moveToNext()){
            Integer id = k.getInt(0);
            String name = k.getString(1);
            Integer kcal = k.getInt(2);
            Double carbs = k.getDouble(3);
            Double fat = k.getDouble(4);
            Double protein = k.getDouble(5);
            tv.setText(tv.getText() + "\n" + id + " " + name + " " + kcal + " " + carbs +
            " " + fat + " " + protein);
        }
    }
}
