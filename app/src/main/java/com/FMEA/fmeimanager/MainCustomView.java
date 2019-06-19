package com.FMEA.fmeimanager;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.FMEA.fmeimanager.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainCustomView extends View {

    private Path mPath = new Path();
    private Paint mPaint = new Paint();
    private int mAnimationRatio = 0;
    private int mAnimationAlpha = 0;
    private int mCirclePosX = 1;
    private int mAngleWheel = 0;
    private double mTouchEffect = 0;

    public MainCustomView(Context context) {
        super(context);
        init(null);
    }

    public MainCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MainCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MainCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int touchPos = 0;
        int r = 0;
        int x1 = 18;
        int x2 = 84;
        int y1 = 69;
        int y2 = 61;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            touchPos = 45;
            r = 70;
            x1 = 18;
            x2 = 84;
            y1 = 63;
            y2 = 55;
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            touchPos = 34;
            r = 75;
            x1 = 18;
            x2 = 81;
            y1 = 69;
            y2 = 61;
        }

        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.FILL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPaint.setColor(getContext().getColor(R.color.colorAccent));
        }else {
            mPaint.setColor(Color.WHITE);
        }

        // horizontal line
        canvas.drawLine(Utils.getPixelPercent(5, getWidth()), Utils.getPixelPercent(10, getHeight()),
                getCoordinateLines(Utils.getPixelPercent(5, getWidth()), getWidth() - Utils.getPixelPercent(10, getWidth())),
                Utils.getPixelPercent(10, getHeight()), mPaint);

        // vertical line
        canvas.drawLine(Utils.getPixelPercent(10, getWidth()), Utils.getPixelPercent(10, getHeight()) - Utils.getPixelPercent(5, getWidth()),
                Utils.getPixelPercent(10, getWidth()),
                getCoordinateLines(Utils.getPixelPercent(10, getHeight()) - Utils.getPixelPercent(5, getWidth()), Utils.getPixelPercent(85, getHeight())),
                mPaint);

        // circle
        double m = getM((double) Utils.getPixelPercent(x1, getWidth()), (double) Utils.getPixelPercent(y1, getHeight()),
                (double)Utils.getPixelPercent(x2, getWidth()),(double) Utils.getPixelPercent(y2, getHeight()));
        double p = getP(Utils.getPixelPercent(y1, getHeight()), m, Utils.getPixelPercent(x1, getWidth()));

        mPaint.setAlpha(mAnimationAlpha);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        canvas.save();

        canvas.translate(getCoordinateCircleX(Utils.getPixelPercent(x1, getWidth()), Utils.getPixelPercent(x2, getWidth())),
                (float) (m * getCoordinateCircleX(Utils.getPixelPercent(x1, getWidth()), Utils.getPixelPercent(x2, getWidth())) + p));
        canvas.drawCircle(0, 0, getCircleRadius(r), mPaint);
        canvas.rotate(mAngleWheel);
        canvas.drawLine(0, -getCircleRadius(r), 0, getCircleRadius(r), mPaint);
        canvas.rotate(mAngleWheel + 90);
        canvas.drawLine(0, -getCircleRadius(r), 0, getCircleRadius(r), mPaint);

        canvas.restore();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPaint.setColor(getContext().getColor(R.color.colorAccentDark));
        }else {
            mPaint.setColor(Color.WHITE);
        }

        // Ground geometry
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(mAnimationAlpha);
        canvas.drawPath(getGroundAera(mPath), mPaint);

        // text
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPaint.setColor(getContext().getColor(R.color.background_light));
        }else {
            mPaint.setColor(Color.WHITE);
        }

        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setTextSize(Utils.getPixelPercent(10, getHeight()));

        canvas.drawText(getContext().getString(R.string.Risk_file_mail_message_line_one),
                Utils.getPixelPercent(20, getWidth()), Utils.getPixelPercent(25, getHeight()), mPaint);

        mPaint.setTextSize(Utils.getPixelPercent(6, getHeight()));

        canvas.drawText(getContext().getString(R.string.main_manager),
                Utils.getPixelPercent(30, getWidth()), Utils.getPixelPercent(35, getHeight()), mPaint);

        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setTextSize(Utils.getPixelPercent(3, getHeight()));
        mPaint.setAlpha((int) mTouchEffect);
        canvas.drawText(getContext().getString(R.string.touch_screen),
                Utils.getPixelPercent(touchPos, getWidth()), Utils.getPixelPercent(95, getHeight()), mPaint);
    }

    /**
     *  ANIMATION
     */

    private PropertyValuesHolder mRatioAnimatorValues = PropertyValuesHolder.ofInt("DISTANCE", 1, 100);
    private PropertyValuesHolder mAlphaAnimatorValues = PropertyValuesHolder.ofInt("VISIBILITY", 1, 255);
    private PropertyValuesHolder mCircleTranslationValue = PropertyValuesHolder.ofInt("TRANSLATION", 1, 100);
    private PropertyValuesHolder mLineWheelValue = PropertyValuesHolder.ofInt("ROTATION", 1, 360);
    private PropertyValuesHolder mTouchScreenEffect = PropertyValuesHolder.ofInt("TOUCH", 0, 10);

    //complete animation
    public void playAnimation(){
        AnimatorSet animatorSet = new AnimatorSet();
        List<Animator> animators = new ArrayList<>();
        animators.add(waitAnimation());
        animators.add(animateLine());
        animators.add(groundApparition());
        animators.add(animateCircle());
        animatorSet.playSequentially(animators);
        animatorSet.start();
    }

    //margin temp
    private Animator waitAnimation(){
        ValueAnimator animator = new ValueAnimator();
        animator.setValues(mRatioAnimatorValues);
        animator.setDuration(1000);
        animator.addUpdateListener(animation -> {});
        return animator;
    }

    //animate line
    private Animator animateLine(){
        ValueAnimator animator = new ValueAnimator();
        animator.setValues(mRatioAnimatorValues);
        animator.setDuration(1500);
        animator.addUpdateListener(animation -> {
            mAnimationRatio = (int) animation.getAnimatedValue();
            invalidate();
        });
        return animator;
    }

    //Ground and circle apparition
    private Animator groundApparition(){
        ValueAnimator animator = new ValueAnimator();
        animator.setValues(mAlphaAnimatorValues);
        animator.setDuration(300);
        animator.addUpdateListener(animation -> {
            mAnimationAlpha = (int) animation.getAnimatedValue();
            invalidate();
        });
        return animator;
    }

    //animate circle
    private Animator animateCircle(){
        ValueAnimator animator = new ValueAnimator();
        animator.setValues(mCircleTranslationValue, mLineWheelValue, mTouchScreenEffect);
        animator.setDuration(700);
        animator.addUpdateListener(animation -> {
            mCirclePosX = (int) animation.getAnimatedValue("TRANSLATION");
            mAngleWheel = (int) animation.getAnimatedValue("ROTATION");
            mTouchEffect = Math.abs(Math.cos((int) animation.getAnimatedValue("TOUCH"))) * 100;
            invalidate();
        });
        return animator;
    }

    // Distance definition for lines
    private int getCoordinateLines(int start, int end){
        if (mAnimationRatio == 0){
            return start;
        }else {
            return ((start + (end - start)) * mAnimationRatio / 100);
        }
    }

    // Distance X definition for circle
    private int getCoordinateCircleX(int start, int end){
        return (start + ((end - start)) * mCirclePosX / 100);
    }

    private double getM(double x1, double x2, double y1, double y2){
        return (y2 - x2) / (y1 - x1);
    }

    private double getP(double y, double m, double x){
        return y - (m*x);
    }


    /**
     *  GEOMETRIE
     */

    // RADIUS CIRCLE
    private int getCircleRadius(int r){
        return Utils.getPixelPercent(80, getHeight()) - Utils.getPixelPercent(r, getHeight());
    }

    // PATH : ground aera
    private Path getGroundAera(Path path){
        path.reset();
        path.moveTo(Utils.getPixelPercent(10, getWidth()), Utils.getPixelPercent(85, getHeight()));
        path.lineTo(Utils.getPixelPercent(10, getWidth()), Utils.getPixelPercent(75, getHeight()));
        path.lineTo(getWidth() - Utils.getPixelPercent(10, getWidth()), Utils.getPixelPercent(65, getHeight()));
        path.lineTo(getWidth() - Utils.getPixelPercent(10, getWidth()), Utils.getPixelPercent(85, getHeight()));
        path.close();
        return path;
    }

}
