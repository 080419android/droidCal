package com.droid080419.droid080419.elevenfifty_nine;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

import java.text.SimpleDateFormat;

/*
Author: Louie Buera
License: 
This is a course requirement for CS 192 Software Engineering II
under the supervision of Asst. Prof. Ma. Rowena C. Solamo of the Department of Computer Science,
College of Engineering, University of the Philippines, Diliman for the AY 2014-2015.
*/

public class AltAddTaskActivity extends FragmentActivity implements TaskEditor{
     EditText eName;
     EditText eDesc;
     CheckBox cAllDay;
     SimpleDateFormat sdf;
     TextView eStartDate;
     TextView eStartTime;
     TextView eEndDate;
     TextView eEndTime;
     Date startDate;
     Date endDate;
     Date startTime;
     Date endTime;
     LatLng coords;

     @Override
     protected void onCreate(Bundle savedInstanceState) {   //initializing the activity
          super.onCreate(savedInstanceState);

          //setting layout
          setContentView(R.layout.activity_alt_add_task);

          //getting views
          eName = (EditText)findViewById(R.id.eventName);
          eDesc = (EditText)findViewById(R.id.description);
          eStartDate = (TextView)findViewById(R.id.startDate);
          eStartTime = (TextView)findViewById(R.id.startTime);
          eEndDate = (TextView)findViewById(R.id.endDate);
          eEndTime = (TextView)findViewById(R.id.endTime);
          cAllDay = (CheckBox)findViewById(R.id.allDayBox);

          //initializing date and time fields to current time
          startDate = new Date();
          endDate = new Date();
          startTime = new Date();
          endTime = new Date();

          //updating views to date and time fields
          eStartDate.setText(CalendarGlobals.stringDate(startDate));
          eStartTime.setText(CalendarGlobals.stringTime(startTime));
          eEndDate.setText(CalendarGlobals.stringDate(endDate));
          eEndTime.setText(CalendarGlobals.stringTime(endTime));

          sdf = new SimpleDateFormat("MMMMM dd, yyyy - h:mm a");
     }

     @Override
     protected void onResume() {
          super.onResume();
          if(CalendarGlobals.locationSet) {
               coords = new LatLng(CalendarGlobals.gps.latitude, CalendarGlobals.gps.longitude);
               CalendarGlobals.locationSet = false;
          }
     }

     public void setLocation(View view){
          CalendarGlobals.alt = this;
          Intent intent = new Intent(this,AddTaskMapActivity.class);
          CalendarGlobals.locationSet = false;
          startActivity(intent);
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
          // as you specify a parent activity in AndroidManiAltAddTaskActivityfest.xml.
          int id = item.getItemId();

          //noinspection SimplifiableIfStatement
          if (id == R.id.action_settings) {
               return true;
          }

          return super.onOptionsItemSelected(item);
     }

     public void startTimeDialog(View view){ //opens a dialog fragment to input start time
          CalendarGlobals.alt = this;
          CalendarGlobals.isStartTime = true;
          DialogFragment newFrag = new TimePickerFragment();
          newFrag.show(getFragmentManager(),"timePicker");
     }

     public void endTimeDialog(View view){   //opens a dialog fragment to input end time


          CalendarGlobals.alt = this;
          CalendarGlobals.isStartTime = false;
          DialogFragment newFrag = new TimePickerFragment();
          newFrag.show(getFragmentManager(),"timePicker");
     }

     public void startDateDialog(View view){ //opens a dialog fragment to input start date
          CalendarGlobals.alt = this;
          CalendarGlobals.isStartDate = true;
          DialogFragment newFrag = new DatePickerFragment();
          newFrag.show(getFragmentManager(),"datePicker");
     }

     public void endDateDialog(View view){   //opens a dialog fragment to input end date
          CalendarGlobals.alt = this;
          CalendarGlobals.isStartDate = false;
          DialogFragment newFrag = new DatePickerFragment();
          newFrag.show(getFragmentManager(),"datePicker");
     }

     public void saveData(View view){
          //saves current data fields to calendar, is called when 'save' button is tapped

          Date junk = new Date();  //junk data to fill in empty fields

          //construct new CalendarData object to add to calendar
          CalendarData data = new CalendarData(((int)junk.getTime()),this);

          //set fields of data object
          data.setStartDate(CalendarGlobals.merge(startDate,startTime));
          data.setEndDate(CalendarGlobals.merge(endDate,endTime));
          data.setName(eName.getText().toString());
          data.setDescription(eDesc.getText().toString());
          data.setRepeatEvery(junk);
          data.setRepeatUntil(junk);
          data.setIsAllDay(true);

          //save the data
          data.save();

          //add task to calendar
          CalendarGlobals.calDC.addEvent(data);

          //end the activity, returns to CalendarViewActivity
          finish();
     }

     @Override
     public Date getStartDate() {
          return startDate;
     }

     @Override
     public Date getStartTime() {
          return startTime;
     }

     @Override
     public Date getEndDate() {
          return endDate;
     }

     @Override
     public Date getEndTime() {
          return endTime;
     }

     @Override
     public TextView getEStartDate() {
          return eStartDate;
     }

     @Override
     public TextView getEStartTime() {
          return eStartTime;
     }

     @Override
     public TextView getEEndDate() {
          return eEndDate;
     }

     @Override
     public TextView getEEndTime() {
          return eEndTime;
     }

     @Override
     public void setStartTime(Date startTime) {
          this.startTime = startTime;
     }

     @Override
     public void setStartDate(Date startDate) {
          this.startDate = startDate;
     }

     @Override
     public void setEndTime(Date endTime) {
          this.endTime = endTime;
     }

     @Override
     public void setEndDate(Date endDate) {
          this.endDate = endDate;
     }
}
