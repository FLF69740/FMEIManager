package com.example.fmeimanager.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class BitmapStorage {

    public static final String CAMERA_CAPTURE = "TEMP_CAMERA_CAPTURE";
    public static final String PHOTO_SEPARATOR  = "!-!";
    public static final String PHOTO_JUNCTION_TITLE_URI = "!__!";

    // save Image into internal memory
    public static void saveImageInternalStorage(Context context, String imageName, Uri uri){
        Bitmap bitmap = null;
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // load Image from internal memory
    public static Bitmap loadImage(Context context, String imageName){
        Bitmap bitmap = null;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(context.getFilesDir() + "/" + imageName));
            bitmap = BitmapFactory.decodeStream(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    // check if image exist into internal memory
    public static boolean isFileExist(Context context, String imageName){
        File file = context.getFileStreamPath(imageName);
        return file.exists();
    }

    // show if bitmpap exist and his path into the logcat
    public static void showImageInformations(Context context, String imageName){
        File file = context.getFileStreamPath(imageName);
        Log.i(Utils.INFORMATION_LOG, "Exist : "+ String.valueOf(BitmapStorage.isFileExist(context, imageName)) + " - chemin : " + file.getAbsolutePath());
    }

    // Organise real photo used for RISK
    public static void purgePhotosInternalMemory(Context context, String source, long id, boolean corrective){
        Log.i(Utils.INFORMATION_LOG, "--> " + context.getFilesDir().getAbsolutePath());
        File directory = context.getFilesDir();

        File[] list = directory.listFiles();
        if (corrective){
            for (File ff : list) {
                if (ff.getName().contains("C" + String.valueOf(id) + PHOTO_JUNCTION_TITLE_URI) && !source.contains(ff.getName())) {
                    deleteImage(context, ff.getName());
                } else {
                    Log.i(Utils.INFORMATION_LOG, "*** " + ff.getName() + " : SAFE !");
                }
            }
        }else {
            for (File ff : list) {
                if (ff.getName().contains(String.valueOf(id) + PHOTO_JUNCTION_TITLE_URI) && !source.contains(ff.getName())) {
                    deleteImage(context, ff.getName());
                } else {
                    Log.i(Utils.INFORMATION_LOG, "*** " + ff.getName() + " : SAFE !");
                }
            }
        }
    }

    // delete a bitmap
    public static void deleteImage(Context context, String imageName){
        if (BitmapStorage.isFileExist(context, imageName)){
            File file = context.getFileStreamPath(imageName);
            file.delete();
            Log.i(Utils.INFORMATION_LOG, imageName + " : bitmap deleted");
        }else {
            Log.i(Utils.INFORMATION_LOG, "bitmap didn't exist");
        }
    }

    //Get an Uri with a Bitmap object
    public static Uri getImageUri(Context context, Bitmap bitmap, long id){
        String imageName = BitmapStorage.findCameraCaptureName(context, id);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BitmapStorage.showImageInformations(context, imageName);

        return Uri.fromFile(new File(context.getFilesDir() + "/" + imageName));
    }

    // define a free name for a new camera capture file
    private static String findCameraCaptureName(Context context, long id){
        int number = 0;
        String result = String.valueOf(id) + PHOTO_JUNCTION_TITLE_URI + CAMERA_CAPTURE + number;

        while (BitmapStorage.isFileExist(context, result)){
            number++;
            result = String.valueOf(id) + PHOTO_JUNCTION_TITLE_URI + CAMERA_CAPTURE + number;
        }

        return result;
    }


}
