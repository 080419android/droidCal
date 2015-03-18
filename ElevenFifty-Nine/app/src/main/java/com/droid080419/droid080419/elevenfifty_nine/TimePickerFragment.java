package com.droid080419.droid080419.elevenfifty_nine;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

/*
Author: Louie Buera
License: 
This is a course requirement for CS 192 Software Engineering II
under the supervision of Asst. Prof. Ma. Rowena C. Solamo of the Department of Computer Science,
College of Engineering, University of the Philippines, Diliman for the AY 2014-2015.
*/

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

     TaskEditor assoc;   //assumes CalendarGlobals.alt is always set before creating the fragment
     int hourOfDay;
     int minute;

     @Override
     public Dialog onCreateDialog(Bundle savedInstanceState) {
          // Use the current time as the default values for the picker
          assoc = CalendarGlobals.alt;

          int hour;
          int minute;

          //checks if start or end time, to determine appropriate data sources
          if(CalendarGlobals.isStartTime){
               hour = assoc.getStartTime().getHours();
               minute = assoc.getStartTime().getMinutes();
          }else{
               hour = assoc.getEndTime().getHours();
               minute = assoc.getEndTime().getMinutes();
          }

          // Create a new instance of TimePickerDialog and return it
          return new TimePickerDialog(getActivity(), this, hour, minute,
                  DateFormat.is24HourFormat(getActivity()));
     }

     @Override
     public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
          //updates fields when time is set
          this.hourOfDay = hourOfDay;
          this.minute = minute;
     }

     @Override
     public void onDismiss(DialogInterface dialog) {
          //updates data in assoc when this fragment is closed
          Calendar cal = new GregorianCalendar();
          cal.set(1,0,1,this.hourOfDay,this.minute);

          if(CalendarGlobals.isStartTime){
               assoc.setStartTime(cal.getTime());
               assoc.getEStartTime().setText(CalendarGlobals.stringTime(assoc.getStartTime()));
          }else{
               assoc.setEndTime(cal.getTime());
               assoc.getEEndTime().setText(CalendarGlobals.stringTime(assoc.getEndTime()));
          }

          super.onDismiss(dialog);
     }
}
