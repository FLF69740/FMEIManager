package com.example.fmeimanager.controllers.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.controllers.fragments.ProcessDashboardFragment;

public class ProcessDashboard extends BaseActivity implements ProcessDashboardFragment.ItemClickedListener {


    @Override
    protected Fragment getFirstFragment() {
        return ProcessDashboardFragment.newInstance();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_process_dashboard;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_process_dashboard;
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
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_newrisk_search_single, menu);
        return true;
    }

    @Override
    public void processDashBoard_To_RiskFile(View view) {
        startActivity(new Intent(this, RiskFile.class));
    }
}
