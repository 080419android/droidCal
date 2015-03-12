package com.droid080419.droid080419.elevenfifty_nine;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by genesis on 2/13/15.
 */
public class CalendarGlobals {

     public static CalendarDataController calDC;
     public static List<CalendarData> eventsList;
     public static TaskEditor alt;
     public static Boolean isStartDate;
     public static Boolean isStartTime;
     public static File dir;
     public static CalendarData being_edited;

     public static String stringDate(Date dat){
          //returns a string representing the date portion of a date object
          return dat.toString().substring(0,10) + dat.toString().substring(29);
     }

     public static String stringTime(Date dat) {
          //returns a string representing the time portion of a date object
          int hours = dat.getHours();

          if(hours < 12){
               if(hours == 0)
                    return "12:" + Integer.toString(dat.getMinutes()) + " AM";
               return Integer.toString(hours) + ":" + Integer.toString(dat.getMinutes()) + " AM";
          }else if(hours == 12){
               if(dat.getMinutes() == 0)
                    return "12 NN";
               return "12:" + Integer.toString(dat.getMinutes()) + "PM";
          }else{
               return Integer.toString(hours - 12)
                       + ":" + Integer.toString(dat.getMinutes()) + " PM";
          }

     }

     public static Date merge(Date date, Date time){
          //combines 2 date objects, representing date and time respectively, into 1 date object

          Date holder = new Date();
          holder.setDate(date.getDate());
          holder.setMonth(date.getMonth());
          holder.setYear(date.getYear());
          holder.setMinutes(time.getMinutes());
          holder.setHours(time.getHours());
          holder.setSeconds(time.getSeconds());
          return holder;
     }
}
