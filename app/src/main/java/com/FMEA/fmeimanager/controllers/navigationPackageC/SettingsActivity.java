package com.FMEA.fmeimanager.controllers.navigationPackageC;

import android.support.v4.app.Fragment;

import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.base.BaseActivity;
import com.FMEA.fmeimanager.database.Participant;

public class SettingsActivity extends BaseActivity implements SettingsFragment.SettingsListener {

    @Override
    protected Fragment getFirstFragment() {
        return SettingsFragment.newInstance();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_settings;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_settings;
    }

    @Override
    protected boolean isAChildActivity() {
        return false;
    }

    @Override
    public void updateSettingsNavHeader(Participant participant) {
        this.updateHeader(participant);
    }
}
