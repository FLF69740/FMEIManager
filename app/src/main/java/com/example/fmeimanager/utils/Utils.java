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

    //GET y position with f(x) & (x1;y1) - (x2;y2)
    public static int getFx(int x1, int y1, int x2, int y2, int value){
        int y, m, p;

        m = (y2 - y1) / (x2 - x1);
        p = y1 - (m * x1);

        y = (m * value) + p;

        return y;
    }
}
