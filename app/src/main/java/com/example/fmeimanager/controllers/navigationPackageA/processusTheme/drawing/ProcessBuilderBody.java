package com.example.fmeimanager.controllers.navigationPackageA.processusTheme.drawing;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
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

import java.util.ArrayList;
import java.util.List;

public class ProcessBuilderBody extends View {

    public static final String RISE = "RISE";
    public static final String FALL = "FALL";
    private static final String MASK = "MASK";

    private Path mPath = new Path();
    private Paint mPaint = new Paint();
    private String mTitle = "TITLE WITH A LONG LONG LONG LONG TEXT";
    private String mBarStatut = "";
    private int mTranslator = 0;

    public ProcessBuilderBody(Context context) {
        super(context);
        init(null);
    }

    public ProcessBuilderBody(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ProcessBuilderBody(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProcessBuilderBody(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);

        //PART 1
        setColorBarStatut("");
        this.getBar();
        canvas.drawPath(mPath, mPaint);

        //PART 2
        setColorBarStatut(mBarStatut);
        this.getLightning();
        canvas.drawPath(mPath, mPaint);

        // text
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPaint.setColor(getContext().getColor(R.color.background_light));
        }else {
            mPaint.setColor(Color.WHITE);
        }

        mPaint.setTextSize(Utils.getPixelPercent(30, getHeight()));

        canvas.drawText(mTitle,
                Utils.getPixelPercent(5, getWidth()), Utils.getPixelPercent(55, getHeight()), mPaint);

        //TEXT MASK
        setColorBarStatut(MASK);
        canvas.drawRect(getMask(), mPaint);
    }

    // SET color PART 1
    private  void setColorBarStatut(String result){
        switch (result){
            case RISE :
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mPaint.setColor(getContext().getColor(R.color.green_low_score));
                    break;
                }else {
                    mPaint.setColor(Color.GREEN);
                    break;
                }
            case FALL :
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mPaint.setColor(getContext().getColor(R.color.red_high_score));
                    break;
                }else {
                    mPaint.setColor(Color.RED);
                    break;
                }
            case MASK :
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mPaint.setColor(getContext().getColor(R.color.colorPrimary));
                    break;
                }else {
                    mPaint.setColor(Color.GRAY);
                    break;
                }
            default:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mPaint.setColor(getContext().getColor(R.color.cardview_title_fmea));
                }else {
                    mPaint.setColor(Color.WHITE);
                }
        }
    }

    //SET MASK Quick timer
    private int setQuickMask(int entry){
        if (mTranslator != 0) {
            mTranslator = 1 + (100 * entry);
            if (mTranslator > 87) mTranslator = 87;
            return mTranslator;
        }else {
            return 0;
        }
    }

    /**
     * GEOMETRIE
     */

    //PATH : PART 1
    private Path getBar(){
        mPath.reset();
        mPath.moveTo(0, Utils.getPixelPercent(20, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(10, getHeight()), 0);
        mPath.lineTo(Utils.getPixelPercent(10, getHeight()), Utils.getPixelPercent(80, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(90, getWidth()), Utils.getPixelPercent(80, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(95, getWidth()), Utils.getPixelPercent(90, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(10, getHeight()), Utils.getPixelPercent(90, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(10, getHeight()), getHeight());
        mPath.lineTo(0, Utils.getPixelPercent(90, getHeight()));
        mPath.close();
        return mPath;
    }

    //PATH : PART 2
    private Path getLightning(){
        mPath.reset();
        mPath.moveTo(0, Utils.getPixelPercent(20, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(10, getHeight()), 0);
        mPath.lineTo(Utils.getPixelPercent(10, getHeight()), Utils.getPixelPercent(80, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(3 + mTranslator, getWidth()), Utils.getPixelPercent(80, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(8 + mTranslator, getWidth()), Utils.getPixelPercent(90, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(10, getHeight()), Utils.getPixelPercent(90, getHeight()));
        mPath.lineTo(Utils.getPixelPercent(10, getHeight()), getHeight());
        mPath.lineTo(0, Utils.getPixelPercent(90, getHeight()));
        mPath.close();
        return mPath;
    }

    //TEXT MASK : PART 3
    private RectF getMask(){
        return new RectF(Utils.getPixelPercent(4 + mSecondTranslator, getWidth()), Utils.getPixelPercent(15, getHeight()),
                Utils.getPixelPercent(4 + setQuickMask(mTranslator), getWidth()), Utils.getPixelPercent(65, getHeight()));
    }

    /**
     * UPLOAD
     */

    //SET text
    public void setTitleBody(String title){
        mTitle = title;
        invalidate();
    }

    //SET bar color
    public void setBarStatut(String barStatut){
        mBarStatut = barStatut;
        invalidate();
    }

    private int mLockChangeColor =0;
    private int mSecondTranslator = 0;

    //INVERSE bar color
    public void inverseBar(){
        if (mLockChangeColor ==0) {
            if (mBarStatut.equals(FALL)) {
                mBarStatut = RISE;
                mLockChangeColor++;
            } else if (mBarStatut.equals(RISE)) {
                mBarStatut = FALL;
                mLockChangeColor++;
            }
        }
    }

    private static final String PROPERTY_TRANSLATION = "PROPERTY_TRANSLATION";
    PropertyValuesHolder propertyTranslation = PropertyValuesHolder.ofInt(PROPERTY_TRANSLATION, 0, 87);

    //LAUNCH TRANSLATION
    public void startTranslation(){
        AnimatorSet animatorSet = new AnimatorSet();
        List<Animator> animators = new ArrayList<>();
        animators.add(translationStepOne());
        animators.add(translationStepTwo());
        animatorSet.playSequentially(animators);
        animatorSet.start();
    }

    //bar translation
    private Animator translationStepOne(){
        ValueAnimator animator = new ValueAnimator();
        animator.setValues(propertyTranslation);
        animator.setDuration(300);
        animator.addUpdateListener(animation -> {
            mTranslator = (int) animation.getAnimatedValue(PROPERTY_TRANSLATION);
            invalidate();
        });
        return animator;
    }

    //inverse color
    private Animator translationStepTwo(){
        ValueAnimator animator = new ValueAnimator();
        animator.setValues(propertyTranslation);
        animator.setDuration(300);
        animator.addUpdateListener(animation -> {
            inverseBar();
            mSecondTranslator = (int) animation.getAnimatedValue(PROPERTY_TRANSLATION);
            invalidate();
        });
        return animator;
    }

















}
