package com.example.fmeimanager.base;

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
import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.activities.About;
import com.example.fmeimanager.controllers.activities.ExportDatas;
import com.example.fmeimanager.controllers.activities.FmeiDashboard;
import com.example.fmeimanager.controllers.activities.Help;
import com.example.fmeimanager.controllers.activities.ProfileSection;
import com.example.fmeimanager.controllers.activities.Settings;
import com.example.fmeimanager.controllers.activities.TeamFmeiDashBoard;
import com.example.fmeimanager.models.Participant;


public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private View mViewHeader;
    private TextView mNavUserName;
    private ImageView mNavUserPhoto;

    // abstract methods
    protected abstract Fragment getFirstFragment();
    protected abstract int getContentView();
    protected abstract int getFragmentLayout();
    protected abstract Fragment getSecondFragment();
    protected abstract int getSecondFragmentLayout();
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
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
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
        mViewHeader = mNavigationView.getHeaderView(0);
        mNavUserName = mViewHeader.findViewById(R.id.nav_userName);
        mNavUserPhoto = mViewHeader.findViewById(R.id.nav_userPhoto);
    }

    //Update header
    protected void updateHeader(Participant participant){
        String completeName = participant.getName() + " " + participant.getForname();
        mNavUserName.setText(completeName);
     /*   mNavUserName.setText(user.getUsername());
        BitmapStorage.showImageInformations(this, user.getUrlPicture());
        if (!user.getUrlPicture().equals(User.EMPTY_CASE) && BitmapStorage.isFileExist(this, user.getUrlPicture())) {
            this.mNavUserPhoto.setImageBitmap(BitmapStorage.loadImage(this, user.getUrlPicture()));
        } else {
            this.mNavUserPhoto.setImageResource(R.drawable.bk_photo);
        }*/
    }

    // Handle Navigation Item Click
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.drawer_item_fmei_dashboard :
                startActivity(new Intent(this, FmeiDashboard.class));
                break;
            case R.id.drawer_item_team_fmei:
                startActivity(new Intent(this, TeamFmeiDashBoard.class));
                break;
            case R.id.drawer_item_section_profile:
                startActivity(new Intent(this, ProfileSection.class));
                break;
            case R.id.drawer_item_export_datas:
                startActivity(new Intent(this, ExportDatas.class));
                break;
            case R.id.drawer_item_settings:
                startActivity(new Intent(this, Settings.class));
                break;
            case R.id.drawer_item_help:
                startActivity(new Intent(this, Help.class));
                break;
            case R.id.drawer_item_about:
                startActivity(new Intent(this, About.class));
                break;
            default:
                break;
        }
        this.mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }




}
