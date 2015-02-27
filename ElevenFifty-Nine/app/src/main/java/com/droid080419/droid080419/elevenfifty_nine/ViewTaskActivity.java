package com.droid080419.droid080419.elevenfifty_nine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Louie on 2/21/2015.
 */
public class ViewTaskActivity extends Activity {
     CalendarData dat;
     int position;
     TextView name;
     TextView startDate;
     TextView endDate;
     TextView startTime;
     TextView endTime;
     TextView desc;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_view_task);
          Intent intent = getIntent();
          position = intent.getIntExtra(CalendarViewActivity.data_holder,-1);
          dat = CalendarGlobals.eventsList.get(position);

          //get text views
          name = (TextView)findViewById(R.id.view_event_name);
          startDate = (TextView)findViewById(R.id.view_start_date);
          endDate = (TextView)findViewById(R.id.view_end_date);
          startTime = (TextView)findViewById(R.id.view_start_time);
          endTime = (TextView)findViewById(R.id.view_end_time);
          desc = (TextView)findViewById(R.id.view_description);

          name.setText(dat.getName());
          startDate.setText(CalendarGlobals.stringDate(dat.getStartDate()));
          startTime.setText(CalendarGlobals.stringTime(dat.getStartDate()));
          if(dat.getEndDate() != null) {
               endDate.setText(CalendarGlobals.stringDate(dat.getEndDate()));
               endTime.setText(CalendarGlobals.stringTime(dat.getEndDate()));
          }
          desc.setText(dat.getDescription());

     }

     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_view_task, menu);
          return true;
     }

     public void editData(View view){

     }

     public void deleteData(View view){
          CalendarGlobals.calDC.removeEvent(dat);
          super.finish();
     }

}