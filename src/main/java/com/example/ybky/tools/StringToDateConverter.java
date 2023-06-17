package com.example.ybky.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StringToDateConverter {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static List<Date> convertArrayOfStringsToArrayOfDates(List<String> listOfStringDates){
        List<Date> convertedDate = new ArrayList<>();

        for(String stringDate: listOfStringDates){
            Date date;
            try {
                date = dateFormat.parse(stringDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            convertedDate.add(date);
        }
        return convertedDate;
    }
    public static Date convertStringToDate(String stringToDate){
        try {
            return dateFormat.parse(stringToDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
