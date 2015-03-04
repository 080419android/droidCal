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

     /*public static String stringDate(Date dat){
          String hold = "";
          int i = dat.getMonth();
          if(i == 0){
               hold = "January ";
          }else if(i == 1){
               hold = "February ";
          }else if(i == 2){
               hold = "March ";
          }else if(i == 3){
               hold = "April ";
          }else if(i == 4){
               hold = "May ";
          }else if(i == 5){
               hold = "June ";
          }else if(i == 6){
               hold = "July ";
          }else if(i == 7){
               hold = "August ";
          }else if(i == 8){
               hold = "September ";
          }else if(i == 9){
               hold = "October ";
          }else if(i == 10){
               hold = "November ";
          }else if(i == 11){
               hold = "December "
          }

          //append day of the month
          hold += Integer.toString(dat.getDate()) + ",";

          //append year
          hold += Integer.toString(dat.getYear() + 1900);

          return hold;
     }*/

     /*public static String stringTime(Date dat){
          String hold = "";

          hold += ;
     }*/

     public static String stringDate(Date dat){
          return dat.toString().substring(0,10) + dat.toString().substring(29);
     }

     /*public static String stringTime(Date dat){
          return dat.toString().substring(12,19);
     }*/

     public static String stringTime(Date dat) {
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
