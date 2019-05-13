package com.example.fmeimanager.utils;

public class Utils {

    public static final String EMPTY = "EMPTY";
    public static final String INFORMATION_LOG = "INFORMATION_LOG";
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    //Get day of month
    public static int getDayOfMonth(String date){
        String[] divider = date.split("/");
        return Integer.valueOf(divider[0]);
    }

    //Get month
    public static int getMonth(String date){
        String[] divider = date.split("/");
        return Integer.valueOf(divider[1]);
    }

    //Get year
    public static int getYear(String date){
        String[] divider = date.split("/");
        return Integer.valueOf(divider[2]);
    }

    //GET percent dimension with pixel
    public static int getPixelPercent(int percent, int dimension){
        return (percent * dimension) / 100;
    }
}
