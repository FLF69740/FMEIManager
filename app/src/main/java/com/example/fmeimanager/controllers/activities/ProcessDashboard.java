package com.example.fmeimanager.controllers.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.controllers.fragments.ProcessDashboardFragment;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.models.Participant;
import com.example.fmeimanager.viewmodels.ProcessusViewModel;

public class ProcessDashboard extends BaseActivity implements ProcessDashboardFragment.ItemClickedListener {

    private ProcessusViewModel mProcessusViewModel;

    @Override
    protected Fragment getFirstFragment() {
        return ProcessDashboardFragment.newInstance();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_process_dashboard;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_process_dashboard;
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

    //TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_newrisk_search_single, menu);
        return true;
    }

    // BUTTON FRAGMENT
    @Override
    public void processDashBoard_To_RiskFile(View view) {
        startActivity(new Intent(this, RiskFile.class));
    }

    /**
     *  DATAS
     */

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.mProcessusViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProcessusViewModel.class);
        this.mProcessusViewModel.init(1);
    }

    private void getAdministrator(long id){
        this.mProcessusViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    /**
     *  UI
     */

    private void updateAdministrator(Participant participant){
        Toast.makeText(this, participant.getForname() + " " + participant.getName(), Toast.LENGTH_SHORT).show();
    }
}
