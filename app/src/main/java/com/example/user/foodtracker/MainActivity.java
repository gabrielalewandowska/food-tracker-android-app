package com.example.user.foodtracker;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    DiaryFragment diaryFragment;
    DiaryFragment newDiaryFragment;
    CalendarFragment calendarFragment;
    String date;
    Integer food_id;
    Integer quantity;
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addDynamicDiaryFragment();

        DbHelper db = new DbHelper(this);
        db.deleteAllFoodItems();
        db.addNewFood("oats", 389, 66.3, 6.9, 16.9);
        db.addNewFood("bananas", 89, 22.8, 0.3, 1.1);
        db.addNewFood("sweet potatoes", 86, 20.12, 0.05, 1.6);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        DiaryFragment fragment = new DiaryFragment();
//        fragmentTransaction.replace(R.id.frag_container, fragment);
//        fragmentTransaction.commit();
        diaryFragment = (DiaryFragment) fragmentManager.findFragmentByTag("diary_fragment");
//        diaryFragment = (DiaryFragment) getSupportFragmentManager().findFragmentById(R.id.diary_fragment);
//        diaryFragment = (DiaryFragment) fragmentManager.findFragmentById(R.id.diary_fragment);
//        diaryFragment = (DiaryFragment) getSupportFragmentManager().findFragmentById(R.id.diary_fragment);
//        FragmentTransaction fragmentTransactionGeneral = fragmentManager.beginTransaction();

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
            android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "Food Diary");
            fragmentTransaction.commit();
        } else if (id == R.id.AddNewFoodFragment) {
            setTitle("Add New Food Fragment");
            AddNewFoodFragment fragment = new AddNewFoodFragment();
            android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "Add New Food");
            fragmentTransaction.commit();
        } else if (id == R.id.HistoryFragment) {
            setTitle("History Fragment");
            HistoryFragment fragment = new HistoryFragment();
            android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
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
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        diaryFragment.setDate(this.date);
//        fragmentTransaction.replace(R.id.diary_fragment, diaryFragment);
        fragmentTransaction.commit();

    }

    public void addDynamicDiaryFragment(){
        newDiaryFragment = new DiaryFragment();
        getFragmentManager().beginTransaction().add(R.id.drawer_layout, newDiaryFragment, "diary_fragment").commit();
        getFragmentManager().executePendingTransactions();
    }

    public String getDate(){
        return this.date;
    }

    public static String getCurrentDateAsString(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

}
