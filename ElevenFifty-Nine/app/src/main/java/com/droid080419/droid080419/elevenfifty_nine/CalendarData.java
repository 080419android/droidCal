package com.droid080419.droid080419.elevenfifty_nine;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
Author: Louie Buera
License: 
This is a course requirement for CS 192 Software Engineering II
under the supervision of Asst. Prof. Ma. Rowena C. Solamo of the Department of Computer Science,
College of Engineering, University of the Philippines, Diliman for the AY 2014-2015.
 */



public class CalendarData
{
     // instance variables - replace the example below with your own
     private File dataFile;
     private String id;
     private Date start_date;
     private Date end_date;
     private Boolean is_all_day;
     private Date repeat_every;
     private Date repeat_until;
     private String description;
     private String name;

     private SimpleDateFormat sdf;
     private CalendarDataAccess calDA;

     //Uses an existing file to create a CalendarData Object and sets write to false
     public CalendarData(File file){
          sdf  = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
          try {
               if(!file.exists())
                    file.createNewFile();

               calDA = new CalendarDataAccess(file,false);


          }catch(FileNotFoundException e){
               Log.e("FileNotFoundException", e.getStackTrace().toString());
          }catch(IOException d){
               Log.e("IOException", d.getStackTrace().toString());
          }
     }

     //Creates a new data file to using an id input and sets write to true
     public CalendarData(int id, Context context){
          String fileName = "evt_"+id+".cevt";
          Log.wtf("FileName",fileName);
          File file = new File(context.getFilesDir(),fileName);
          setId(Integer.toString(id));
          sdf  = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
          try {
               if(!file.exists())
                    file.createNewFile();

               calDA = new CalendarDataAccess(file,true);

          }catch(FileNotFoundException e){
               Log.e("FileNotFoundException", e.getStackTrace().toString());
          }catch(IOException d){
               Log.e("IOException", d.getStackTrace().toString());
          }
     }

     //Loads the Calendar data from the file
     public void loadData(){
          ParsePosition pos = new ParsePosition(0);
          dataFile = new File(CalendarGlobals.dir,"evt_"+calDA.getData(CalendarDataField.ID) + ".cevt");
          setId(calDA.getData(CalendarDataField.ID));
          setName(calDA.getData(CalendarDataField.NAME));
          Log.wtf("Description",calDA.getData(CalendarDataField.DESCRIPTION));
          setDescription(calDA.getData(CalendarDataField.DESCRIPTION));
          Log.wtf("Start date",calDA.getData(CalendarDataField.START_DATE));
          setStartDate(sdf.parse(calDA.getData(CalendarDataField.START_DATE),pos));
          setEndDate(sdf.parse(calDA.getData(CalendarDataField.END_DATE),pos));
          setIsAllDay(Boolean.valueOf(calDA.getData(CalendarDataField.IS_ALL_DAY)));
          setRepeatEvery(sdf.parse(calDA.getData(CalendarDataField.REPEAT_EVERY),pos));
          setRepeatUntil(sdf.parse(calDA.getData(CalendarDataField.REPEAT_UNTIL),pos));


     }

     //Returns the String equivalent of the Object
     public String toString(){
          return start_date.toString() + "\n" +
                    end_date.toString() + "\n" +
                    is_all_day.toString() + "\n" +
                    repeat_every.toString() + "\n" +
                    repeat_until.toString() + "\n" +
                    description;
     }

     //Getter functions
     public String getId(){
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
     public File getDataFile(){
          return dataFile;
     }

     //Setter functions
     public void setId(String id){
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

     //Saves the changes to the file
     public void save(){
          calDA.writeData(CalendarDataField.ID, getId());
          calDA.writeData(CalendarDataField.NAME,getName());
          calDA.writeData(CalendarDataField.DESCRIPTION, getDescription());
          calDA.writeData(CalendarDataField.START_DATE,getStartDate().toString());
          calDA.writeData(CalendarDataField.END_DATE,getEndDate().toString());
          calDA.writeData(CalendarDataField.IS_ALL_DAY, getIsAllDay().toString());
          calDA.writeData(CalendarDataField.REPEAT_EVERY,getRepeatEvery().toString());
          calDA.writeData(CalendarDataField.REPEAT_UNTIL,getRepeatUntil().toString());
          calDA.save();
     }

     public void saveChanges(){
          calDA.save();
     }

     public void writeIDData(){
          calDA.writeData(CalendarDataField.ID,getId());
     }

     public void writeNameData(){
          calDA.writeData(CalendarDataField.NAME,getName());
     }

     public void writeDescriptionData(){
          calDA.writeData(CalendarDataField.DESCRIPTION,getDescription());
     }

     public void writeStartDateData(){
          calDA.writeData(CalendarDataField.START_DATE,getStartDate().toString());
     }

     public void writeEndDateData(){
          calDA.writeData(CalendarDataField.END_DATE,getEndDate().toString());
     }

     public void writeIsAllDayData(){
          calDA.writeData(CalendarDataField.IS_ALL_DAY,getIsAllDay().toString());
     }

     public void writeRepeatEveryData(){
          calDA.writeData(CalendarDataField.REPEAT_EVERY,getRepeatEvery().toString());
     }

     public void writeRepeatUntilData(){
          calDA.writeData(CalendarDataField.REPEAT_UNTIL,getRepeatUntil().toString());
     }

     //Deletes the file
     public void delete(){
          //Log.wtf("dataFile",dataFile.toString());
          Boolean b = dataFile.delete();
          //Log.wtf("deleted",b.toString());

     }

}
