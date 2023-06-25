package com.example.ybky.tools;

import java.util.Comparator;
import java.util.List;
import java.util.Date;
public class Sorter {

    public static List<String> sortingArrayOfDates(List<Date> dates){
        List<Date> sorted = dates.stream()
                .sorted(Comparator.comparing(Date::getTime))
                .toList();
        return Converter.convertDatesToStrings(sorted);
    }


}
