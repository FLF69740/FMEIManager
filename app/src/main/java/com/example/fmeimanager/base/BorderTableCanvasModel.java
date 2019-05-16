package com.example.fmeimanager.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.fmeimanager.R;
import com.example.fmeimanager.utils.Utils;

public class BorderTableCanvasModel extends View {

    private Paint mPaint = new Paint();
    private Path mPath = new Path();

    public BorderTableCanvasModel(Context context) {
        super(context);
        init(null);
    }

    public BorderTableCanvasModel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BorderTableCanvasModel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BorderTableCanvasModel(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPaint.setColor(getContext().getColor(R.color.cardview_title_fmea));
        }else {
            mPaint.setColor(Color.WHITE);
        }

        canvas.drawRect(getBorder(), mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        getGroundAera();
        canvas.drawPath(mPath, mPaint);

        canvas.save();
        canvas.translate(getWidth(),0);
        canvas.rotate(90);
        canvas.drawPath(mPath, mPaint);

        canvas.restore();
        canvas.save();
        canvas.translate(getWidth(), getHeight());
        canvas.rotate(180);
        canvas.drawPath(mPath, mPaint);

        canvas.restore();
        canvas.translate(0, getHeight());
        canvas.rotate(270);
        canvas.drawPath(mPath, mPaint);
    }

    /**
     *  GEOMETRIE
     */

    // PATH : ground aera
    private Path getGroundAera(){
        mPath.reset();
        mPath.moveTo(0, 0);
        mPath.lineTo(Utils.getPixelPercent(20, getHeight()), 0);
        mPath.lineTo(Utils.getPixelPercent(15, getHeight()), Utils.getPixelPercent(2, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(2, getHeight()), Utils.getPixelPercent(2, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(2, getHeight()), Utils.getPixelPercent(15, getHeight()));
        mPath.lineTo(0, Utils.getPixelPercent(20, getHeight()));
        mPath.close();
        return mPath;
    }

    //border
    private RectF getBorder(){
        return new RectF(2, 2, getWidth(), getHeight());
    }

}
