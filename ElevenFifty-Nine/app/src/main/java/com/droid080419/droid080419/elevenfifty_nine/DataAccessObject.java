package com.droid080419.droid080419.elevenfifty_nine;
/**
 * Write a description of class DataAccessObject here.
 * 
 * @author Loie Buera
 * @version Nov. 19, 2014, 8:22 AM
 */

import android.annotation.SuppressLint;
import java.util.ArrayList;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;

public class DataAccessObject
{

    public static void test(){
        Transfer tr = new Transfer();
        tr.setDescription("testing123");
        tr.setStartDate(new Date());
        tr.setIsAllDay(false);
        tr.setEndDate(new Date());
        tr.setRepeatEvery(new Date());
        tr.setRepeatUntil(new Date());
        tr.setName("test123");
        File file = new File("test.tsf");
        ArrayList<Transfer> arr = new ArrayList<Transfer>();
        arr.add(tr);
        tr = new Transfer();
        tr.setDescription("testing456");
        tr.setStartDate(new Date());
        tr.setIsAllDay(false);
        tr.setName("test456");
        tr.setEndDate(new Date());
        tr.setRepeatEvery(new Date());
        tr.setRepeatUntil(new Date());
        arr.add(tr);
        try{
            save(arr,file);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    public static void save(ArrayList<Transfer> list, File f) throws FileNotFoundException
    {
        PrintWriter pw = new PrintWriter(f);
        for(Transfer t: list){
            pw.println("Transfer::");
            pw.println("name::"+t.getName());
            pw.println("start_date::"+t.getStartDate().toString());
            pw.println("end_date::"+t.getEndDate().toString());
            pw.println("repeat_every::"+t.getRepeatEvery().toString());
            pw.println("repeat_until::"+t.getRepeatUntil().toString());
            pw.println("is_all_day::"+t.getIsAllDay().toString());
            pw.println("description::"+t.getDescription());
        }
        pw.println("STOP::");
        pw.close();
    }
    
    @SuppressLint("SimpleDateFormat")
	public static ArrayList<Transfer> load(InputStream f) throws FileNotFoundException,NullPointerException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Transfer curr = null;
        ParsePosition pos = new ParsePosition(0);
        ArrayList<Transfer> arr = new ArrayList<Transfer>();
        Scanner sc = new Scanner(f);
        String line = null;
        while(sc.hasNextLine()){
            line = sc.nextLine();
            pos.setIndex(0);
            if(line.startsWith("Transfer::")){
                line.replace("Transfer::","");
                if(curr != null){
                    arr.add(curr);
                }
                curr = new Transfer();
            }else if(line.startsWith("name::")){
                line = line.replace("name::","");
                curr.setName(line);
            }else if(line.startsWith("start_date::")){
                line = line.replace("start_date::","");
                curr.setStartDate(sdf.parse(line,pos));
            }else if(line.startsWith("end_date::")){
                line = line.replace("end_date::","");
                curr.setEndDate(sdf.parse(line,pos));
            }else if(line.startsWith("repeat_every::")){
                line = line.replace("repeat_every::","");
                curr.setRepeatEvery(sdf.parse(line,pos));
            }else if(line.startsWith("repeat_until::")){
                line = line.replace("repeat_until::","");
                curr.setRepeatUntil(sdf.parse(line,pos));
            }else if(line.startsWith("description::")){
                line = line.replace("description::","");
                curr.setDescription(line);
            }else if(line.startsWith("is_all_day::")){
                line = line.replace("is_all_day::","");
                if(line.matches("TRUE"))
                    curr.setIsAllDay(true);
                else
                    curr.setIsAllDay(false);
            }else if(line.startsWith("STOP::")){
                if(curr != null)
                    arr.add(curr);
                break;
            }
        }
        sc.close();
        return arr;
    }
    
}
