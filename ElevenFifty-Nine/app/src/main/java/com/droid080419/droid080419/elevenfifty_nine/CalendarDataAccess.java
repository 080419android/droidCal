package com.droid080419.droid080419.elevenfifty_nine;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by genesis on 2/13/15.
 */
public class CalendarDataAccess {

     private File dataFile;
     private Map<CalendarDataField, String> dataMap;
     private PrintWriter fileWriter;

     public CalendarDataAccess(File data, boolean write) throws FileNotFoundException{
          this.dataFile = data;
          this.dataMap = loadData();

          if(write)
               this.fileWriter = new PrintWriter(dataFile);
     }

     private Map<CalendarDataField,String> loadData()  throws FileNotFoundException, NullPointerException{
          Map<CalendarDataField, String> ret = new EnumMap<CalendarDataField, String>(CalendarDataField.class);
          String str = "false";
          Scanner dataScanner = new Scanner(this.dataFile);
          Log.w("What ", "What the fuck");
          while(dataScanner.hasNextLine()){
               String curLine = dataScanner.nextLine();
               Log.w("curLine ", curLine);
               String[] split = curLine.split("::");
               Log.w("Line read", split[0] + "::" + split[1]);
               ret.put(CalendarDataField.valueOf(split[0]),split[1]);
          }

          return ret;
     }

     public String getData(CalendarDataField field){
          return this.dataMap.get(field);
     }

     public void writeData(CalendarDataField field, String data){
          this.dataMap.put(field,data);
          Log.w("Field Generated: " , field.toString() +"::"+data);
          this.fileWriter.println(field.toString() +"::"+data);
     }

     public void save(){
          fileWriter.close();
     }


}
