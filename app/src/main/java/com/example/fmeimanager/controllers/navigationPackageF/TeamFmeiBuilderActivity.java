package com.example.fmeimanager.controllers.navigationPackageF;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.viewmodels.TeamViewModel;

public class TeamFmeiBuilderActivity extends BaseActivity implements TeamFmeiBuilderFragment.TeamFmeiBuilderItemClickedListener{

    @Override
    protected Fragment getFirstFragment() {
        return TeamFmeiBuilderFragment.newInstance(getIntent().getIntExtra(TeamFmeiDashBoardActivity.BUNDLE_TEAM_FMEI_FMEA_ID, 1));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_team_fmei_builder;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_team_fmei_builder;
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
        getMenuInflater().inflate(R.menu.toolbar_menu_save_single, menu);
        return true;
    }

    //BUTTON FRAGMENT
    @Override
    public void teamFmeiBuilder_To_teamFmeiDashboard(View view) {
        startActivity(new Intent(this, TeamFmeiDashBoardActivity.class));
    }



}
