package com.example.fmeimanager.controllers.navigationPackage1.correctiveActionTheme;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.controllers.navigationPackage1.riskTheme.RiskFileActivity;
import com.example.fmeimanager.controllers.navigationPackage1.processusTheme.ProcessDashboardActivity;

public class CorrectiveActionFileActivity extends BaseActivity implements CorrectiveActionFragment.CorrectiveActionItemClickedListener {

    @Override
    protected Fragment getFirstFragment() {
        return CorrectiveActionFragment.newInstance(getIntent().getLongExtra(RiskFileActivity.RISK_ID_TO_CORRECTIVE_ACTION_DASHBOARD, 200));
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
                Toast.makeText(this, "SEARCH", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.toolbar_save:
                Toast.makeText(this, "SAVE", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //BUTTON FRAGMENT
    @Override
    public void correctiveAction_To_riskFile(View view, long riskId) {
        Intent intent = new Intent(this, RiskFileActivity.class);
        intent.putExtra(ProcessDashboardActivity.RISK_ID_TO_RISK_DASHBOARD, riskId);
        startActivity(intent);
    }


}
