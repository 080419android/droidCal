package com.droid080419.droid080419.elevenfifty_nine;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
//import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class  CalendarViewActivity extends Activity {

     ListView events;
     ArrayAdapter adapt;
     List<CalendarData> eventsList;
     public final static String data_holder = "com.droid080419.elevenfifty_nine.data_holder";


     @Override
     protected void onCreate(Bundle savedInstanceState) {
          CalendarGlobals.calDC = new CalendarDataController(this);
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_calendar_view);
          loadEvents();
          events = (ListView)findViewById(R.id.eventsList);
          /*eventsAdapter=new ArrayAdapter<CalendarData>(this,R.layout.activity_calendar_view,eventsList);
          events.setAdapter(eventsAdapter);*/

          //fills list  with items
          adapt = new EventLineAdapter();
          events.setAdapter(adapt);

          registerClickCallback(); //make items in list clickable
          CalendarGlobals.eventsList = eventsList;
     }

     @Override
     protected void onResume() {
          super.onResume();
          loadEvents();
          events = (ListView)findViewById(R.id.eventsList);
          events.setAdapter(adapt);

          registerClickCallback();


     }

     public void registerClickCallback(){
          events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CalendarData cal = eventsList.get(position);
                    Intent intent = new Intent(CalendarViewActivity.this,ViewTaskActivity.class);
                    intent.putExtra(data_holder,position);
                    startActivity(intent);
               }
          });
     }

     private class EventLineAdapter extends ArrayAdapter<CalendarData>{

          public EventLineAdapter(){
               super(CalendarViewActivity.this,R.layout.event_line_view,eventsList);
          }

          @Override
          public View getView(int position, View convertView, ViewGroup parent) {
               //checking against null view
               View itemView = convertView;
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
                    startDate.setText("Start Date and Time:" + cal.getStartDate().toString());
               }

               if(cal.getEndDate() != null){
                    TextView endDate = (TextView)itemView.findViewById(R.id.end_date_view);
                    endDate.setText("End Date and Time:" + cal.getEndDate().toString());
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
                    Intent intent = new Intent(this, AddTaskActivity.class);
                    startActivity(intent);
          }

          if(id == R.id.action_add_alt){
               Intent intent = new Intent(this,AltAddTaskActivity.class);
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
