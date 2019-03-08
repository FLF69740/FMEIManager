package com.example.fmeimanager.controllers.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.controllers.fragments.AboutFragment;

public class About extends BaseActivity {


    @Override
    protected Fragment getFirstFragment() {
        return AboutFragment.newInstance();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_about;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_about;
    }

    @Override
    protected Fragment getSecondFragment() {
        return null;
    }

    @Override
    protected int getSecondFragmentLayout() {
        return 0;
    }

    @Override
    protected boolean isAChildActivity() {
        return false;
    }
}
