package com.droid080419.droid080419.elevenfifty_nine;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.sql.Timestamp;
import java.util.Date;


public class AddTaskActivity extends ActionBarActivity {

     EditText eName;
     EditText eDesc;
     EditText eStartDate;
     EditText eStartTime;
     EditText eEndDate;
     EditText eEndTime;
     CheckBox cAllDay;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_add_task);

          eName = (EditText)findViewById(R.id.eventName);
          eDesc = (EditText)findViewById(R.id.description);
          eStartDate = (EditText)findViewById(R.id.startDate);
          eStartTime = (EditText)findViewById(R.id.startTime);
          eEndDate = (EditText)findViewById(R.id.endDate);
          eEndTime = (EditText)findViewById(R.id.endTime);
          cAllDay = (CheckBox)findViewById(R.id.allDayBox);
     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_add_task, menu);
          return true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
          // Handle action bar item clicks here. The action bar will
          // automatically handle clicks on the Home/Up button, so long
          // as you specify a parent activity in AndroidManifest.xml.
          int id = item.getItemId();

          //noinspection SimplifiableIfStatement
          if (id == R.id.action_settings) {
               return true;
          }

          return super.onOptionsItemSelected(item);
     }

     public void saveData(View view){
          Date now = new Date();
          Timestamp tNow = new Timestamp(now.getTime());
          int id = Integer.parseInt(tNow.toString());
          CalendarData data = new CalendarData(id, this.getBaseContext());
     }
}
