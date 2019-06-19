package com.FMEA.fmeimanager.controllers.navigationPackageG;

import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.base.BaseActivity;

public class ProfileActivity extends BaseActivity{

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
    protected boolean isAChildActivity() {
        return true;
    }

    // TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toolbar_photo:
                ((ProfileFragment) getSupportFragmentManager().findFragmentById(getFragmentLayout())).createPhotoIdentity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
