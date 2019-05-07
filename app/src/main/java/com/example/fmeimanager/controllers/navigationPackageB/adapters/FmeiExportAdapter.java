package com.example.fmeimanager.controllers.navigationPackageB.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.fmeimanager.controllers.navigationPackageB.ExportViewPagerFragment;
import com.example.fmeimanager.database.Fmei;

import java.util.List;

public class FmeiExportAdapter extends FragmentPagerAdapter {

    private List<Fmei> mListFmei;

    public FmeiExportAdapter(FragmentManager fm, List<Fmei> listString) {
        super(fm);
        this.mListFmei = listString;
    }

    @Override
    public Fragment getItem(int i) {
        return ExportViewPagerFragment.newInstance(mListFmei.get(i));
    }

    @Override
    public int getCount() {
        return mListFmei.size();
    }
}
