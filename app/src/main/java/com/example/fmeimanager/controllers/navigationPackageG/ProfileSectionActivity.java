package com.example.fmeimanager.controllers.navigationPackageG;

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
import com.example.fmeimanager.viewmodels.ParticipantViewModel;

public class ProfileSectionActivity extends BaseActivity implements ProfileSectionFragment.ProfileSectionItemClickedListener{

    private ParticipantViewModel mParticipantViewModel;

    @Override
    protected Fragment getFirstFragment() {
        return ProfileSectionFragment.newInstance();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_profile_section;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_profile_section;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.configureViewModel();
        this.getAdministrator(1);
    }

    // TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getFirstFragment() instanceof ProfileSectionFragment && findViewById(getSecondFragmentLayout()) == null){
            getMenuInflater().inflate(R.menu.toolbar_menu_add_single, menu);
        }
        return true;
    }

    // BUTTON FRAGMENT
    @Override
    public void ProfileSection_To_Profile(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    /**
     *  DATAS
     */

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.mParticipantViewModel = ViewModelProviders.of(this, viewModelFactory).get(ParticipantViewModel.class);
        this.mParticipantViewModel.init(1);
    }

    private void getAdministrator(long id){
        this.mParticipantViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    /**
     *  UI
     */

    private void updateAdministrator(Participant participant){
        Toast.makeText(this, participant.getForname() + " " + participant.getName() + "/sectionProfile", Toast.LENGTH_SHORT).show();
        this.updateHeader(participant);
    }
}
