package com.example.fmeimanager.controllers.navigationPackageA.processusTheme;

import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;

public class ProcessusBuilderActivity extends BaseActivity{

    public static final String BUNDLE_PROCESSUS_BUILDER_FMEI_ID = "BUNDLE_PROCESSUS_BUILDER_FMEI_ID";

    @Override
    protected Fragment getFirstFragment() {
        return ProcessusBuilderFragment.newInstance(getIntent().getLongExtra(ProcessDashboardActivity.PROCESS_DASHBOARD_TO_PROCESS_BUILDER,0));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_processus_builder;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_processus_builder;
    }

    @Override
    protected boolean isAChildActivity() {
        return true;
    }

    //TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_add_single, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.toolbar_dashboard_add) {
            ((ProcessusBuilderFragment) getSupportFragmentManager().findFragmentById(getFragmentLayout())).addProcessus();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
