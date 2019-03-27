package com.example.fmeimanager.controllers.navigationPackageA.processusTheme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.controllers.navigationPackageA.riskTheme.RiskFileActivity;
import com.example.fmeimanager.controllers.navigationPackageA.fmeiTheme.FmeiDashboardActivity;

public class ProcessDashboardActivity extends BaseActivity implements ProcessDashboardFragment.ItemClickedListener {

    public static final String RISK_ID_TO_RISK_DASHBOARD = "RISK_ID_TO_RISK_DASHBOARD";
    public static final String PROCESS_DASHBOARD_TO_PROCESS_BUILDER = "PROCESS_DASHBOARD_TO_PROCESS_BUILDER";

    private static final String SHARED_ID = "SHARED_ID";
    private static final String BUNDLE_RESTORE_PARENT_ID = "BUNDLE_RESTORE_PARENT_ID";

    @Override
    protected Fragment getFirstFragment() {
        return ProcessDashboardFragment.newInstance(
                getIntent().getLongExtra(FmeiDashboardActivity.FMEI_ID_TO_PROCESSUS_DASHBOARD,
                getSharedPreferences(SHARED_ID, MODE_PRIVATE).getLong(BUNDLE_RESTORE_PARENT_ID, 1000)));
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



    //TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_newrisk_search_single, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toolbar_dashboard_new_slot_1:
                ((ProcessDashboardFragment) getSupportFragmentManager().findFragmentById(getFragmentLayout())).createProcessus();
                return true;
            case R.id.toolbar_dashboard_new_slot_2:
                ((ProcessDashboardFragment) getSupportFragmentManager().findFragmentById(getFragmentLayout())).startCreateRisk();
                return true;
            case R.id.toolbar_search:
                Toast.makeText(this, "SEARCH", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //save sharedPreferences long about fmei Id
    private void saveSharedPreferences(long parentId){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_ID, MODE_PRIVATE);
        sharedPreferences.edit().putLong(BUNDLE_RESTORE_PARENT_ID, parentId).apply();
    }

    // BUTTON FRAGMENT
    @Override
    public void processDashBoard_To_RiskFile(View view, long riskId, long parentId) {
        saveSharedPreferences(parentId);
        Intent intent = new Intent(this, RiskFileActivity.class);
        intent.putExtra(RISK_ID_TO_RISK_DASHBOARD, riskId);
        startActivity(intent);
    }

    // BUTTON PROCESSUS BUILDER
    @Override
    public void processDashBoard_To_ProcessusBuilder(View view, long parentId) {
        saveSharedPreferences(parentId);
    }

}
