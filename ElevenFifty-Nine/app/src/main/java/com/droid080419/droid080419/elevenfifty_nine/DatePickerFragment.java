package com.droid080419.droid080419.elevenfifty_nine;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

/*
Author: Louie Buera
License: 
This is a course requirement for CS 192 Software Engineering II
under the supervision of Asst. Prof. Ma. Rowena C. Solamo of the Department of Computer Science,
College of Engineering, University of the Philippines, Diliman for the AY 2014-2015.
*/

public class DatePickerFragment extends DialogFragment
                              implements DatePickerDialog.OnDateSetListener{

     TaskEditor assoc;     //assumes CalendarGlobals.alt is always set before creating the fragment
     int year;
     int monthOfYear;
     int dayOfMonth;

     @Override
     public Dialog onCreateDialog(Bundle savedInstanceState) {
          assoc = CalendarGlobals.alt;
          int year;
          int day;
          int month;

          //checks if start or end date, to determine appropriate data sources
          if(CalendarGlobals.isStartDate){
               year = assoc.getStartDate().getYear() + 1900;
               day = assoc.getStartDate().getDate();
               month = assoc.getStartDate().getMonth();
          }else{
               year = assoc.getEndDate().getYear() + 1900;
               day = assoc.getEndDate().getDate();
               month = assoc.getEndDate().getMonth();
          }

          return new DatePickerDialog(getActivity(),this,year,month,day);
     }

     @Override
     public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
          //updates fields when date is set
          this.year = year;
          this.monthOfYear = monthOfYear;
          this.dayOfMonth = dayOfMonth;
     }

     @Override
     public void onDismiss(DialogInterface dialog) {
          //updates data in assoc when this fragment is closed
          Calendar cal = new GregorianCalendar();
          cal.set(this.year,this.monthOfYear,this.dayOfMonth);

          if(CalendarGlobals.isStartDate){
               assoc.setStartDate(cal.getTime());
               assoc.getEStartDate().setText(CalendarGlobals.stringDate(assoc.getStartDate()));
          }else{
               assoc.setEndDate(cal.getTime());
               assoc.getEEndDate().setText(CalendarGlobals.stringDate(assoc.getEndDate()));
          }

          super.onDismiss(dialog);
     }
}
