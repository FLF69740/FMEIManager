package com.example.fmeimanager.controllers.navigationPackageD;

import android.support.v4.app.Fragment;
import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.database.Participant;

public class HelpActivity extends BaseActivity implements HelpFragment.HelpListener{


    @Override
    protected Fragment getFirstFragment() {
        return HelpFragment.newInstance();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_help;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_help;
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
    public void updateHelpNavHeader(Participant participant) {
        this.updateHeader(participant);
    }



}
