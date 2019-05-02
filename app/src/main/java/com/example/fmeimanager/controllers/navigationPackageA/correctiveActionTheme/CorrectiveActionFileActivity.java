package com.example.fmeimanager.controllers.navigationPackageA.correctiveActionTheme;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.controllers.navigationPackageA.riskTheme.RiskFileActivity;
import com.example.fmeimanager.controllers.navigationPackageA.processusTheme.ProcessDashboardActivity;

public class CorrectiveActionFileActivity extends BaseActivity implements CorrectiveActionFragment.CorrectiveActionItemClickedListener {

    public static final String FMEI_ID_TO_RISK_DASHBOARD = "FMEI_ID_TO_RISK_DASHBOARD";

    @Override
    protected Fragment getFirstFragment() {
        return CorrectiveActionFragment.newInstance(getIntent().getLongExtra(RiskFileActivity.RISK_ID_TO_CORRECTIVE_ACTION_DASHBOARD, 200),
                getIntent().getIntExtra(RiskFileActivity.PROCESSUS_STEP_TO_CORRECTIVE_ACTION_DASHBOARD, 0),
                getIntent().getLongExtra(RiskFileActivity.FMEI_ID_TO_CORRECTIVE_ACTION_DASHBOARD, 0),
                getIntent().getStringExtra(RiskFileActivity.RISK_NAME_TO_CORRECTIVE_ACTION_DASHBOARD));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_corrective_action;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_corrective_action;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_photo_single, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toolbar_photo:
                ((CorrectiveActionFragment) getSupportFragmentManager().findFragmentById(getFragmentLayout())).launchCorrectivePhotoActivity();
                return true;
            case R.id.toolbar_save:
                ((CorrectiveActionFragment) getSupportFragmentManager().findFragmentById(getFragmentLayout())).saveCorrectiveActionSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //BUTTON FRAGMENT
    @Override
    public void correctiveAction_To_riskFile(View view, long riskId, int processusStep, long fmeiId) {
        Intent intent = new Intent(this, RiskFileActivity.class);
        intent.putExtra(ProcessDashboardActivity.RISK_ID_TO_RISK_DASHBOARD, riskId);
        intent.putExtra(ProcessDashboardActivity.PROCESSUS_STEP_TO_RISK_DASHBOARD, processusStep);
        intent.putExtra(FMEI_ID_TO_RISK_DASHBOARD, fmeiId);
        startActivity(intent);
    }


}
