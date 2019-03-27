package com.example.fmeimanager.controllers.navigationPackageA.processusTheme.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.fmeimanager.controllers.navigationPackageA.processusTheme.InsertRiskPageFragment;

import java.util.List;

public class InsertRiskPageAdapter extends FragmentPagerAdapter {

    private int mSize;
    private List<String> mListString;

    public InsertRiskPageAdapter(FragmentManager fm, int size, List<String> listString) {
        super(fm);
        this.mSize = size;
        this.mListString = listString;
    }

    @Override
    public Fragment getItem(int i) {
        return InsertRiskPageFragment.newInstance(mListString.get(i), i);
    }

    @Override
    public int getCount() {
        return mSize;
    }
}
