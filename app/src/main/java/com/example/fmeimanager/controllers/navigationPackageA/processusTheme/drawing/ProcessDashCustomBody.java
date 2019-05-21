package com.example.fmeimanager.controllers.navigationPackageA.processusTheme.drawing;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import com.example.fmeimanager.R;
import com.example.fmeimanager.utils.Utils;

import static android.content.Context.MODE_PRIVATE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_HIGH_VALUE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_LOW_VALUE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_MEDIUM_VALUE;
import static com.example.fmeimanager.MainActivity.SHARED_HIGH_SCORE;
import static com.example.fmeimanager.MainActivity.SHARED_LOW_SCORE;
import static com.example.fmeimanager.MainActivity.SHARED_MEDIUM_SCORE;

public class ProcessDashCustomBody extends View {

    private static final String LOW_RATE = "LOW_RATE";
    private static final String MEDIUM_RATE = "MEDIUM_RATE";
    private static final String HIGH_RATE = "HIGH_RATE";
    private static final String BORDER = "BORDER";
    private static final String IPR = "IPR";
    private static final String X = " X ";
    private static final String EQUALS = " = ";
    private int mLowInt, mMediumInt, mHighInt;

    private Paint mPaint = new Paint();
    private RectF mRectArc = new RectF();
    private Path mPath = new Path();

    private String mRiskNumber = "1";
    private String mTitle = "MAUVAISE DECOUPE DES ANGLES DE CADRAGE (haut et bas)";
    private String mTeamLeader = "TEAM LEADER NAME";
    private int mGravity = 10 , mProbability = 10 , mDetectability = 10, mCorrectiveScore = 0;
    private int mRiskScore = mGravity * mProbability * mDetectability;

    public ProcessDashCustomBody(Context context) {
        super(context);
        init(null);
    }

    public ProcessDashCustomBody(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ProcessDashCustomBody(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProcessDashCustomBody(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){}

    private void configurePreferencesScore(){
        mLowInt = getContext().getSharedPreferences(SHARED_LOW_SCORE, MODE_PRIVATE).getInt(BUNDLE_KEY_LOW_VALUE, 1);
        mMediumInt = getContext().getSharedPreferences(SHARED_MEDIUM_SCORE, MODE_PRIVATE).getInt(BUNDLE_KEY_MEDIUM_VALUE, 150);
        mHighInt = getContext().getSharedPreferences(SHARED_HIGH_SCORE, MODE_PRIVATE).getInt(BUNDLE_KEY_HIGH_VALUE, 200);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.configurePreferencesScore();

        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPaint.setColor(getContext().getColor(R.color.colorPrimary));
        }else {
            mPaint.setColor(Color.WHITE);
        }
        canvas.drawRect(0,0 , getWidth(), getHeight(), mPaint);

        //DRAW arc
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);


        if (mCorrectiveScore != 0){
            setColorCriticalRates(mCorrectiveScore);
        }else {
            setColorCriticalRates(mRiskScore);
        }


        mRectArc.set(0, 0, Utils.getPixelPercent(40, getHeight()), Utils.getPixelPercent(40, getHeight()));
        canvas.drawArc(mRectArc, -75f, 240, false, mPaint);

        //DRAW text RISK NUMBER
        mPaint.setStrokeWidth(4);
        mPaint.setTextSize(Utils.getPixelPercent(15, getHeight()));

        int textCursor;
        switch (mRiskNumber.length()){
            case 1: textCursor = 16; break;
            case 2: textCursor = 12; break;
            case 3: textCursor = 7; break;
            default: textCursor = 5; break;
        }
        canvas.drawText(mRiskNumber, Utils.getPixelPercent(textCursor, getHeight()), Utils.getPixelPercent(25, getHeight()), mPaint);

        //DRAW window
        mPaint.setStrokeWidth(4);

        mPaint.setStyle(Paint.Style.FILL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPaint.setColor(getContext().getColor(R.color.cardview_border_fmea));
        }else {
            mPaint.setColor(Color.WHITE);
        }

        canvas.drawPath(getWindowAera(),mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        setColorCriticalRates(1001);

        canvas.drawPath(getWindowAera(),mPaint);

        //DRAW TEXT title - team leader
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setTextSize(Utils.getPixelPercent(10, getHeight()));

        canvas.drawText(mTitle, Utils.getPixelPercent(50, getHeight()), Utils.getPixelPercent(15, getHeight()), mPaint);
        mPaint.setTypeface(Typeface.DEFAULT);
        canvas.drawText(mTeamLeader,
                Utils.getPixelPercent(50, getHeight()),
                Utils.getPixelPercent(32, getHeight()), mPaint);

        //DRAW IPR section
        mPaint.setStyle(Paint.Style.FILL);
        setColorCriticalRates(0);
        canvas.drawRect(Utils.getPixelPercent(22, getHeight()),Utils.getPixelPercent(55, getHeight()) ,
                Utils.getPixelPercent(20, getWidth()), Utils.getPixelPercent(72, getHeight()), mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(Utils.getPixelPercent(22, getHeight()),Utils.getPixelPercent(55, getHeight()) ,
                Utils.getPixelPercent(20, getWidth()), Utils.getPixelPercent(72, getHeight()), mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(IPR, Utils.getPixelPercent(23, getHeight()), Utils.getPixelPercent(65, getHeight()), mPaint);

        //DRAW IPR note
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(Utils.getPixelPercent(14, getHeight()));
        canvas.drawText(getIPRDevelopment(String.valueOf(mGravity),String.valueOf(mProbability),String.valueOf(mDetectability)),
                Utils.getPixelPercent(25, getWidth()), Utils.getPixelPercent(69, getHeight()), mPaint);

        //DRAW IPR lightning
        mPaint.setStyle(Paint.Style.FILL);

        setColorCriticalRates(mRiskScore);
        canvas.drawOval(getOval(0), mPaint);

        setColorCriticalRates(1001);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(7);
        canvas.drawOval(getOval(0), mPaint);



        //DRAW logo
        canvas.drawBitmap(getRefreshLogo(), Utils.getPixelPercent(20, getHeight()), Utils.getPixelPercent(75, getHeight()), mPaint);

        //DRAW CORRECTIVE NOTE
        int level2 = 22;
        if (mCorrectiveScore != 0) {
            mPaint.setColor(Color.BLACK);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setTextSize(Utils.getPixelPercent(14, getHeight()));
            canvas.drawText(String.valueOf(mCorrectiveScore), Utils.getPixelPercent(25, getWidth()), Utils.getPixelPercent(69 + level2, getHeight()), mPaint);
        }

        //DRAW CORRECTIVE Lightning
        mPaint.setStyle(Paint.Style.FILL);

        if (mCorrectiveScore == 0){
            mPaint.setColor(Color.GRAY);
        }else {
            setColorCriticalRates(mCorrectiveScore);
        }
        canvas.drawOval(getOval(level2), mPaint);

        setColorCriticalRates(1001);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(7);
        canvas.drawOval(getOval(level2), mPaint);


    }


    // BITMAP
    private Bitmap getRefreshLogo(){
        Resources resources = getResources();
        return BitmapFactory.decodeResource(resources, R.mipmap.baseline_refresh_black_24);
    }

    // rate color loading
    private  void setColorCriticalRates(int score){

        switch (this.compareWithLadder(score)){
            case LOW_RATE :
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mPaint.setColor(getContext().getColor(R.color.green_low_score));
                    break;
                }else {
                    mPaint.setColor(Color.GREEN);
                    break;
                }
            case MEDIUM_RATE :
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mPaint.setColor(getContext().getColor(R.color.orange_medium_score));
                    break;
                }else {
                    mPaint.setColor(Color.YELLOW);
                    break;
                }
            case HIGH_RATE :
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mPaint.setColor(getContext().getColor(R.color.red_high_score));
                    break;
                }else {
                    mPaint.setColor(Color.RED);
                    break;
                }
            case BORDER :
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mPaint.setColor(getContext().getColor(R.color.cardview_title_fmea));
                }else {
                    mPaint.setColor(Color.WHITE);
                }
                default: mPaint.setColor(Color.GRAY);
        }
    }

    // define the level compare with sharedpreferences ladder
    private String compareWithLadder(int score){
        if (score > mLowInt -1
                && score < mMediumInt){
            return LOW_RATE;
        }else if (score > mMediumInt -1
                && score < mHighInt){
            return MEDIUM_RATE;
        }else if (score > mHighInt -1
                && score < 1001){
            return HIGH_RATE;
        }else if (score > 1000){
            return BORDER;
        }else {
            return "";
        }
    }

    // define IPR calculating text
    private String getIPRDevelopment(String gravity, String probability, String detectability){
        return mRiskScore + EQUALS + gravity + X + probability + X + detectability;
    }


    /**
     * GEOMETRIE
     */

    //PATH : Window AERA
    private Path getWindowAera(){
        mPath.reset();

        mPath.moveTo(0, Utils.getPixelPercent(45, getHeight()));
        mPath.arcTo(new RectF(0, 0, Utils.getPixelPercent(45, getHeight()), Utils.getPixelPercent(45, getHeight())), 90, -90);
        mPath.lineTo(Utils.getPixelPercent(45, getHeight()), 0);
        mPath.lineTo(getWidth() - Utils.getPixelPercent(20, getHeight()), 0);
        mPath.lineTo(getWidth() -1, Utils.getPixelPercent(10, getHeight()));
        mPath.lineTo(getWidth() -1, getHeight());
        mPath.lineTo(Utils.getPixelPercent(20, getHeight()), getHeight());
        mPath.lineTo(0, getHeight() - Utils.getPixelPercent(10, getHeight()));
        mPath.close();

        return mPath;
    }

    // ECLIPSE LIGHTNING INDICATOR
    private RectF getOval(int level){
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return new RectF(
                    getWidth() - Utils.getPixelPercent(9, getWidth()), Utils.getPixelPercent(56 + level, getHeight()),
                    getWidth() - Utils.getPixelPercent(2, getWidth()), Utils.getPixelPercent(72 + level, getHeight())
            );
        } else {
            return new RectF(
                    getWidth() - Utils.getPixelPercent(6, getWidth()), Utils.getPixelPercent(56 + level, getHeight()),
                    getWidth() - Utils.getPixelPercent(2, getWidth()), Utils.getPixelPercent(72 + level, getHeight())
            );
        }
    }

    /**
     *  ACTUALISATION
     */

    public void actualise(int gravity, int probability, int detectability, int correctiveScore, int riskNumber, String title, String teamLeader){
        mTitle = title;
        mTeamLeader = teamLeader;
        mRiskNumber = String.valueOf(riskNumber);
        mGravity = gravity;
        mProbability = probability;
        mDetectability = detectability;
        mRiskScore = gravity * probability * detectability;
        mCorrectiveScore = correctiveScore;
        invalidate();
    }



}
