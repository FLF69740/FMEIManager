package com.example.fmeimanager.controllers.navigationPackage1.processusTheme;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapterInsertRisk extends FragmentPagerAdapter {

    private int mSize;
    private String mListString;

    public PageAdapterInsertRisk(FragmentManager fm, int size, String listString) {
        super(fm);
        this.mSize = size;
        this.mListString = listString;
    }

    @Override
    public Fragment getItem(int i) {
        return ProcessusIdPageFragment.newInstance(mListString, i);
    }

    @Override
    public int getCount() {
        return mSize;
    }
}
