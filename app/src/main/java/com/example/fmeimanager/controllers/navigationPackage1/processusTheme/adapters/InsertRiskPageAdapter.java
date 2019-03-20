package com.example.fmeimanager.controllers.navigationPackage1.processusTheme.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.fmeimanager.controllers.navigationPackage1.processusTheme.InsertRiskPageFragment;

public class InsertRiskPageAdapter extends FragmentPagerAdapter {

    private int mSize;
    private String mListString;

    public InsertRiskPageAdapter(FragmentManager fm, int size, String listString) {
        super(fm);
        this.mSize = size;
        this.mListString = listString;
    }

    @Override
    public Fragment getItem(int i) {
        return InsertRiskPageFragment.newInstance(mListString, i);
    }

    @Override
    public int getCount() {
        return mSize;
    }
}
