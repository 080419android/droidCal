package com.droid080419.droid080419.elevenfifty_nine;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.text.SimpleDateFormat;

/**
 * Created by Louie on 2/26/2015.
 */
public class EditTaskActivity extends FragmentActivity implements TaskEditor{
     CalendarData dat;
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

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_alt_add_task);

          Intent intent = getIntent();
          int position = intent.getIntExtra(CalendarViewActivity.data_holder,-1);

          dat = CalendarGlobals.eventsList.get(position);

          eName = (EditText)findViewById(R.id.eventName);
          eDesc = (EditText)findViewById(R.id.description);
          eStartDate = (TextView)findViewById(R.id.startDate);
          eStartTime = (TextView)findViewById(R.id.startTime);
          eEndDate = (TextView)findViewById(R.id.endDate);
          eEndTime = (TextView)findViewById(R.id.endTime);
          cAllDay = (CheckBox)findViewById(R.id.allDayBox);

          startDate = dat.getStartDate();
          endDate = dat.getEndDate();
          startTime = dat.getStartDate();
          endTime = dat.getEndDate();

          eStartDate.setText(CalendarGlobals.stringDate(startDate));
          eStartTime.setText(CalendarGlobals.stringTime(startTime));
          eEndDate.setText(CalendarGlobals.stringDate(endDate));
          eEndTime.setText(CalendarGlobals.stringTime(endTime));

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
          if (id == R.id.action_settings) {
               return true;
          }

          return super.onOptionsItemSelected(item);
     }

     public void startTimeDialog(View view){
          CalendarGlobals.alt = this;
          CalendarGlobals.isStartTime = true;
          DialogFragment newFrag = new TimePickerFragment();
          newFrag.show(getFragmentManager(),"timePicker");
     }

     public void endTimeDialog(View view){
          CalendarGlobals.alt = this;
          CalendarGlobals.isStartTime = false;
          DialogFragment newFrag = new TimePickerFragment();
          newFrag.show(getFragmentManager(),"timePicker");
     }

     public void startDateDialog(View view){
          CalendarGlobals.alt = this;
          CalendarGlobals.isStartDate = true;
          DialogFragment newFrag = new DatePickerFragment();
          newFrag.show(getFragmentManager(),"datePicker");
     }

     public void endDateDialog(View view){
          CalendarGlobals.alt = this;
          CalendarGlobals.isStartDate = false;
          DialogFragment newFrag = new DatePickerFragment();
          newFrag.show(getFragmentManager(),"datePicker");
     }

     public void saveData(View view){
          /*Date junk = new Date();  //junk data to fill in empty fields

          CalendarData data = new CalendarData(-((int)junk.getTime()),this);

          data.setStartDate(startDate);
          data.setEndDate(endDate);
          data.setName(eName.getText().toString());
          data.setDescription(eDesc.getText().toString());
          data.setRepeatEvery(junk);
          data.setRepeatUntil(junk);
          data.setIsAllDay(true);

          data.save();

          CalendarGlobals.calDC.addEvent(data);*/


          Map<String,Object> hm = new HashMap<>();

          hm.put("NAME",eName.getText().toString());
          hm.put("DESCRIPTION",eDesc.getText().toString());
          hm.put("START_DATE",CalendarGlobals.merge(startDate,startTime));
          Log.w("MISSING",CalendarGlobals.merge(endDate,endTime).toString());
          hm.put("END_DATE",CalendarGlobals.merge(endDate,endTime));
          hm.put("REPEAT_EVERY",new Date());
          hm.put("REPEAT_UNTIL",new Date());

          CalendarGlobals.calDC.updateEvent(dat,hm);
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