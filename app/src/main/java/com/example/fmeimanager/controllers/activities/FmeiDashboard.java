package com.example.fmeimanager.controllers.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.controllers.fragments.FmeiDashboardFragment;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.viewmodels.GeneralViewModel;

public class FmeiDashboard extends BaseActivity implements FmeiDashboardFragment.ItemClickedListener{

    private GeneralViewModel mGeneralViewModel;

    @Override
    protected Fragment getFirstFragment() {
        return FmeiDashboardFragment.newInstance();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_fmei_dashboard;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_fmei_dashboard;
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
    }

    // TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getFirstFragment() instanceof FmeiDashboardFragment && findViewById(getSecondFragmentLayout()) == null){
            getMenuInflater().inflate(R.menu.toolbar_menu_add_single, menu);
        }
        return true;
    }

    // BUTTON FRAGMENT
    @Override
    public void fmeiDashBoard_To_ProcessDashboard(View view) {
        startActivity(new Intent(this, ProcessDashboard.class));
    }

    /**
     *  DATAS
     */

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.mGeneralViewModel = ViewModelProviders.of(this, viewModelFactory).get(GeneralViewModel.class);
        this.mGeneralViewModel.init(1);
    }

}
