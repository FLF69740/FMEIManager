package com.example.fmeimanager.controllers.navigationPackage1.processusTheme;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;

public class ProcessusBuilderActivity extends BaseActivity {

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


}
