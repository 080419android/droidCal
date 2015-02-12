package com.droid080419.droid080419.elevenfifty_nine;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Transfer Object for the Data Access Object.
 * For use in system scheduler.
 *
 * @author Louie
 * @version Feb. 11, 2015, 09:38 AM
 */



public class CalendarData
{
     // instance variables - replace the example below with your own
     private File dataFile;
     private int id;
     private Date start_date;
     private Date end_date;
     private Boolean is_all_day;
     private Date repeat_every;
     private Date repeat_until;
     private String description;
     private String name;



     private SimpleDateFormat sdf;
     private CalendarDataAccess calDA;


     public CalendarData(File file){
          sdf  = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
          try {
               if(!file.exists())
                    file.createNewFile();

               calDA = new CalendarDataAccess(file);
               loadData();

          }catch(FileNotFoundException e){
               Log.e("FileNotFoundException", e.getStackTrace().toString());
          }catch(IOException d){
               Log.e("IOException", d.getStackTrace().toString());
          }
     }


     public CalendarData(int id, Context context){
          String fileName = "evt_"+id+".cevt";
          File file = new File(context.getFilesDir(),fileName);
          sdf  = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
          try {
               if(!file.exists())
                    file.createNewFile();

               calDA = new CalendarDataAccess(file);
               loadData();

          }catch(FileNotFoundException e){
               Log.e("FileNotFoundException", e.getStackTrace().toString());
          }catch(IOException d){
               Log.e("IOException", d.getStackTrace().toString());
          }
     }

     public void loadData(){
          ParsePosition pos = new ParsePosition(0);
          setName(calDA.getData(CalendarDataField.NAME));
          setDescription(calDA.getData(CalendarDataField.DESCRIPTION));
          setStartDate(sdf.parse(calDA.getData(CalendarDataField.START_DATE),pos));
          setEndDate(sdf.parse(calDA.getData(CalendarDataField.END_DATE),pos));
          setIsAllDay(Boolean.valueOf(calDA.getData(CalendarDataField.IS_ALL_DAY)));
          /*setRepeatEvery(sdf.parse(calDA.getData(CalendarDataField.REPEAT_EVERY),pos));
          setRepeatUntil(sdf.parse(calDA.getData(CalendarDataField.REPEAT_UNTIL),pos));*/


     }

     public String toString(){
          return start_date.toString() + "\n" +
                    end_date.toString() + "\n" +
                    is_all_day.toString() + "\n" +
                    repeat_every.toString() + "\n" +
                    repeat_until.toString() + "\n" +
                    description;
     }

     public int getId(){
          return id;
     }

     public Date getStartDate(){
          return start_date;
     }
     public Date getEndDate(){
          return end_date;
     }
     public Boolean getIsAllDay(){
          return is_all_day;
     }
     public Date getRepeatEvery(){
          return repeat_every;
     }
     public Date getRepeatUntil(){
          return repeat_until;
     }
     public String getDescription(){
          return description;
     }
     public String getName(){
          return name;
     }

     public void setId(int id){
          this.id = id;
     }

     public void setStartDate(Date start_date){
          this.start_date = start_date;
     }
     public void setEndDate(Date end_date){
          this.end_date = end_date;
     }
     public void setIsAllDay(Boolean is_all_day){
          this.is_all_day = is_all_day;
     }
     public void setRepeatEvery(Date repeat_every){
          this.repeat_every = repeat_every;
     }
     public void setRepeatUntil(Date repeat_until){
          this.repeat_until = repeat_until;
     }
     public void setDescription(String description){
          this.description = description;
     }
     public void setName(String name){
          this.name = name;
     }

     public void save(){
          calDA.writeData(CalendarDataField.ID, Integer.toString(getId()));
          calDA.writeData(CalendarDataField.NAME,getName());
          calDA.writeData(CalendarDataField.DESCRIPTION, getDescription());
          calDA.writeData(CalendarDataField.START_DATE,getStartDate().toString());
          calDA.writeData(CalendarDataField.END_DATE,getEndDate().toString());
          calDA.writeData(CalendarDataField.IS_ALL_DAY, getIsAllDay().toString());
         /*calDA.writeData(CalendarDataField.REPEAT_EVERY,getRepeatEvery().toString());
         calDA.writeData(CalendarDataField.REPEAT_UNTIL,getRepeatUntil().toString());*/
     }
}
