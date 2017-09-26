package com.example.user.foodtracker;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    MainActivity mainActivity;


//    public interface OnSetSelectedDateListener {
//        public void setSelectedDate(String date);
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mainActivity = (MainActivity) context;
        Log.d("onAttach", "hi it's me");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {


        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(c.getTime());
        mainActivity.setSelectedDate(formattedDate);

    }



}
