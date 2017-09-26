package com.example.user.foodtracker;


import android.app.DialogFragment;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryFragment extends Fragment {
    Spinner spinner;


    String date;
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";

    public DiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//         Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_diary, container, false);
        TextView dateBox = (TextView) layout.findViewById(R.id.text_current_date);
        date = getCurrentDateAsString();
        dateBox.setText(date);

        spinner = (Spinner) layout.findViewById(R.id.spinner);
//        spinner.setOnItemClickListener();

        loadSpinnerData();

        return layout;

    }

    private void loadSpinnerData() {
        // database handler
        DbHelper db = new DbHelper(getActivity().getApplicationContext());

        // Spinner Drop down elements
        List<String> foods = db.getAllFoodNames();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item, foods);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    public static String getCurrentDateAsString(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
