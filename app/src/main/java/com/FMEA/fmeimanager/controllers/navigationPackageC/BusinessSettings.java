package com.FMEA.fmeimanager.controllers.navigationPackageC;

public class BusinessSettings {

    public static Boolean scoreComparaison(int low, int medium, int high){
        return low < medium && medium < high;
    }
}
