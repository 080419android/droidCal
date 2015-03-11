package com.droid080419.droid080419.elevenfifty_nine;

/**
 * Created by cs192fwru on 3/4/15.
 */

import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

public interface TaskEditor {

     public Date getStartDate();
     public Date getStartTime();
     public Date getEndDate();
     public Date getEndTime();
     public TextView getEStartDate();
     public TextView getEStartTime();
     public TextView getEEndDate();
     public TextView getEEndTime();
     public void setStartTime(Date startTime);
     public void setStartDate(Date startDate);
     public void setEndTime(Date endTime);
     public void setEndDate(Date endDate);
}
