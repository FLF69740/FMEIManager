package com.example.fmeimanager.controllers.navigationPackageA.processusTheme.drawing;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.example.fmeimanager.R;
import com.example.fmeimanager.utils.Utils;


public class ProcessDashCustomTitle extends View {

    private Path mPath = new Path();
    private Paint mPaint = new Paint();
    private String mTitle = "FFFFF";

    public ProcessDashCustomTitle(Context context) {
        super(context);
        init(null);
    }

    public ProcessDashCustomTitle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ProcessDashCustomTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProcessDashCustomTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){}

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPaint.setColor(getContext().getColor(R.color.cardview_title_fmea));
        }else {
            mPaint.setColor(Color.WHITE);
        }

        //PART 1
        mPath.reset();
        mPath.moveTo(0, Utils.getPixelPercent(20, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(10, getHeight()), 0);
        mPath.lineTo(Utils.getPixelPercent(10, getHeight()), Utils.getPixelPercent(80, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(80, getWidth()), Utils.getPixelPercent(80, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(85, getWidth()), Utils.getPixelPercent(90, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(10, getHeight()), Utils.getPixelPercent(90, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(10, getHeight()), getHeight());
        mPath.lineTo(0, Utils.getPixelPercent(90, getHeight()));
        mPath.close();
        canvas.drawPath(mPath, mPaint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPaint.setColor(getContext().getColor(R.color.cardview_info_fmea));
        }else {
            mPaint.setColor(Color.WHITE);
        }


        int y = 4, x = - 1;

        //PART 2
        mPath.reset();
        mPath.moveTo(Utils.getPixelPercent(15 + x, getHeight()), Utils.getPixelPercent(25 + y, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(20 + x , getHeight()), Utils.getPixelPercent(40 + y , getHeight()));
        mPath.lineTo(Utils.getPixelPercent(20 + x , getHeight()), Utils.getPixelPercent(56 + y , getHeight()));
        mPath.lineTo(Utils.getPixelPercent(30 + x , getHeight()), Utils.getPixelPercent(68 + y , getHeight()));
        mPath.lineTo(Utils.getPixelPercent(24 + x , getWidth()), Utils.getPixelPercent(68 + y , getHeight()));
        mPath.lineTo(Utils.getPixelPercent(19 + x , getWidth()), Utils.getPixelPercent(71 + y , getHeight()));
        mPath.lineTo(Utils.getPixelPercent(25 + x , getHeight()), Utils.getPixelPercent(71 + y , getHeight()));
        mPath.lineTo(Utils.getPixelPercent(15 + x , getHeight()), Utils.getPixelPercent(60 + y , getHeight()));
        mPath.close();
        canvas.drawPath(mPath, mPaint);

        // text
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPaint.setColor(getContext().getColor(R.color.background_light));
        }else {
            mPaint.setColor(Color.WHITE);
        }

        //PROCESSUS
        mPaint.setTextSize(Utils.getPixelPercent(22, getHeight()));

        canvas.drawText(getContext().getString(R.string.Process_dashboard_processus),
                Utils.getPixelPercent(35, getHeight()), Utils.getPixelPercent(65, getHeight()), mPaint);

        //TITLE
        mPaint.setTextSize(Utils.getPixelPercent(30, getHeight()));

        canvas.drawText(mTitle,
                Utils.getPixelPercent(25, getWidth()), Utils.getPixelPercent(70, getHeight()), mPaint);








    }

    public void setTitle(String title){
        mTitle = title;
        invalidate();
    }



}
