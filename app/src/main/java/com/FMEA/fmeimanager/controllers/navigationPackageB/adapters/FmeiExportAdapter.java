package com.FMEA.fmeimanager.controllers.navigationPackageB.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.FMEA.fmeimanager.controllers.navigationPackageB.ExportViewPagerFragment;
import com.FMEA.fmeimanager.database.Fmei;

import java.util.List;

public class FmeiExportAdapter extends FragmentPagerAdapter {

    private List<Fmei> mListFmei;

    public FmeiExportAdapter(FragmentManager fm, List<Fmei> listString) {
        super(fm);
        this.mListFmei = listString;
    }

    @Override
    public Fragment getItem(int i) {
        return ExportViewPagerFragment.newInstance(mListFmei.get(i), i, mListFmei.size());
    }

    @Override
    public int getCount() {
        return mListFmei.size();
    }
}
