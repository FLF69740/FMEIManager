package com.example.fmeimanager.controllers.navigationPackageF;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.viewmodels.TeamViewModel;

public class TeamFmeiDashBoardActivity extends BaseActivity implements TeamFmeiFragment.TeamFmeiItemClickedListener {

    @Override
    protected Fragment getFirstFragment() {
        return TeamFmeiFragment.newInstance();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_team_fmei;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_team_fmei_dashboard;
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
        return false;
    }

    //TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getFirstFragment() instanceof TeamFmeiFragment && findViewById(getSecondFragmentLayout()) == null){
            getMenuInflater().inflate(R.menu.toolbar_menu_add_single, menu);
        }
        return true;
    }

    //BUTTON FRAGMENT
    @Override
    public void teamFmeiDashboard_To_teamFmeiBuilder(View view) {
        startActivity(new Intent(this, TeamFmeiBuilderActivity.class));
    }

    @Override
    public void updateTeamFmeiNavHeader(Participant participant) {
        this.updateHeader(participant);
    }


}
