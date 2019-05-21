package com.example.fmeimanager.controllers.navigationPackageC;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.viewmodels.ParticipantViewModel;

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
