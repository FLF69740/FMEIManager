package com.FMEA.fmeimanager.base;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.controllers.navigationPackageE.AboutActivity;
import com.FMEA.fmeimanager.controllers.navigationPackageB.ExportDatasActivity;
import com.FMEA.fmeimanager.controllers.navigationPackageA.fmeiTheme.FmeiDashboardActivity;
import com.FMEA.fmeimanager.controllers.navigationPackageD.HelpActivity;
import com.FMEA.fmeimanager.controllers.navigationPackageG.ProfileSectionActivity;
import com.FMEA.fmeimanager.controllers.navigationPackageC.SettingsActivity;
import com.FMEA.fmeimanager.controllers.navigationPackageF.TeamFmeiDashBoardActivity;
import com.FMEA.fmeimanager.database.Participant;
import com.FMEA.fmeimanager.utils.BitmapStorage;


public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private TextView mNavUserName;
    private ImageView mNavUserPhoto;

    // abstract methods
    protected abstract Fragment getFirstFragment();
    protected abstract int getContentView();
    protected abstract int getFragmentLayout();
  //  protected abstract Fragment getSecondFragment();
  //  protected abstract int getSecondFragmentLayout();
    protected abstract boolean isAChildActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        this.configureToolbar();
        this.configureFragment(savedInstanceState);

    }

    protected void configureFragment(Bundle bundle){
        if (bundle == null){
            getSupportFragmentManager().beginTransaction()
                    .add(getFragmentLayout(), getFirstFragment())
                    .commit();
        }
    }

    /**
     *  TOOLBAR
     */

    private void configureToolbar(){
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (isAChildActivity()){
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled(true);
        } else {
            this.configureDrawerLayout();
            this.configureNavigationView();
        }
    }

    /**
     *  NAVIGATION DRAWER
     */

    // Handle back click to close menu
    @Override
    public void onBackPressed() {
        if (this.mDrawerLayout != null) {
            if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                this.mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }else {
            super.onBackPressed();
        }
    }

    //Configure Drawer Layout
    private void configureDrawerLayout(){
        mDrawerLayout = findViewById(R.id.general_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    //Configure NavigationView
    private void configureNavigationView(){
        NavigationView mNavigationView = findViewById(R.id.general_nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        View viewHeader = mNavigationView.getHeaderView(0);
        mNavUserName = viewHeader.findViewById(R.id.nav_userName);
        mNavUserPhoto = viewHeader.findViewById(R.id.nav_userPhoto);
    }

    //Update header
    protected void updateHeader(Participant participant){
        String completeName = participant.getName() + " " + participant.getForname();
        mNavUserName.setText(completeName);
        if (BitmapStorage.isFileExist(getApplication().getApplicationContext(), "P" + participant.getId())) {
            mNavUserPhoto.setImageBitmap(BitmapStorage.loadImage(getApplication().getApplicationContext(), "P" + participant.getId()));
        }else {
            mNavUserPhoto.setImageResource(R.drawable.blank_profile_picture);
        }
    }

    // Handle Navigation Item Click
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.drawer_item_fmei_dashboard :
                startActivity(new Intent(this, FmeiDashboardActivity.class));
                break;
            case R.id.drawer_item_team_fmei:
                startActivity(new Intent(this, TeamFmeiDashBoardActivity.class));
                break;
            case R.id.drawer_item_section_profile:
                startActivity(new Intent(this, ProfileSectionActivity.class));
                break;
            case R.id.drawer_item_export_datas:
                startActivity(new Intent(this, ExportDatasActivity.class));
                break;
            case R.id.drawer_item_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.drawer_item_help:
                startActivity(new Intent(this, HelpActivity.class));
                break;
            case R.id.drawer_item_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            default:
                break;
        }
        this.mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }




}
