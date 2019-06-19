package com.FMEA.fmeimanager.controllers.navigationPackageA.riskTheme.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.FMEA.fmeimanager.controllers.navigationPackageA.riskTheme.RiskPhotoViewPagerFragment;

import java.util.List;

public class RiskPhotoPageAdapter extends FragmentStatePagerAdapter {

    private List<String> mStringList;

    public RiskPhotoPageAdapter(FragmentManager fm, List<String> stringList) {
        super(fm);
        this.mStringList = stringList;
    }

    public void reloadList(List<String> stringList){
        this.mStringList = stringList;
    }

    @Override
    public Fragment getItem(int i) {
        return RiskPhotoViewPagerFragment.newInstance(mStringList.get(i), i);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mStringList.size();
    }
}
