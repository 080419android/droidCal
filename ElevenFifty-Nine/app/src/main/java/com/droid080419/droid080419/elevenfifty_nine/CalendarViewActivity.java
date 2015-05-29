package com.droid080419.droid080419.elevenfifty_nine;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
//import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/*
Author: Genesis Palaganas
License: 
This is a course requirement for CS 192 Software Engineering II
under the supervision of Asst. Prof. Ma. Rowena C. Solamo of the Department of Computer Science,
College of Engineering, University of the Philippines, Diliman for the AY 2014-2015.
*/

/*

Main activity seen by user
Displays tasks in calendar to user, which can be viewed by tapping

 */
public class  CalendarViewActivity extends Activity {

     ListView events;
     ArrayAdapter adapt;
     List<CalendarData> eventsList;
     public final static String data_holder = "com.droid080419.elevenfifty_nine.data_holder";


     @Override
     protected void onCreate(Bundle savedInstanceState) {
          CalendarGlobals.calDC = new CalendarDataController(this);
          super.onCreate(savedInstanceState);
          //set layout
          setContentView(R.layout.activity_calendar_view);
          //load all events into calendar
          loadEvents();
          CalendarGlobals.dir = this.getFilesDir();
     }

     @Override
     protected void onResume() {
          super.onResume();
          loadEvents();
          events = (ListView)findViewById(R.id.eventsList);
          adapt = new EventLineAdapter();
          events.setAdapter(adapt);

          registerClickCallback();

          CalendarGlobals.eventsList = eventsList;
     }

     public void registerClickCallback(){
          //used to respond to taps on task items on ListView
          events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //starts TaskViewActivity
                    CalendarData cal = eventsList.get(position);
                    Intent intent = new Intent(CalendarViewActivity.this,ViewTaskActivity.class);
                    intent.putExtra(data_holder,position);
                    startActivity(intent);
               }
          });
     }

     private class EventLineAdapter extends ArrayAdapter<CalendarData>{
          //provides data to event_line_view in ListView

          public EventLineAdapter(){
               super(CalendarViewActivity.this,R.layout.event_line_view,eventsList);
          }

          @Override
          public View getView(int position, View convertView, ViewGroup parent) {
               //checking against null view
               View itemView = convertView;
               SimpleDateFormat  sdf  = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
               if(itemView == null){
                    itemView = getLayoutInflater().inflate(R.layout.event_line_view,parent,false);
               }

               //find the event
               CalendarData cal = eventsList.get(position);

               //fill the view
               TextView eventName = (TextView)itemView.findViewById(R.id.event_name_view);
               eventName.setText("Event Name:" + cal.getName());

               if(cal.getStartDate() != null){
                    TextView startDate = (TextView)itemView.findViewById(R.id.start_date_view);
                    startDate.setText("Start Date and Time: " + sdf.format(cal.getStartDate()));
               }

               if(cal.getEndDate() != null){
                    TextView endDate = (TextView)itemView.findViewById(R.id.end_date_view);
                    endDate.setText("End Date and Time: " + sdf.format(cal.getEndDate()));
               }



               return itemView;
          }

          public CalendarData getItem(int position){
               return eventsList.get(position);
          }

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
                    Intent intent = new Intent(this, AltAddTaskActivity.class);
                    startActivity(intent);

          }



          if(id == R.id.action_about){
              Intent intent = new Intent(this,AboutActivity.class);
              startActivity(intent);
          }


          return super.onOptionsItemSelected(item);
     }

     public void loadEvents(){
          //loads events from files
          eventsList = CalendarGlobals.calDC.getAllEvents();

     }



}
