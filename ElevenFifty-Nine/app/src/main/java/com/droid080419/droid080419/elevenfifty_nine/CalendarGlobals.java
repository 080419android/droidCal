package com.droid080419.droid080419.elevenfifty_nine;

import java.util.Date;
import java.util.List;

/**
 * Created by genesis on 2/13/15.
 */
public class CalendarGlobals {

     public static CalendarDataController calDC;
     public static List<CalendarData> eventsList;

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
          return dat.toString().substring(0,10) + dat.toString().substring(25);
     }

     public static String stringTime(Date dat){
          return dat.toString().substring(12,19);
     }
}
