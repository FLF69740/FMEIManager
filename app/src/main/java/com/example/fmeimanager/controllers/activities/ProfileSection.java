package com.example.fmeimanager.controllers.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.controllers.fragments.ProfileSectionFragment;

public class ProfileSection extends BaseActivity implements ProfileSectionFragment.ProfileSectionItemClickedListener{


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
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getFirstFragment() instanceof ProfileSectionFragment && findViewById(getSecondFragmentLayout()) == null){
            getMenuInflater().inflate(R.menu.toolbar_menu_add_single, menu);
        }
        return true;
    }

    @Override
    public void ProfileSection_To_Profile(View view) {
        startActivity(new Intent(this, Profile.class));
    }
}
