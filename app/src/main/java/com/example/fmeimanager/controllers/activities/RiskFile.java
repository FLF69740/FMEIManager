package com.example.fmeimanager.controllers.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.controllers.fragments.RiskFileDescriptionFragment;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.models.Participant;
import com.example.fmeimanager.viewmodels.RiskViewModel;

public class RiskFile extends BaseActivity implements RiskFileDescriptionFragment.RiskFileItemClickedListener {

    private RiskViewModel mRiskViewModel;

    @Override
    protected Fragment getFirstFragment() {
        return RiskFileDescriptionFragment.newInstance();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_risk_file;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_risk_file;
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
        getMenuInflater().inflate(R.menu.toolbar_menu_photo_single, menu);
        return true;
    }

    // BUTTON FRAGMENT
    @Override
    public void riskFile_To_CorrectiveAction(View view) {
        startActivity(new Intent(this, CorrectiveActionFile.class));
    }

    /**
     *  DATAS
     */

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.mRiskViewModel = ViewModelProviders.of(this, viewModelFactory).get(RiskViewModel.class);
        this.mRiskViewModel.init(1);
    }

    private void getAdministrator(long id){
        this.mRiskViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    /**
     *  UI
     */

    private void updateAdministrator(Participant participant){
        Toast.makeText(this, participant.getForname() + " " + participant.getName(), Toast.LENGTH_SHORT).show();
    }
}
