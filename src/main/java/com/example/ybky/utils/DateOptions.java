package com.example.ybky.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateOptions {
    private static final SimpleDateFormat halfDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static String getCurrentDate(){
        Date date = new Date();
        return halfDateFormat.format(date);
    }
    public static String getDatePlus0000(String date){
        return String.format("%s 00:00:00",date);
    }
    public static String getDatePlus2359(String date){
        return String.format("%s 23:59:59",date);
    }
}
