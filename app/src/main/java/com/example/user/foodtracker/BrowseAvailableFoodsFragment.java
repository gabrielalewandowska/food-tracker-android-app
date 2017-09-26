package com.example.user.foodtracker;


import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseAvailableFoodsFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {
    Spinner spinner;

    public BrowseAvailableFoodsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_browse_available_foods, container, false);
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


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
