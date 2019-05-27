package com.example.fmeimanager.controllers.navigationPackageA.fmeiTheme;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.controllers.navigationPackageA.processusTheme.ProcessDashboardActivity;
import com.example.fmeimanager.database.Participant;

public class FmeiDashboardActivity extends BaseActivity implements FmeiDashboardFragment.ItemClickedListener {

    public static final String FMEI_ID_TO_PROCESSUS_DASHBOARD = "FMEI_ID_TO_PROCESSUS_DASHBOARD";

    @Override
    protected Fragment getFirstFragment() {
        return FmeiDashboardFragment.newInstance();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_fmei_dashboard;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_fmei_dashboard;
    }

    @Override
    protected boolean isAChildActivity() {
        return false;
    }

    // TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_add_single, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toolbar_dashboard_add :
                ((FmeiDashboardFragment) getSupportFragmentManager().findFragmentById(getFragmentLayout())).createFmea();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // BUTTON FRAGMENT
    @Override
    public void fmeiDashBoard_To_ProcessDashboard(View view, long fmeiId) {
        Intent intent = new Intent(this, ProcessDashboardActivity.class);
        intent.putExtra(FMEI_ID_TO_PROCESSUS_DASHBOARD, fmeiId);
        startActivity(intent);
    }

    // UPDATE NAVIGATION DRAWER HEADER
    @Override
    public void updateNavHeader(Participant participant) {
        this.updateHeader(participant);
    }

}
