package com.example.user.foodtracker;


import android.app.DialogFragment;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryFragment extends Fragment {
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
        return layout;

    }

    public static String getCurrentDateAsString(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }



}
