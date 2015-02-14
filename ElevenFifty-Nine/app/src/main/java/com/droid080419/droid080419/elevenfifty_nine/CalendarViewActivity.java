package com.droid080419.droid080419.elevenfifty_nine;

import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CalendarViewActivity extends ActionBarActivity {

     ListView events;
     ArrayAdapter<CalendarData> eventsAdapter;
     List<CalendarData> eventsList;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          CalendarGlobals.calDC = new CalendarDataController(this);
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_calendar_view);

          events = (ListView)findViewById(R.id.eventsList);
          eventsAdapter=new ArrayAdapter<CalendarData>(this,R.layout.activity_calendar_view,eventsList);
          events.setAdapter(eventsAdapter);
     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_calendar_view, menu);
          return true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
          // Handle action bar item clicks here. The action bar will
          // automatically handle clicks on the Home/Up button, so long
          // as you specify a parent activity in AndroidManifest.xml.
          int id = item.getItemId();

          //noinspection SimplifiableIfStatement

          switch(id){
               case R.id.action_add:
                    Intent intent = new Intent(this, AddTaskActivity.class);
                    startActivity(intent);
          }

          if (id == R.id.action_settings) {
               return true;
          }

          return super.onOptionsItemSelected(item);
     }

     public void loadEvents(){

          eventsList = CalendarGlobals.calDC.getAllEvents();

     }
}
