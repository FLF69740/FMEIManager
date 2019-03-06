package com.example.fmeimanager.controllers.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.controllers.fragments.RiskFileDescriptionFragment;

public class RiskFile extends BaseActivity implements RiskFileDescriptionFragment.RiskFileItemClickedListener {


    @Override
    protected Fragment getFirstFragment() {
        return RiskFileDescriptionFragment.newInstance();
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
    public void riskFile_To_CorrectiveAction(View view) {
        startActivity(new Intent(this, CorrectiveAction.class));
    }
}
