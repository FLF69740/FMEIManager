package com.example.fmeimanager.controllers.navigationPackageA.riskTheme;

import android.net.Uri;

import com.example.fmeimanager.utils.BitmapStorage;
import com.example.fmeimanager.utils.Utils;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.fmeimanager.utils.BitmapStorage.PHOTO_JUNCTION_TITLE_URI;
import static com.example.fmeimanager.utils.BitmapStorage.PHOTO_SEPARATOR;

public class BusinessRiskTheme {

    public static int getAeraState(String source){
        if (source.equals(Utils.EMPTY)){
            return 0;
        }else if (!source.equals(Utils.EMPTY) && !source.contains(PHOTO_SEPARATOR)) {
            return 1;
        }else {
            String[] photoNumber = source.split(PHOTO_SEPARATOR);
            return photoNumber.length;
        }
    }

    public static ArrayList<String> getPhotoList(String source){
        ArrayList<String> result = new ArrayList<>();
        if (source.equals(Utils.EMPTY)){
            result.add(Utils.EMPTY);
        }else if (!source.equals(Utils.EMPTY) && !source.contains(PHOTO_SEPARATOR)) {
            result.add(source);
        }else {
            result.addAll(Arrays.asList(source.split(PHOTO_SEPARATOR)));
        }
        return result;
    }

    public static String getPhotoTitle(String photoName){
        String[] subIdentity = photoName.split(PHOTO_JUNCTION_TITLE_URI);
        return subIdentity[1];
    }

    public static String getStringUrl(String before, String uri){
        if (before.equals(Utils.EMPTY)) {
            return uri;
        }else {
            return before + PHOTO_SEPARATOR + uri;
        }
    }

    public static String getListPhotoToOriginalFormat(ArrayList<String> strings){
        StringBuilder result = new StringBuilder();
        for (int i = 0 ; i < strings.size() ; i++){
            if (i == 0){
                result.append(strings.get(i));
            }else {
                result.append(BitmapStorage.PHOTO_SEPARATOR).append(strings.get(i));
            }
        }
        return result.toString();
    }

    // Get Calendar date
    public static DateTime getDateMemory(String date){
        DateTime calendar = new DateTime();
        if (!date.equals(Utils.EMPTY)) {
            calendar = calendar.year().setCopy(Utils.getYear(date));
            calendar = calendar.monthOfYear().setCopy(Utils.getMonth(date));
            calendar = calendar.dayOfMonth().setCopy(Utils.getDayOfMonth(date));
        }
        return calendar;
    }

}
