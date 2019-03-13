package com.example.fmeimanager.controllers.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.controllers.fragments.TeamFmeiBuilderFragment;

public class TeamFmeiBuilder extends BaseActivity implements TeamFmeiBuilderFragment.TeamFmeiBuilderItemClickedListener{


    @Override
    protected Fragment getFirstFragment() {
        return TeamFmeiBuilderFragment.newInstance();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_save_single, menu);
        return true;
    }

    @Override
    public void teamFmeiBuilder_To_teamFmeiDashboard(View view) {
        startActivity(new Intent(this, TeamFmeiDashBoard.class));
    }
}
