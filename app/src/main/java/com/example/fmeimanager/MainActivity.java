package com.example.fmeimanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.fmeimanager.controllers.navigationPackageA.fmeiTheme.FmeiDashboardActivity;

public class MainActivity extends AppCompatActivity {

    public static final String BUNDLE_KEY_ACTIVE_USER = "BUNDLE_KEY_ACTIVE_USER";
    public static final long DEFAULT_USER_ID = 1;
    public static final String SHARED_MAIN_PROFILE_ID = "SHARED_MAIN_PROFILE_ID";
    public static final String SHARED_LOW_SCORE = "SHARED_LOW_SCORE";
    public static final String BUNDLE_KEY_LOW_VALUE = "BUNDLE_KEY_LOW_VALUE";
    public static final String SHARED_MEDIUM_SCORE = "SHARED_MEDIUM_SCORE";
    public static final String BUNDLE_KEY_MEDIUM_VALUE = "BUNDLE_KEY_LOW_VALUE";
    public static final String SHARED_HIGH_SCORE = "SHARED_HIGH_SCORE";
    public static final String BUNDLE_KEY_HIGH_VALUE = "BUNDLE_KEY_LOW_VALUE";
    public static final String SHARED_BUSINESS_NAME = "SHARED_BUSINESS_NAME";
    public static final String BUNDLE_KEY_BUSINESS_NAME = "BUNDLE_KEY_BUSINESS_NAME";

    public static final String BUNDLE_KEY_TEAM_LEADER_CATALOG_VIEWPAGER  = "BUNDLE_KEY_TEAM_LEADER_CATALOG_VIEWPAGER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imageButton = findViewById(R.id.run_touch_screen);
        imageButton.setOnClickListener(v -> startFirstActivity());

    }

    private void startFirstActivity(){
        startActivity(new Intent(this, FmeiDashboardActivity.class));
    }
}
