package com.example.fmeimanager.controllers.navigationPackage7;

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

public class ProfileActivity extends BaseActivity implements ProfileFragment.ProfileItemClickedListener{

    private ParticipantViewModel mParticipantViewModel;

    @Override
    protected Fragment getFirstFragment() {
        return ProfileFragment.newInstance();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_profile;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_profile;
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

    // TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_save_single, menu);
        return true;
    }

    //BUTTON FRAGMENT
    @Override
    public void profile_To_SectionProfile(View view) {
        startActivity(new Intent(this, ProfileSectionActivity.class));
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
        Toast.makeText(this, participant.getForname() + " " + participant.getName() + "/ProfileActivity", Toast.LENGTH_SHORT).show();
    }
}
