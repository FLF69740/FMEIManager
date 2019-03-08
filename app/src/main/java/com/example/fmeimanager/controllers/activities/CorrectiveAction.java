package com.example.fmeimanager.controllers.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.controllers.fragments.CorrectiveActionFragment;

public class CorrectiveAction extends BaseActivity implements CorrectiveActionFragment.CorrectiveActionItemClickedListener {


    @Override
    protected Fragment getFirstFragment() {
        return CorrectiveActionFragment.newInstance();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_photo_single, menu);
        return true;
    }

    @Override
    public void correctiveAction_To_riskFile(View view) {
        startActivity(new Intent(this, RiskFile.class));
    }
}
