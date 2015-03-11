package com.droid080419.droid080419.elevenfifty_nine;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by genesis on 2/13/15.
 */

public class CalendarDataController {

     private Map<String,List<CalendarData>> calDataMap;
     private Context context;
     private SimpleDateFormat sdf;

     public CalendarDataController(Context context){
          this.context = context;
          File dir = context.getFilesDir();
          calDataMap = new HashMap<String,List<CalendarData>>();
          sdf = new SimpleDateFormat("yyyy-MM-dd");
          File[] files = dir.listFiles(new FilenameFilter(){
               @Override
               public boolean accept(File dir, String name) {
                    return name.endsWith(".cevt");
               }
          });

          for(File file: files){
               CalendarData data = new CalendarData(file);
               data.loadData();
               addEvent(data);
          }

     }

     public List<CalendarData> getEventsOnDate(Date date){
          String key = sdf.format(date);
          return calDataMap.get(key);
     }

     public List<CalendarData> getAllEvents(){
          List<CalendarData> list = new ArrayList<CalendarData>();

          for(String date: calDataMap.keySet()){
               list.addAll(calDataMap.get(date));
          }

          return list;
     }

     public void addEvent(CalendarData data){
          String date = sdf.format(data.getStartDate());

          if(!calDataMap.containsKey(date))
               calDataMap.put(date, new ArrayList<CalendarData>());

          calDataMap.get(date).add(data);
     }

     public void removeEvent(CalendarData data){
          String date = sdf.format(data.getStartDate());
          List<CalendarData> list =  calDataMap.get(date);
          list.remove(data);
          Log.wtf("File",data.getDataFile().getPath());
          data.delete();


     }

     public void updateEvent(CalendarData data, Map<String,Object> updates){


          for(String key: updates.keySet()){
               Object val = updates.get(key);
               switch(CalendarDataField.valueOf(key.toUpperCase())){
                    case NAME:{
                         data.setName((String)val);

                    }break;
                    case START_DATE:{
                         data.setStartDate((Date)val);
                         //data.writeStartDateData();
                    }break;
                    case END_DATE:{
                         data.setEndDate((Date)val);
                         Log.w("END_DATE",data.getEndDate().toString());
                         //data.writeEndDateData();
                    }break;
                    case REPEAT_EVERY:{
                         data.setRepeatEvery((Date)val);
                         //data.writeRepeatEveryData();
                    }break;
                    case REPEAT_UNTIL:{
                         data.setRepeatUntil((Date)val);
                         //data.writeRepeatUntilData();
                    }break;
                    case IS_ALL_DAY:{
                         data.setIsAllDay((Boolean)val);
                         //data.writeIsAllDayData();
                    }break;
                    case DESCRIPTION:{
                         data.setDescription((String)val);
                         //data.writeDescriptionData();
                    }break;
               }

          }

          //data.writeNameData();

          //data.saveChanges();
          data.save();
     }

}
