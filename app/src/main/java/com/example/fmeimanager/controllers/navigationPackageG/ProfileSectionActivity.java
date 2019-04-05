package com.example.fmeimanager.controllers.navigationPackageG;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.database.Participant;

public class ProfileSectionActivity extends BaseActivity implements ProfileSectionFragment.ProfileSectionItemClickedListener{

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

    // TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getFirstFragment() instanceof ProfileSectionFragment && findViewById(getSecondFragmentLayout()) == null){
            getMenuInflater().inflate(R.menu.toolbar_menu_add_single, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toolbar_dashboard_add :
                ((ProfileSectionFragment) getSupportFragmentManager().findFragmentById(getFragmentLayout())).createProfile();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static final String PARTICIPANT_ID_PROFILE_SECTION = "PARTICIPANT_ID_PROFILE_SECTION";

    // BUTTON FRAGMENT
    @Override
    public void ProfileSection_To_Profile(View view, long position) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(PARTICIPANT_ID_PROFILE_SECTION, position);
        startActivity(intent);
    }

    @Override
    public void updateNavHeader(Participant participant) {
        this.updateHeader(participant);
    }


}
