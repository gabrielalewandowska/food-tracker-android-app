package com.example.user.foodtracker;


import android.app.DialogFragment;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    String date;
    MainActivity mainActivity;
    TextView dateBox;
    EditText quantityBox;
    Button addEntry;


    public DiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//         Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_diary, container, false);

        spinner = (Spinner) layout.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        loadSpinnerData();

        quantityBox = (EditText) layout.findViewById(R.id.edit_quantity);
        addEntry = (Button) layout.findViewById(R.id.btn_add_entry);

        return layout;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        dateBox = (TextView) getView().findViewById(R.id.text_current_date);
        dateBox.setText(mainActivity.getDate());

        addEntry.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String value = quantityBox.getText().toString();
                int finalValue = Integer.parseInt(value);
                mainActivity.setQuantity(finalValue);
                Toast.makeText(mainActivity, "Entry saved!", Toast.LENGTH_SHORT).show();
                Log.d("Main quantity: ", mainActivity.getQuantity().toString());
            }
        });
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String foodName = parent.getItemAtPosition(pos).toString();
        mainActivity.setFoodName(foodName);
        Log.d("MainActivity foodName: ", mainActivity.getFoodName());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
