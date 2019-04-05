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

public class ProfileActivity extends BaseActivity implements ProfileFragment.ProfileItemClickedListener{

    @Override
    protected Fragment getFirstFragment() {
        return ProfileFragment.newInstance(getIntent().getLongExtra(ProfileSectionActivity.PARTICIPANT_ID_PROFILE_SECTION, 1));
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


}
