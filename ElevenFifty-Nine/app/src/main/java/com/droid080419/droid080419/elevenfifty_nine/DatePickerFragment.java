package com.droid080419.droid080419.elevenfifty_nine;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Louie on 2/21/2015.
 */
public class DatePickerFragment extends DialogFragment
                              implements DatePickerDialog.OnDateSetListener{

     @Override
     public Dialog onCreateDialog(Bundle savedInstanceState) {
          final Calendar c = Calendar.getInstance();
          int year = c.get(Calendar.YEAR);
          int day = c.get(Calendar.DAY_OF_MONTH);
          int month = c.get(Calendar.MONTH);


          return new DatePickerDialog(getActivity(),this,year,month,day);
     }

     @Override
     public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

     }
}
