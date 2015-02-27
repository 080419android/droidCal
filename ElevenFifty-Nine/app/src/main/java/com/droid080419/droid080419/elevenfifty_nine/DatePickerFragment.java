package com.droid080419.droid080419.elevenfifty_nine;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Louie on 2/21/2015.
 */
public class DatePickerFragment extends DialogFragment
                              implements DatePickerDialog.OnDateSetListener{

     AltAddTaskActivity assoc;     //assumes this field is always set before creating the fragment
     int year;
     int monthOfYear;
     int dayOfMonth;

     @Override
     public Dialog onCreateDialog(Bundle savedInstanceState) {
          assoc = CalendarGlobals.alt;
          int year;
          int day;
          int month;

          if(CalendarGlobals.isStartDate){
               year = assoc.startDate.getYear() + 1900;
               day = assoc.startDate.getDate();
               month = assoc.startDate.getMonth();
          }else{
               year = assoc.endDate.getYear() + 1900;
               day = assoc.endDate.getDate();
               month = assoc.endDate.getMonth();
          }

          return new DatePickerDialog(getActivity(),this,year,month,day);
     }

     @Override
     public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
          this.year = year;
          this.monthOfYear = monthOfYear;
          this.dayOfMonth = dayOfMonth;
     }

     @Override
     public void onDismiss(DialogInterface dialog) {
          Calendar cal = new GregorianCalendar();
          cal.set(this.year,this.monthOfYear,this.dayOfMonth);

          if(CalendarGlobals.isStartDate){
               assoc.startDate = cal.getTime();
               assoc.eStartDate.setText(CalendarGlobals.stringDate(assoc.startDate));
          }else{
               assoc.endDate = cal.getTime();
               assoc.eEndDate.setText(CalendarGlobals.stringDate(assoc.endDate));
          }

          super.onDismiss(dialog);
     }
}
