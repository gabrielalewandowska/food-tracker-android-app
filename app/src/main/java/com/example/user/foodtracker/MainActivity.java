package com.example.user.foodtracker;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    android.support.v4.app.FragmentManager fragmentManager;
    DiaryFragment diaryFragment;
    DiaryFragment newDiaryFragment;
    String date;
    String foodName;
    Integer food_id;
    Integer quantity;
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";
    DbHelper db;
    DiaryFragment testDiaryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        addDynamicDiaryFragment();

        db = new DbHelper(this);
//        db.deleteAllFoodItems();
//        db.addNewFood("oats", 389, 66.3, 6.9, 16.9);
//        db.addNewFood("bananas", 89, 22.8, 0.3, 1.1);
//        db.addNewFood("sweet potatoes", 86, 20.12, 0.05, 1.6);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
        diaryFragment = (DiaryFragment) fragmentManager.findFragmentByTag("diary_fragment");
        date = getCurrentDateAsString();


    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new CalendarFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.diaryFragment) {
            setTitle("Diary");
            DiaryFragment fragment = new DiaryFragment();
            testDiaryFragment = fragment;
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "Food Diary");
            fragmentTransaction.commit();
        } else if (id == R.id.AddNewFoodFragment) {
            setTitle("Add New Food Fragment");
            AddNewFoodFragment fragment = new AddNewFoodFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "Add New Food");
            fragmentTransaction.commit();
        } else if (id == R.id.HistoryFragment) {
            setTitle("History Fragment");
            HistoryFragment fragment = new HistoryFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "History");
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setDate(String newDate){
        this.date = newDate;
        Log.d("Changed date:", this.date);
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
////        diaryFragment.setDate(this.date);
////        fragmentTransaction.replace(R.id.diary_fragment, diaryFragment);
//        fragmentTransaction.commit();
        testDiaryFragment.setDate(this.date);

    }

    public void addDynamicDiaryFragment(){
        newDiaryFragment = new DiaryFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.drawer_layout, newDiaryFragment, "diary_fragment").commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    public String getDate(){
        return this.date;
    }

    public static String getCurrentDateAsString(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public void setQuantity(Integer newQuantity){
        this.quantity = newQuantity;
        Log.d("MainActivity", "quantity updated");
        db.addNewFoodHistory(this.date, this.food_id, this.quantity);
        Log.d("Database", "updated");
    }

    public Integer getQuantity(){
        return this.quantity;
    }


    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
        this.updateFoodIdWithName(foodName);
    }

    public void updateFoodIdWithName(String foodName){
        this.food_id = db.findFoodIdWithName(foodName);
        Log.d("food_id: ", this.food_id.toString());
    }

//    public void addNewFoodHistoryToDb(){
//        db.addNewFoodHistory(this.date, this.food_id, this.quantity);
//        Log.d("Database", "updated");
//    }
}
