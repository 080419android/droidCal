package com.droid080419.droid080419.elevenfifty_nine;

import android.content.Context;

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
               addEvent(data);
          }

     }

     public List<CalendarData> getEventsOnDate(Date date){
          String key = sdf.format(date);
          return calDataMap.get(key);
     }

     public void addEvent(CalendarData data){
          String date = sdf.format(data.getStartDate());

          if(!calDataMap.containsKey(date))
               calDataMap.put(date, new ArrayList<CalendarData>());

          calDataMap.get(date).add(data);
     }

}
