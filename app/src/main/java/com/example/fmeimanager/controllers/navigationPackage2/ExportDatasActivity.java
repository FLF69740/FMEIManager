package com.example.fmeimanager.controllers.navigationPackage2;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.models.Participant;
import com.example.fmeimanager.viewmodels.ParticipantViewModel;

public class ExportDatasActivity extends BaseActivity {

    @Override
    protected Fragment getFirstFragment() {
        return ExportDatasFragment.newInstance();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_export_datas;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_export_datas;
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


}
