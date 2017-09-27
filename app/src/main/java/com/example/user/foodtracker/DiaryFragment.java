package com.example.user.foodtracker;


import android.app.DialogFragment;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
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
    MainActivity mainActivity;
    TextView dateBox;


    public DiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//         Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_diary, container, false);
//        TextView dateBox = (TextView) layout.findViewById(R.id.text_current_date);
//        date = getCurrentDateAsString();
//        dateBox.setText(date);


//        dateBox.setText(mainActivity.getDa);
        spinner = (Spinner) layout.findViewById(R.id.spinner);
//        spinner.setOnItemClickListener();

        loadSpinnerData();

        return layout;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        dateBox = (TextView) getView().findViewById(R.id.text_current_date);
        dateBox.setText(mainActivity.getDate());
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

    @Override
    public void onResume(){
        super.onResume();
        Log.d("onResume is called", mainActivity.getDate());
        dateBox.setText(mainActivity.getDate());
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        dateBox.setText(date);
    }

}
