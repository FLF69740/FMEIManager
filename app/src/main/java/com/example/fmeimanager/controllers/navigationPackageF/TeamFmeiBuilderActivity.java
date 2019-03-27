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

    private TeamViewModel mTeamViewModel;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.configureViewModel();
        this.getAdministrator(1);
    }

    //TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_save_single, menu);
        return true;
    }

    //BUTTON FRAGMANT
    @Override
    public void teamFmeiBuilder_To_teamFmeiDashboard(View view) {
        startActivity(new Intent(this, TeamFmeiDashBoardActivity.class));
    }

    /**
     *  DATAS
     */

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.mTeamViewModel = ViewModelProviders.of(this, viewModelFactory).get(TeamViewModel.class);
        this.mTeamViewModel.init(1);
    }

    private void getAdministrator(long id){
        this.mTeamViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    /**
     *  UI
     */

    private void updateAdministrator(Participant participant){
        Toast.makeText(this, participant.getForname() + "/" + participant.getName() + "/builder", Toast.LENGTH_SHORT).show();
    }
}
