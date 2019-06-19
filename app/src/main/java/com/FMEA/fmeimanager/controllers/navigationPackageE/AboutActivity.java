package com.FMEA.fmeimanager.controllers.navigationPackageE;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Button;

import com.FMEA.fmeimanager.R;

public class AboutActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about);
        Button close = findViewById(R.id.about_btn);
        close.setOnClickListener(v -> finish());
    }
}
