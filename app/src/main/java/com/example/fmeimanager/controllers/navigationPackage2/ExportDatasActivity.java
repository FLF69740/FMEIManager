package com.example.fmeimanager.controllers.navigationPackage2;

import android.support.v4.app.Fragment;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;

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
