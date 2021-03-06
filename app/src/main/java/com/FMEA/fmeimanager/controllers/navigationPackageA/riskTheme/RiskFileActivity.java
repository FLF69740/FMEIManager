package com.FMEA.fmeimanager.controllers.navigationPackageA.riskTheme;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.base.BaseActivity;
import com.FMEA.fmeimanager.controllers.navigationPackageA.correctiveActionTheme.CorrectiveActionFileActivity;
import com.FMEA.fmeimanager.controllers.navigationPackageA.processusTheme.ProcessDashboardActivity;

public class RiskFileActivity extends BaseActivity implements RiskFileDescriptionFragment.RiskFileItemClickedListener {

    public static final String RISK_ID_TO_CORRECTIVE_ACTION_DASHBOARD = "RISK_ID_TO_CORRECTIVE_ACTION_DASHBOARD";
    public static final String RISK_NAME_TO_CORRECTIVE_ACTION_DASHBOARD = "RISK_NAME_TO_CORRECTIVE_ACTION_DASHBOARD";
    public static final String PROCESSUS_STEP_TO_CORRECTIVE_ACTION_DASHBOARD = "PROCESSUS_STEP_TO_CORRECTIVE_ACTION_DASHBOARD";
    public static final String FMEI_ID_TO_CORRECTIVE_ACTION_DASHBOARD = "FMEI_ID_TO_CORRECTIVE_ACTION_DASHBOARD";

    @Override
    protected Fragment getFirstFragment() {
        return RiskFileDescriptionFragment.newInstance(getIntent().getLongExtra(ProcessDashboardActivity.RISK_ID_TO_RISK_DASHBOARD, 200),
                getIntent().getIntExtra(ProcessDashboardActivity.PROCESSUS_STEP_TO_RISK_DASHBOARD, 0),
                getIntent().getLongExtra(ProcessDashboardActivity.FMEI_ID_TO_RISK_DASHBOARD, 0));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_risk_file;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_risk_file;
    }

    @Override
    protected boolean isAChildActivity() {
        return true;
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
                ((RiskFileDescriptionFragment) getSupportFragmentManager().findFragmentById(getFragmentLayout())).launchRiskPhotoActivity();
                return true;
            case R.id.toolbar_save:
                ((RiskFileDescriptionFragment) getSupportFragmentManager().findFragmentById(getFragmentLayout())).saveRiskSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // BUTTON FRAGMENT
    @Override
    public void riskFile_To_CorrectiveAction(View view, long riskId, int processusStepInteger, long fmeiId, String riskName) {
        Intent intent = new Intent(this, CorrectiveActionFileActivity.class);
        intent.putExtra(RISK_ID_TO_CORRECTIVE_ACTION_DASHBOARD, riskId);
        intent.putExtra(PROCESSUS_STEP_TO_CORRECTIVE_ACTION_DASHBOARD, processusStepInteger);
        intent.putExtra(FMEI_ID_TO_CORRECTIVE_ACTION_DASHBOARD, fmeiId);
        intent.putExtra(RISK_NAME_TO_CORRECTIVE_ACTION_DASHBOARD, riskName);
        startActivity(intent);
    }

    @Override
    public void riskFileDelete(View view) {
        finish();
    }


}
