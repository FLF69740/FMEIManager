package com.example.fmeimanager.controllers.navigationPackageA.processusTheme;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.utils.Utils;

public class ProcessusBuilderActivity extends BaseActivity implements ProcessusBuilderFragment.ProcessBuilderItemClickedListener{

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
        getMenuInflater().inflate(R.menu.toolbar_menu_save_add_single, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toolbar_save:
                ((ProcessusBuilderFragment) getSupportFragmentManager().findFragmentById(getFragmentLayout())).saveBuilder();
                return true;
            case R.id.toolbar_dashboard_add:
                ((ProcessusBuilderFragment) getSupportFragmentManager().findFragmentById(getFragmentLayout())).addProcessus();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void processusBuilder_To_ProcessDashBoard(long fmeiId) {
        Log.i(Utils.INFORMATION_LOG, "PROCESSUS BUILDER SAVE - FMEI ID = " + fmeiId);
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_PROCESSUS_BUILDER_FMEI_ID, fmeiId);
        setResult(RESULT_OK, intent);
        finish();
    }
}
