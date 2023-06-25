package com.example.ybky.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Converter {
    private static final SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static Date convertStringToDate(String stringToDate){
        try {
            return fullDateFormat.parse(stringToDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<String> convertDatesToStrings(List<Date> dates) {
        List<String> strings = new ArrayList<>();
        for (Date date : dates) {
            String string = fullDateFormat.format(date);
            strings.add(string);
        }
        return strings;
    }
    public static List<Date> convertArrayOfStringsToArrayOfDates(List<String> dates){
        List<Date> datess = new ArrayList<>();
        for(String date:dates){
            try {
                datess.add(fullDateFormat.parse(date));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return datess;
    }
}
