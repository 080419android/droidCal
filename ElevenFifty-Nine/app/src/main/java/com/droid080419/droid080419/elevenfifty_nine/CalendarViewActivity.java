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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class  CalendarViewActivity extends Activity {

     ListView events;
     ArrayAdapter<CalendarData> eventsAdapter;
     List<CalendarData> eventsList;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          CalendarGlobals.calDC = new CalendarDataController(this);
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_calendar_view);
          loadEvents();
          events = (ListView)findViewById(R.id.eventsList);
          /*eventsAdapter=new ArrayAdapter<CalendarData>(this,R.layout.activity_calendar_view,eventsList);
          events.setAdapter(eventsAdapter);*/
          ArrayAdapter adapt = new EventLineAdapter();
          events.setAdapter(adapt);
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
               eventName.setText(cal.getName());

               if(cal.getStartDate() != null){
                    TextView startDate = (TextView)itemView.findViewById(R.id.start_date_view);
                    startDate.setText(cal.getStartDate().toString());
               }

               if(cal.getEndDate() != null){
                    TextView endDate = (TextView)itemView.findViewById(R.id.end_date_view);
                    endDate.setText(cal.getEndDate().toString());
               }



               return itemView;
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

          if (id == R.id.action_settings) {
               return true;
          }

          return super.onOptionsItemSelected(item);
     }

     public void loadEvents(){

          eventsList = CalendarGlobals.calDC.getAllEvents();

     }
}
