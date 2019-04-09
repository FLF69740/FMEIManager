package com.example.fmeimanager.controllers.navigationPackageE;

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

public class AboutActivity extends BaseActivity implements AboutFragment.AboutListener{

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


    @Override
    public void updateAboutNavHeader(Participant participant) {
        this.updateHeader(participant);
    }

}
