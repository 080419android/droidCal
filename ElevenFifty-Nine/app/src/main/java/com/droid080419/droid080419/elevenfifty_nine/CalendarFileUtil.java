package com.droid080419.droid080419.elevenfifty_nine;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by genesis on 2/13/15.
 */

/*
     File Utility functions
 */

public class CalendarFileUtil {

     //Finds the first occurence of a string and returns what line it occured
     public static int findString(File file, String expr) throws FileNotFoundException{
          int line = -1;
          int cur = 0;
          Scanner scanner = new Scanner(file);
          while(scanner.hasNextLine()){
               cur++;
               if(scanner.nextLine().contains(expr))
                    return cur;
          }
          return line;
     }

     //Gets the string in the given line from the file
     public static String getStringOnLine(File file, int line) throws FileNotFoundException{
          Scanner scanner = new Scanner(file);
          String ret = "";
          for(int i = 1; i <= line; i++ )
               ret = scanner.nextLine();

          return ret;
     }

     //Go to line in the file
     public static Scanner goToLine(int line, Scanner s){
          for(int i = 1; i < line; i++)
               s.nextLine();
          return s;
     }

}
