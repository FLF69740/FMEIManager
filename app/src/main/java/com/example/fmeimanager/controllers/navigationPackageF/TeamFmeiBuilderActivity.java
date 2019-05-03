package com.example.fmeimanager.controllers.navigationPackageF;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.controllers.navigationPackageF.adapter.CatalogParticipantAdapter;
import com.example.fmeimanager.database.Fmei;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.TeamFmei;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.utils.Utils;
import com.example.fmeimanager.viewmodels.TeamViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_TEAM_LEADER_CATALOG_VIEWPAGER;
import static com.example.fmeimanager.MainActivity.SHARED_MAIN_PROFILE_ID;

public class TeamFmeiBuilderActivity extends AppCompatActivity implements CatalogParticipantFragment.CatalogParticipantListener{

    private TeamViewModel mTeamViewModel;

    private ArrayList<Participant> mCatalog;
    private List<TeamFmei> mTeamFmeiList;

    private ViewPager mViewPager;
    private ArrayList<String> mParticipantListID;
    private Fmei mFmei;
    private long mTeamLeaderId = 1;
    private int mPosition = 0;

    private TextView mParticipantCoutnTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_fmei_builder);

        if (savedInstanceState != null){
            mPosition = savedInstanceState.getInt("POSITION",0);
        }

        mParticipantCoutnTitle = findViewById(R.id.activity_team_fmei_participant_count);
        mCatalog = new ArrayList<>();
        mParticipantListID = new ArrayList<>();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.configureViewModel();
        this.getFmea(getIntent().getIntExtra(TeamFmeiDashBoardActivity.BUNDLE_TEAM_FMEI_FMEA_ID, 1));

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("POSITION", mPosition);
    }

    //CONFIGURE Participant catalog
    private void configureViewPager(){
        mViewPager = findViewById(R.id.team_fmei_builder_viewpager);
        mViewPager.setAdapter(new CatalogParticipantAdapter(getSupportFragmentManager(), mCatalog, mParticipantListID, mTeamLeaderId,
                BusinnessTeamFmei.getNumberOfPages(mCatalog.size())));
        mViewPager.setCurrentItem(mPosition);
    }

    //GTE textView countdown information
    private String getCountDownTitle(){
        int value = 12 - mParticipantListID.size();
        return getString(R.string.team_fmei_dashboard_title) + " : " + mParticipantListID.size() + " " +
                getString(R.string.team_fmei_builder_still) + " " + value + ")";
    }



    /**
     *  DATAS
     */

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.mTeamViewModel = ViewModelProviders.of(this, viewModelFactory).get(TeamViewModel.class);
        this.mTeamViewModel.init(1);
    }

    //GET fmea about team concerned
    private void getFmea(long id){
        this.mTeamViewModel.getFmei(id).observe(this, this::updateTeamLeaderName);
    }

    //GET all team fmea
    private void getConcernedTeamFmea(long fmeiId){
        this.mTeamViewModel.getTeamsWithLinkFmei(fmeiId).observe(this, this::updateTeamFmea);
    }

    //GET all participant
    private void getAllParticipant(){
        this.mTeamViewModel.getAllParticipant().observe(this, this::updateAllParticipant);
    }







    /**
     *  UI
     */

    //RECORD team leader about fmea
    private void updateTeamLeaderName(Fmei fmei){
        mFmei = fmei;
        mTeamLeaderId = fmei.getTeamLeader();
        getConcernedTeamFmea(fmei.getId());
    }

    //RECORD All team fmea about concerned fmea
    private void updateTeamFmea(List<TeamFmei> teamFmeiList){
        mTeamFmeiList = teamFmeiList;
        mParticipantListID = new ArrayList<>();
        for (int i = 0 ; i < mTeamFmeiList.size() ; i++){
            mParticipantListID.add(String.valueOf(mTeamFmeiList.get(i).getParticipantId()));
        }
        mParticipantCoutnTitle.setText(getCountDownTitle());
        getAllParticipant();
    }

    //RECORD all participant
    private void updateAllParticipant(List<Participant> participants){
        mCatalog = new ArrayList<>(participants);
        this.configureViewPager();
    }

    //Viewpager callback
    @Override
    public void catalogSendTag(Participant participant, int position, boolean addParticipant) {
        if (mCatalog.size() != 1){
            if (!addParticipant) {
                Log.i(Utils.INFORMATION_LOG, "DELETE : " + participant.getId() + " " + participant.getForname() + " " + participant.getName());
                for (TeamFmei teamFmei : mTeamFmeiList) {
                    if (teamFmei.getFmeiId() == mFmei.getId() && teamFmei.getParticipantId() == participant.getId()) {
                        mTeamViewModel.deleteTeamFmei(teamFmei.getTeamFmeiId());
                    }
                }
            }else {
                Log.i(Utils.INFORMATION_LOG, "ADD : " + participant.getId() + " " + participant.getForname() + " " + participant.getName());
                mTeamViewModel.createTeamFmei(new TeamFmei(mFmei.getId(), participant.getId()));
            }
            mPosition = position;

        }
        mParticipantCoutnTitle.setText(getCountDownTitle());
    }


}
