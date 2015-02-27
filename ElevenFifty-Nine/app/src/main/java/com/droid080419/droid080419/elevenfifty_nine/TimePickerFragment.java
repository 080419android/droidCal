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

/**
 * Created by Louie on 2/27/2015.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

     AltAddTaskActivity assoc;
     int hourOfDay;
     int minute;

     @Override
     public Dialog onCreateDialog(Bundle savedInstanceState) {
          // Use the current time as the default values for the picker
          assoc = CalendarGlobals.alt;

          int hour;
          int minute;

          if(CalendarGlobals.isStartTime){
               hour = assoc.startTime.getHours();
               minute = assoc.startTime.getMinutes();
          }else{
               hour = assoc.endTime.getHours();
               minute = assoc.endTime.getMinutes();
          }

          // Create a new instance of TimePickerDialog and return it
          return new TimePickerDialog(getActivity(), this, hour, minute,
                  DateFormat.is24HourFormat(getActivity()));
     }

     @Override
     public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
          this.hourOfDay = hourOfDay;
          this.minute = minute;
     }

     @Override
     public void onDismiss(DialogInterface dialog) {
          Calendar cal = new GregorianCalendar();
          cal.set(1,0,1,this.hourOfDay,this.minute);

          if(CalendarGlobals.isStartTime){
               assoc.startTime = cal.getTime();
               assoc.eStartTime.setText(CalendarGlobals.stringTime(assoc.startTime));
          }else{
               assoc.endTime = cal.getTime();
               assoc.eEndTime.setText(CalendarGlobals.stringTime(assoc.endTime));
          }

          super.onDismiss(dialog);
     }
}
