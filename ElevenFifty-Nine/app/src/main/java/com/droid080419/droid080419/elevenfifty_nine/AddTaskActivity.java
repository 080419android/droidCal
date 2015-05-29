package com.droid080419.droid080419.elevenfifty_nine;

/*
Author: Genesis Palaganas
License: 
This is a course requirement for CS 192 Software Engineering II
under the supervision of Asst. Prof. Ma. Rowena C. Solamo of the Department of Computer Science,
College of Engineering, University of the Philippines, Diliman for the AY 2014-2015.
*/

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddTaskActivity extends Activity {

     EditText eName;
     EditText eDesc;
     EditText eStartDate;
     EditText eStartTime;
     EditText eEndDate;
     EditText eEndTime;
     CheckBox cAllDay;
     SimpleDateFormat sdf;


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

          sdf = new SimpleDateFormat("MMMMM dd, yyyy - h:mm a");
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

          return super.onOptionsItemSelected(item);
     }


     public void saveData(View view){
          Date now = new Date();
          ParsePosition pos = new ParsePosition(0);
          Timestamp tNow = new Timestamp(now.getTime());

          int id = (int)now.getTime();

          Intent intent = new Intent(this, CalendarViewActivity.class);

          CalendarData data = new CalendarData(id, this);
          String start = eStartDate.getText() + " - " + eStartTime.getText();
          String end = eEndDate.getText() + " - " + eEndTime.getText();
          Log.wtf("start date input", start);

          Date startDate = sdf.parse(start,pos);

          Log.wtf("Date in parsed", startDate.toString());
          Date endDate = sdf.parse(end,pos);

          data.setStartDate(startDate);
          data.setEndDate(endDate);
          data.setName(eName.getText().toString());
          data.setDescription(eDesc.getText().toString());

          data.save();

          CalendarGlobals.calDC.addEvent(data);

          startActivity(intent);
     }
}
