package com.FMEA.fmeimanager.controllers.navigationPackageA.processusTheme.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.utils.Utils;

public class InsertRiskCustomPos extends View {

    private Path mPath = new Path();
    private Paint mPaint = new Paint();
    private boolean mFirst = false;
    private boolean mLast = false;

    public InsertRiskCustomPos(Context context) {
        super(context);
        init(null);
    }

    public InsertRiskCustomPos(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public InsertRiskCustomPos(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InsertRiskCustomPos(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(3);

        // MIDDLE GEOMETRY
        canvas.save();
        canvas.translate(getWidth()/2 - Utils.getPixelPercent(5, getWidth()), getHeight()/3 - Utils.getPixelPercent(5, getHeight()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPaint.setColor(getContext().getColor(R.color.cardview_title_fmea));
        }else {
            mPaint.setColor(Color.WHITE);
        }

        canvas.drawPath(getMiddleRect(), mPaint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPaint.setColor(getContext().getColor(R.color.cardview_info_fmea));
        }else {
            mPaint.setColor(Color.WHITE);
        }

        canvas.drawPath(getMiddleInternalRect(), mPaint);
        canvas.restore();

        // LEFT GEOMETRY
        canvas.save();
        canvas.translate(getWidth()/3 - Utils.getPixelPercent(5, getWidth()), getHeight()/3 - Utils.getPixelPercent(5, getHeight()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPaint.setColor(getContext().getColor(R.color.cardview_title_fmea));
        }else {
            mPaint.setColor(Color.WHITE);
        }

        if (!mFirst) {
            canvas.drawPath(getMiddleInternalRect(), mPaint);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mPaint.setColor(getContext().getColor(R.color.cardview_info_fmea));
            } else {
                mPaint.setColor(Color.WHITE);
            }
        }

        canvas.drawPath(getLittleRect(), mPaint);
        canvas.restore();

        // RIGHT GEOMETRY
        canvas.save();
        canvas.translate(getWidth() - getWidth()/3 - Utils.getPixelPercent(5, getWidth()), getHeight()/3 - Utils.getPixelPercent(5, getHeight()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPaint.setColor(getContext().getColor(R.color.cardview_title_fmea));
        }else {
            mPaint.setColor(Color.WHITE);
        }

        if (!mLast) {
            canvas.drawPath(getMiddleInternalRect(), mPaint);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mPaint.setColor(getContext().getColor(R.color.cardview_info_fmea));
            } else {
                mPaint.setColor(Color.WHITE);
            }
        }

        canvas.drawPath(getLittleRect(), mPaint);
        canvas.restore();

    }

    /**
     *  GEOMETRIE
     */

    //GET bigger geometry
    private Path getMiddleRect(){
        mPath.reset();
        mPath.moveTo(Utils.getPixelPercent(5, getWidth()), 0);
        mPath.lineTo(Utils.getPixelPercent(10, getWidth()), Utils.getPixelPercent(5, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(5, getWidth()), Utils.getPixelPercent(10, getHeight()));
        mPath.lineTo(0, Utils.getPixelPercent(5, getHeight()));
        mPath.close();
        return mPath;
    }

    //GET middle geometry
    private Path getMiddleInternalRect(){
        mPath.reset();
        mPath.moveTo(Utils.getPixelPercent(5, getWidth()), Utils.getPixelPercent(2, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(8, getWidth()), Utils.getPixelPercent(5, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(5, getWidth()), Utils.getPixelPercent(8, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(2, getWidth()), Utils.getPixelPercent(5, getHeight()));
        mPath.close();
        return mPath;
    }

    //GET little geometry
    private Path getLittleRect(){
        mPath.reset();
        mPath.moveTo(Utils.getPixelPercent(5, getWidth()), Utils.getPixelPercent(4, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(6, getWidth()), Utils.getPixelPercent(5, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(5, getWidth()), Utils.getPixelPercent(6, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(4, getWidth()), Utils.getPixelPercent(5, getHeight()));
        mPath.close();
        return mPath;
    }

    /**
     *  HIDE FIRST OR LAST GEOMETRY
     */

    //HIDE left Geometry
    public void hideFirst(){
        mFirst = true;
        invalidate();
    }

    //HIDE right geometry
    public void hideLast(){
        mLast = true;
        invalidate();
    }

    //HIDE left and right
    public void onlyOne(){
        mLast = true;
        mFirst = true;
        invalidate();
    }


}
