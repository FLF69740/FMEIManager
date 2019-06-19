package com.FMEA.fmeimanager.controllers.navigationPackageB;

import android.support.v4.app.Fragment;

import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.base.BaseActivity;
import com.FMEA.fmeimanager.database.Participant;

public class ExportDatasActivity extends BaseActivity implements ExportDatasFragment.ExportDataListener {

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
    protected boolean isAChildActivity() {
        return false;
    }

    @Override
    public void updateExportDataNavHeader(Participant participant) {
        this.updateHeader(participant);
    }
}
